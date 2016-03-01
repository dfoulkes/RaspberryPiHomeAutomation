package com.foulkes.homeautomation.hue.controller;

import com.foulkes.homeautomation.hue.listener.PHSDKListenerImpl;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.events.HueComponentEvent;
import com.foulkes.lights.common.events.HueDO;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.DeviceNotFound;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.service.ComponentService;
import com.foulkes.lights.common.types.Hue;
import com.foulkes.lights.common.types.LightLevel;
import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;


/**
 * Created by danfoulkes on 03/02/2016.
 */

@Service
public class HueService implements HueService_ {

    private PHSDKListenerImpl phsdkListener;
    private PHHueSDK phHueSDK;
    private final String username = "669dea6d7eaae4a94f8cb18955de1240";
    private PHAccessPoint accessPoint;
    private DiscoverThread discoverThread;
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(HueService.class);


    @Autowired
    private ComponentService componentService;

    public HueService(){
        phHueSDK = PHHueSDK.getInstance();
        phHueSDK.setAppName("FoulkesHomeAuto");
        phHueSDK.setDeviceName("javaWebApp");


        phsdkListener = new PHSDKListenerImpl(phHueSDK, username);
        phHueSDK.getNotificationManager().registerSDKListener(phsdkListener);
        try {
            setupAccessPoint();
          //  discoverDevices();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public String getBridgeMacAddress(){
       return phsdkListener.getActionPionts().get(0).getMacAddress();
    }

    public PHSDKListenerImpl getPhsdkListener() {
        return phsdkListener;
    }


    @PostConstruct
    private void discoverDevices(){
        discoverThread = new DiscoverThread();
        new Thread(discoverThread).start();
    }

    @PreDestroy
    public void killDiscover(){
        try {
            discoverThread.kill();
            Thread.sleep(30010);
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
    }

    private void setupAccessPoint() throws InterruptedException {


        //scan for access points
            PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
            sm.search(true, true);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        // now connect 1st access point
        //TODO work with multiple access points
        accessPoint = phsdkListener.getActionPionts().get(0);
        accessPoint.setUsername(phsdkListener.getUsername());

        if(!phHueSDK.isAccessPointConnected(accessPoint))
                phHueSDK.connect(accessPoint);

        Thread.sleep(3000);
    }

    public HueDO getStatus(HueDO light) throws DeviceNotFound {
        return makeHue(getLight(light));
    }

    public HueDO updateDevice(HueDO light) throws DeviceNotFound {

        PHLight cacheDevice =  getLight(light);
        PHBridge bridge = phHueSDK.getSelectedBridge();

        // if the state of the device has changed
        if(light.isOn() != null && cacheDevice.getLastKnownLightState().isOn() != light.isOn()){
            PHLightState newState = new PHLightState();
            newState.setOn(light.isOn());
            bridge.updateLightState(cacheDevice,newState);
            if(!light.isOn()){
                makeHue(cacheDevice);
            }
        }

        if(light.getChangeHue() || light.getChangeLevel()){
            PHLightState newState = new PHLightState();
            newState.setOn(light.isOn());
            if(light.getChangeHue()){
                newState.setHue(light.getHue().getHue());
            }
            if(light.getChangeLevel()){
                newState.setBrightness(light.getLightLevel().getLevel());
            }
            bridge.updateLightState(cacheDevice,newState);
        }
        return makeHue(cacheDevice);
    }

    private PHLight getLight(HueDO light) throws DeviceNotFound {
        List<PHLight> lights = phHueSDK.getSelectedBridge().getResourceCache().getAllLights();
        for(PHLight x : lights) {
            if (x.getUniqueId().equals(light.getDeviceId())) {
                return x;
            }
        }
        throw new DeviceNotFound();
    }

    private HueComponentEvent makeHue(PHLight device){
        PHLightState state = device.getLastKnownLightState();
        EventState eventState = (state.isOn()? EventState.ON : EventState.OFF);
        Hue hueState = new Hue(state.getHue());
        LightLevel lightLevel = new LightLevel(state.getBrightness());
        return new HueComponentEvent(accessPoint.getIpAddress(), eventState, ServiceTypes.LIGHT_HUE, GenericType.LIGHT, device.getUniqueId(), hueState, lightLevel);
    }


    public HueDO getDevice(String deviceId) throws DeviceNotFound {
        ComponentsModel com = null;
        try {
            com = componentService.getById(accessPoint.getMacAddress(), deviceId);
            HueDO hueDo = new HueComponentEvent(com.getIp(), com.getComponentType(), com.getGenericType(), com.getAddressDetails());
            return  getStatus(hueDo);
        } catch (NotFound notFound) {
            throw new DeviceNotFound();
        }
    }

    class DiscoverThread implements Runnable{

        PHBridge bridge;
        volatile Boolean keepALive;

        public DiscoverThread(){
            bridge = phHueSDK.getSelectedBridge();
            keepALive = true;
        }

        public void kill(){
            keepALive = false;
        }

        @Override
        public void run() {
            while(keepALive){
                List<PHAccessPoint> accessPoints = phsdkListener.getActionPionts();
                PHAccessPoint accesPoint = accessPoints.get(0);
                try {
                    PHBridgeResourcesCache cache = bridge.getResourceCache();

                    for(PHLight x : cache.getAllLights()){
                        try {
                            componentService.add(accessPoint.getMacAddress(), ServiceTypes.LIGHT_HUE, accesPoint.getIpAddress(), x.getUniqueId(), GenericType.LIGHT);
                        } catch (AlreadyExists alreadyExists) {
                            logger.error(alreadyExists.getMessage());
                        } catch (FailedToAdd failedToAdd) {
                            logger.error(failedToAdd.getMessage());
                        }
                    }
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }


}

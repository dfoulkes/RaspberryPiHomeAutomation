package com.foulkes.homeautomation.hue;

import com.foulkes.homeautomation.hue.listener.PHSDKListenerImpl;
import com.foulkes.homeautomation.hue.listener.PHSDKListenerImplTest;
import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHBridgeSearchManager;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResourcesCache;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by danfoulkes on 03/02/2016.
 */
public class ConnectToHueTestIntegration {

    private PHHueSDK phHueSDK;
    private PHSDKListenerImpl listenerImplTest;
    private PHLightState red;
    private PHLightState green;
    private PHLightState blue;
    private int counter;
    private Boolean stopCapture;
    private ByteArrayOutputStream byteArrayOutputStream;


    @Before
    public void setup(){
        phHueSDK = PHHueSDK.getInstance();
        phHueSDK.setAppName("FoulkesHomeAuto");
        phHueSDK.setDeviceName("javaWebApp");
        listenerImplTest = new PHSDKListenerImpl(phHueSDK, "669dea6d7eaae4a94f8cb18955de1240");
        phHueSDK.getNotificationManager().registerSDKListener(listenerImplTest);
        red = createColour(65280);
        green = createColour(25500);
        blue = createColour(46920);
        counter = 0;
        stopCapture = false;
        findHue();
    }

    private void findHue(){
        PHBridgeSearchManager sm = (PHBridgeSearchManager) phHueSDK.getSDKService(PHHueSDK.SEARCH_BRIDGE);
        sm.search(true, true);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void scanForHue(){
        Boolean accessPointsFound = false;
        if(listenerImplTest.getActionPionts().size() > 0){
            accessPointsFound= true;
        }
        assertEquals(true, accessPointsFound);
    }

    @Test
    public void findDevices(){
        try {
            PHAccessPoint accessPoint = listenerImplTest.getActionPionts().get(0);
            accessPoint.setUsername(listenerImplTest.getUsername());
            phHueSDK.connect(accessPoint);
            Thread.sleep(10000);

            Boolean isNotEmpty = (listenerImplTest.getUsername() != null ? true: false);
            assertEquals(true, isNotEmpty);

            PHBridgeResourcesCache cache = phHueSDK.getSelectedBridge().getResourceCache();
            List myLights = cache.getAllLights();
            assertEquals(true, (cache.getAllLights().size()) > 0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private PHLightState createColour(Integer hue){
        PHLightState colour = new PHLightState();
        colour.setHue(hue);
        return colour;
    }

    private PHLightState getCurrentColour(){
        PHLightState current = null;
        switch (counter){
            case 0: current = red; break;
            case 1: current = blue; break;
            case 2: current = green; break;
        }
        counter++;
        if(counter > 2){
            counter = 0;
        }

        return current;
    }


    private PHLightState getColour(int light){
        PHLightState current = null;
        switch (light){
            case 0: current = red; break;
            case 1: current = blue; break;
            case 2: current = green; break;
        }
        return current;
    }

    @Test
    public void discoMode(){

        PHLightState currentColour;
        try {
            setupAccessPoint();

            PHBridge bridge = phHueSDK.getSelectedBridge();
            PHBridgeResourcesCache cache = phHueSDK.getSelectedBridge().getResourceCache();
            List<PHLight> myLights = cache.getAllLights();
            List<LightSequence> lights = new ArrayList<>();

            lights.add(new LightSequence(myLights.get(0),0));
            lights.add(new LightSequence(myLights.get(1),1));
            lights.add(new LightSequence(myLights.get(2),2));

            int count = 0;
            int device = 0;

            while(count < 100){
                Thread.sleep(300);
               // myLights = phHueSDK.getSelectedBridge().getResourceCache().getAllLights();
                LightSequence deviceObj = lights.get(device);
                if(deviceObj == null){
                    Thread.sleep(100);
                }

                bridge.updateLightState(deviceObj.getLight(),getColour((deviceObj.nextColour())));
                device++;
                if(device > 2){
                    device = 0;
                }
                count++;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dimmerMode(){

    }

    private void setupAccessPoint() throws InterruptedException {
        PHAccessPoint accessPoint = listenerImplTest.getActionPionts().get(0);
        accessPoint.setUsername(listenerImplTest.getUsername());
        phHueSDK.connect(accessPoint);
        Thread.sleep(5000);
    }

    @Test
    public void turnLightOnOff(){
        try {
            setupAccessPoint();
            Boolean isNotEmpty = (listenerImplTest.getUsername() != null ? true: false);
            assertEquals(true, isNotEmpty);

            PHBridgeResourcesCache cache = phHueSDK.getSelectedBridge().getResourceCache();

            PHBridge bridge = phHueSDK.getSelectedBridge();

            List<PHLight> myLights = cache.getAllLights();

            PHLightState stateoff = new PHLightState();
            stateoff.setOn(false);
            for(PHLight i : myLights){
                bridge.updateLightState(i,stateoff);
            }

            Thread.sleep(100);

            PHLightState stateon = new PHLightState();
            stateon.setOn(true);
            for(PHLight i : myLights){
                bridge.updateLightState(i,stateon);
            }
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}

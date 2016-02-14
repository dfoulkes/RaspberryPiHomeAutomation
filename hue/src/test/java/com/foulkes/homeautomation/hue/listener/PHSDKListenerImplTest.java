package com.foulkes.homeautomation.hue.listener;

import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHMessageType;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueParsingError;
import org.apache.log4j.Logger;


import javax.swing.text.StyledEditorKit;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danfoulkes on 03/02/2016.
 */
public class PHSDKListenerImplTest implements PHSDKListener {

    Logger logger = Logger.getLogger(PHSDKListenerImplTest.class);
    List<PHAccessPoint> actionPionts = new ArrayList<>();
    HashMap<String, PHBridge> bridges = new HashMap<>();
    PHHueSDK phHueSDK;
    String username;
    Boolean stopCapture = false;

    public PHSDKListenerImplTest(PHHueSDK phHueSDK){
        this.phHueSDK =phHueSDK;
    }


    public List<PHAccessPoint> getActionPionts() {
        return actionPionts;
    }

    public void setActionPionts(List<PHAccessPoint> actionPionts) {
        this.actionPionts = actionPionts;
    }

    public HashMap<String, PHBridge> getBridges() {
        return bridges;
    }

    public void setBridges(HashMap<String, PHBridge> bridges) {
        this.bridges = bridges;
    }

    public PHHueSDK getPhHueSDK() {
        return phHueSDK;
    }

    public void setPhHueSDK(PHHueSDK phHueSDK) {
        this.phHueSDK = phHueSDK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCacheUpdated(List<Integer> list, PHBridge phBridge) {
        if (list.contains(PHMessageType.LIGHTS_CACHE_UPDATED)) {
            logger.info("Lights Cache Updated ");
        }
    }

    @Override
    public void onBridgeConnected(PHBridge phBridge, String username) {
        phHueSDK.setSelectedBridge(phBridge);
        phHueSDK.enableHeartbeat(phBridge, PHHueSDK.HB_INTERVAL);
        logger.info("setting the username "+username);
        this.username = username;
    }

    @Override
    public void onAuthenticationRequired(PHAccessPoint phAccessPoint) {
        phHueSDK.startPushlinkAuthentication(phAccessPoint);
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAccessPointsFound(List<PHAccessPoint> list) {
        actionPionts = list;
    }

    @Override
    public void onError(int i, String s) {
        logger.error(s);
    }

    @Override
    public void onConnectionResumed(PHBridge phBridge) {
        phHueSDK.addBridge(phBridge);
    }

    @Override
    public void onConnectionLost(PHAccessPoint phAccessPoint) {

    }

    @Override
    public void onParsingErrors(List<PHHueParsingError> list) {

    }

}

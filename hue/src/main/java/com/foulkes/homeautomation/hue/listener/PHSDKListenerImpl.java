package com.foulkes.homeautomation.hue.listener;

import com.foulkes.homeautomation.hue.controller.HueService;
import com.philips.lighting.hue.sdk.PHAccessPoint;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.hue.sdk.PHSDKListener;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHHueParsingError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danfoulkes on 03/02/2016.
 */
public class PHSDKListenerImpl implements PHSDKListener {


    List<PHAccessPoint> actionPionts = new ArrayList<>();
    HashMap<String, PHBridge> bridges = new HashMap<>();
    PHHueSDK phHueSDK;
    String username;


    public PHSDKListenerImpl(PHHueSDK phHueSDK, String username){
        this.setUsername(username);
        this.phHueSDK = phHueSDK;
    }

    public List<PHAccessPoint> getActionPionts() {
        return actionPionts;
    }

    public void setActionPionts(List<PHAccessPoint> actionPionts) {
        this.actionPionts = actionPionts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void onCacheUpdated(List<Integer> list, PHBridge phBridge) {

    }

    @Override
    public void onBridgeConnected(PHBridge phBridge, String username) {
        phHueSDK.setSelectedBridge(phBridge);
        phHueSDK.enableHeartbeat(phBridge, PHHueSDK.HB_INTERVAL);

    }

    @Override
    public void onAuthenticationRequired(PHAccessPoint phAccessPoint) {

    }

    @Override
    public void onAccessPointsFound(List<PHAccessPoint> list) {
        actionPionts = list;
    }

    @Override
    public void onError(int i, String s) {

    }

    @Override
    public void onConnectionResumed(PHBridge phBridge) {

    }

    @Override
    public void onConnectionLost(PHAccessPoint phAccessPoint) {
        try {
            Thread.sleep(5000);
            phHueSDK.connect(phAccessPoint);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onParsingErrors(List<PHHueParsingError> list) {

    }
}

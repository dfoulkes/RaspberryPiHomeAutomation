package com.foulkes.lights.mvc.routing.homeauto.annotation.lights.service;

import com.foulkes.homeautomation.hue.controller.HueService_;
import com.foulkes.lights.common.events.HueDO;
import com.foulkes.lights.common.exception.DeviceNotFound;


import java.util.List;

/**
 * Created by danfoulkes on 10/02/2016.
 */
public class HueServiceControllerTest implements HueService_ {


    private void discoverDevices(){

    }

    private void setupAccessPoint() throws InterruptedException {

    }


    @Override
    public HueDO getStatus(HueDO light) throws DeviceNotFound {
        return null;
    }

    @Override
    public HueDO updateDevice(HueDO light) throws DeviceNotFound {
        return null;
    }

    @Override
    public HueDO getDevice(String deviceId) throws DeviceNotFound {
        return null;
    }

    @Override
    public String getBridgeMacAddress() {
        return null;
    }

}

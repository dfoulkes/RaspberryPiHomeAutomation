package com.foulkes.homeautomation.hue.controller;

import com.foulkes.lights.common.events.HueComponentEvent;
import com.foulkes.lights.common.events.HueDO;
import com.foulkes.lights.common.exception.DeviceNotFound;
import com.philips.lighting.model.PHLight;

/**
 * Created by danfoulkes on 10/02/2016.
 */
public interface HueService_ {

     HueDO getStatus(HueDO light) throws DeviceNotFound;

     HueDO updateDevice(HueDO light) throws DeviceNotFound;

     HueDO getDevice(String deviceId) throws DeviceNotFound;

     String getBridgeMacAddress();
}

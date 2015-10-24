package com.foulkes.lights.common.components;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class SimpleLight implements ComponentInterface {

   final String ip;

    public SimpleLight(String ip){
        this.ip = ip;
    }

    @Override
    public String getRoom() {
        return null;
    }

    @Override
    public ServiceTypes getDescription() {
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}

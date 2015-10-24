package com.foulkes.lights.common.interfaces;

import com.foulkes.lights.common.enums.ServiceTypes;

import java.io.Serializable;

/**
 * Created by danfoulkes on 03/10/15.
 */
public interface ComponentInterface extends Serializable{

    String getRoom();
    ServiceTypes getDescription();
    void start();
    void stop();
}

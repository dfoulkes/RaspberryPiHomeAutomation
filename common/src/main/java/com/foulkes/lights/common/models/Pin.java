package com.foulkes.lights.common.models;

import com.foulkes.lights.common.enums.Gpio;

import java.io.Serializable;

/**
 * Created by dfoulkes on 30/03/2015.
 */
public class Pin implements Serializable{

    private static final long serialVersionUID = -1449962682877672618L;
    private Gpio name;
    private Boolean status;


    public Pin(Gpio name){
        this.name = name;
        status= false;
    }

    public Gpio getName() {
        return name;
    }

    public void setName(Gpio name) {
        this.name = name;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

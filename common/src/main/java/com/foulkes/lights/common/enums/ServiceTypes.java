package com.foulkes.lights.common.enums;

/**
 * Created by danfoulkes on 03/10/15.
 */
public enum ServiceTypes {

    LIGHT("LIGHT"), CAM("CAM");

    private String name;
    ServiceTypes(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

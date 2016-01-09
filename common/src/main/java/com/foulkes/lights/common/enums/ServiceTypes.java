package com.foulkes.lights.common.enums;

/**
 * Created by danfoulkes on 03/10/15.
 */
public enum ServiceTypes {

    LIGHT_HOMEBREW("LIGHT_HOMEBREW"), CAM("CAM"), LIGHT_WEMO("LIGHT_WEMO");

    private String name;
    ServiceTypes(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

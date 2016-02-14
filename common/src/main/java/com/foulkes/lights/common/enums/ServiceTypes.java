package com.foulkes.lights.common.enums;

/**
 * Created by danfoulkes on 03/10/15.
 */
public enum ServiceTypes {

    SWITCH_HOMEBREW("SWITCH_HOMEBREW"), CAM("CAM"),SWITCH_WEMO("SWITCH_WEMO"), LIGHT_HUE("LIGHT_HUE");

    private String name;
    ServiceTypes(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}

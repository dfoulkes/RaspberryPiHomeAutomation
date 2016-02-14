package com.foulkes.lights.common.factory;

import com.foulkes.lights.common.components.SimpleLight;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class ImplementationFactory {

    //TODO
    public static ComponentInterface getInstance(String ip, ServiceTypes type){

        switch (type){
            case SWITCH_HOMEBREW: new SimpleLight(ip);
        }
        return null;
    }
}

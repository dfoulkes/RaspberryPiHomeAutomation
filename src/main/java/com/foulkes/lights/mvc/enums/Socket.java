package com.foulkes.lights.mvc.enums;

/**
 * Created by dfoulkes on 30/03/2015.
 */
public enum Socket {

    SOC1, SOC2, SOC3, SOC4;



    public static Socket find(String name){
        if(name.equals("1")){
            return SOC1;
        }
        else if(name.equals("2")){
            return SOC2;
        }
        else if(name.equals("3")){
            return SOC3;
        }
        else if(name.equals("4")){
            return SOC4;
        }
        else
            return SOC4;

    }
}

package com.foulkes.lights.mvc.factory;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.models.GpioPins;
import com.foulkes.lights.mvc.enums.Gpio;

/**
 * Created by dfoulkes on 30/03/2015.
 */

public class CommandFactory {

    /**
     * TODO update commands to represent correct ones
     * @param socket
     * @return
     */
    public static GpioPins getturnOnCommand(Socket socket){
        switch(socket){
            case SOC1: return new GpioPins(true,true,true,true);
            case SOC2: return new GpioPins(false,true,true,true);
            case SOC3: return new GpioPins(true,false,true,true);
            case SOC4: return new GpioPins(false,false,true,true);
        }
        return null;
    }

    public static GpioPins getturnOffCommand(Socket socket){
        switch(socket){
            case SOC1: return new GpioPins(true,true,true,false);
            case SOC2: return new GpioPins(false,true,true,false);
            case SOC3: return new GpioPins(true,false,true,false);
            case SOC4: return new GpioPins(false,false,true,false);
        }
        return null;
    }

}

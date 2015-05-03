package com.foulkes.lights.mvc.factory;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.models.GpioPins;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by danfoulkes on 30/03/15.
 */
public class CommandFactoryTests {

    @Test
    public void turnOn_Socket1(){

        GpioPins pins = CommandFactory.getturnOnCommand(Socket.SOC1);
        assertEquals(pins.getD0().getStatus(),true);
        assertEquals(pins.getD1().getStatus(),true);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),true);
    }

    @Test
    public void turnOffSocket1(){
        GpioPins pins = CommandFactory.getturnOffCommand(Socket.SOC1);
        assertEquals(pins.getD0().getStatus(),true);
        assertEquals(pins.getD1().getStatus(),true);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),false);
    }

    @Test
    public void turnOnSocket2(){
        GpioPins pins = CommandFactory.getturnOnCommand(Socket.SOC2);
        assertEquals(pins.getD0().getStatus(),false);
        assertEquals(pins.getD1().getStatus(),true);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),true);
    }

    @Test
    public void turnOffSocket2(){
        GpioPins pins = CommandFactory.getturnOffCommand(Socket.SOC2);
        assertEquals(pins.getD0().getStatus(),false);
        assertEquals(pins.getD1().getStatus(),true);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),false);
    }

    @Test
    public void turnOnSocket3(){
        GpioPins pins = CommandFactory.getturnOnCommand(Socket.SOC3);
        assertEquals(pins.getD0().getStatus(),true);
        assertEquals(pins.getD1().getStatus(),false);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),true);
    }

    @Test
    public void turnOffSocket3(){
        GpioPins pins = CommandFactory.getturnOffCommand(Socket.SOC3);
        assertEquals(pins.getD0().getStatus(),true);
        assertEquals(pins.getD1().getStatus(),false);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),false);
    }

    @Test
    public void turnOnSocket4(){
        GpioPins pins = CommandFactory.getturnOnCommand(Socket.SOC4);
        assertEquals(pins.getD0().getStatus(),false);
        assertEquals(pins.getD1().getStatus(),false);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),true);
    }

    @Test
    public void turnOffSocket4(){
        GpioPins pins = CommandFactory.getturnOffCommand(Socket.SOC4);
        assertEquals(pins.getD0().getStatus(),false);
        assertEquals(pins.getD1().getStatus(),false);
        assertEquals(pins.getD2().getStatus(),true);
        assertEquals(pins.getD3().getStatus(),false);
    }

}

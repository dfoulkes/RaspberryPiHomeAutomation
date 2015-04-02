package com.foulkes.lights.mvc.service;


import com.pi4j.io.gpio.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class PortService {
    private Logger logger = LogManager.getLogger(PortService.class);
    GpioController gpio;
    GpioPinDigitalOutput p11;
    GpioPinDigitalOutput p15;
    GpioPinDigitalOutput p16;
    GpioPinDigitalOutput p13;
    GpioPinDigitalOutput p18;
    GpioPinDigitalOutput p22;

    public PortService() {
        gpio = GpioFactory.getInstance();
    }

    public GpioPinDigitalOutput getP11() {
        return p11;
    }

    public void setP11(Boolean d0) {
        try {
            p11 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_11, "GPIO_11", ((d0 == true) ? PinState.HIGH : PinState.LOW));
        }catch(Exception e){
            logger.error("failed to set p11 ");
            e.printStackTrace();
        }
        this.p11 = p11;
    }

    public GpioPinDigitalOutput getP15() {
        return p15;
    }

    public void setP15(Boolean d1) {
        try {
            p15 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_15, "GPIO_15", ((d1 == true) ? PinState.HIGH : PinState.LOW));
        }catch(Exception e){
            logger.error("failed to set p15 ");
            e.printStackTrace();
        }
    }

    public GpioPinDigitalOutput getP16() {
        return p16;
    }

    public void setP16(Boolean d2) {
        try{
        p16 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_16, "GPIO_16", ((d2 == true) ? PinState.HIGH : PinState.LOW));}
        catch(Exception e){
            logger.error("failed to set p16 ");
            e.printStackTrace();
        }
    }

    public GpioPinDigitalOutput getP13() {
        return p13;
    }

    public void setP13(Boolean d3) {
        try{
        p13 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_13, "GPIO_13", ((d3 == true) ? PinState.HIGH : PinState.LOW));}
        catch(Exception e){
            logger.error("failed to set p13 ");
            e.printStackTrace();
        }
    }

    public GpioPinDigitalOutput getP18() {
        return p18;
    }

    public void setP18(Boolean d4) {
        try {
            p18 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_18, "GPIO_18", ((d4 == true) ? PinState.HIGH : PinState.LOW));
        }catch(Exception e){
            logger.error("failed to set p18 ");
            e.printStackTrace();
        }
    }

 public void setup() {
     logger.info("begin setup");
     gpio.setMode(PinMode.DIGITAL_OUTPUT);
     setP18(false);
     setP22(false);
 }

    public GpioPinDigitalOutput getP22() {
        return p22;
    }

    public void setP22(Boolean p22) {
        try {
            this.p22 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_22, "GPIO_22", ((p22 == true) ? PinState.HIGH : PinState.LOW));
        }catch(Exception e){
            logger.error("failed to set p22 ");
            e.printStackTrace();
        }
    }

    public void shutdownPorts(){
        try {
            gpio.unprovisionPin(p11);
            gpio.unprovisionPin(p15);
            gpio.unprovisionPin(p16);
            gpio.unprovisionPin(p13);
            gpio.unprovisionPin(p18);
            gpio.unprovisionPin(p22);
            gpio.shutdown();
        }catch(Exception e){

        }
    }



}
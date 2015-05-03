package com.foulkes.lights.mvc.models;

import com.foulkes.lights.mvc.enums.Gpio;

import java.io.Serializable;

/**
 * Created by dfoulkes on 30/03/2015.
 */
public class GpioPins implements Serializable{

    private static final long serialVersionUID = -3701311894304101096L;

    private Pin d0;
    private Pin d1;
    private Pin d2;
    private Pin d3;

    public GpioPins(){
        d0 = new Pin(Gpio.D0);
        d1 = new Pin(Gpio.D1);
        d2 = new Pin(Gpio.D2);
        d3 = new Pin(Gpio.D3);
    }

    public GpioPins(Boolean d0,
                    Boolean d1,
                    Boolean d2,
                    Boolean d3){
        this.d0 = new Pin(Gpio.D0);
        this.d0.setStatus(d0);
        this.d1 = new Pin(Gpio.D1);
        this.d1.setStatus(d1);
        this.d2 = new Pin(Gpio.D2);
        this.d2.setStatus(d2);
        this.d3 = new Pin(Gpio.D3);
        this.d3.setStatus(d3);
    }

    public Pin getD0() {
        return d0;
    }

    public void setD0(Pin d0) {
        this.d0 = d0;
    }

    public Pin getD1() {
        return d1;
    }

    public void setD1(Pin d1) {
        this.d1 = d1;
    }

    public Pin getD2() {
        return d2;
    }

    public void setD2(Pin d2) {
        this.d2 = d2;
    }

    public Pin getD3() {
        return d3;
    }

    public void setD3(Pin d3) {
        this.d3 = d3;
    }
}

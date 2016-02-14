package com.foulkes.lights.common.events;

/**
 * Created by danfoulkes on 06/02/2016.
 */
import com.foulkes.lights.common.types.Hue;
import com.foulkes.lights.common.types.LightLevel;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;

/**
 * Created by danfoulkes on 04/02/2016.
 */
public class HueComponentEvent extends ComponentEvent implements HueDO {


    Boolean changeHue =false;
    Boolean changeLevel =false;
    LightLevel lightLevel;
    Hue hue;
    /**
     * Constructor for basic operations like turn light ON light OFF
     * @param ip
     * @param eventState
     * @param serviceTypes
     * @param genericType
     * @param address
     */
    public HueComponentEvent(String ip, EventState eventState, ServiceTypes serviceTypes, GenericType genericType, String address) {
        super(ip, eventState, serviceTypes, genericType, address);
        changeHue = false;
        changeLevel= false;
    }

    public HueComponentEvent(String ip, ServiceTypes serviceTypes, GenericType genericType, String address) {
        super(ip, null, serviceTypes, genericType, address);
        changeHue = false;
        changeLevel= false;
    }

    /**
     * Constructor for turning light ON light OFF as well as changing the colour of the hue light
     * @param ip
     * @param eventState
     * @param serviceTypes
     * @param genericType
     * @param address
     * @param hue
     */
    public HueComponentEvent(String ip, EventState eventState, ServiceTypes serviceTypes, GenericType genericType, String address, Hue hue) {
        super(ip, eventState, serviceTypes, genericType, address);
        changeHue = true;
        changeLevel= false;
        this.hue = hue;
    }

    /**
     * Constructor for basic operations, changing colour of the light and the light level
     * @param ip
     * @param eventState
     * @param serviceTypes
     * @param genericType
     * @param address
     * @param hue
     * @param lightLevel
     */
    public HueComponentEvent(String ip, EventState eventState, ServiceTypes serviceTypes, GenericType genericType, String address, Hue hue, LightLevel lightLevel) {
        super(ip, eventState, serviceTypes, genericType, address);
        changeHue = true;
        changeLevel= true;
        this.hue = hue;
        this.lightLevel = lightLevel;
    }

    /**
     * Constructor for basic operations & changing the light level
     * @param ip
     * @param eventState
     * @param serviceTypes
     * @param genericType
     * @param address
     * @param lightLevel
     */
    public HueComponentEvent(String ip, EventState eventState, ServiceTypes serviceTypes, GenericType genericType, String address, LightLevel lightLevel) {
        super(ip, eventState, serviceTypes, genericType, address);
        changeHue = false;
        changeLevel= true;
        this.lightLevel = lightLevel;
    }

    @Override
    public Boolean getChangeHue() {
        return changeHue;
    }

    @Override
    public void setChangeHue(Boolean changeHue) {
        this.changeHue = changeHue;
    }

    @Override
    public String getDeviceId() {
        return getAddress();
    }

    @Override
    public Boolean isOn() {
        if(getEventState().equals(EventState.OFF))
            return false;
        else if(getEventState().equals(EventState.ON))
            return true;
        else
            return null;
    }

    @Override
    public void setOn(Boolean value) {
        setEventState((value? EventState.ON:EventState.OFF));
    }

    public void setHue(Hue hue) {
        this.hue = hue;
    }

    @Override
    public Hue getHue() {
        return hue;
    }

    @Override
    public LightLevel getLightLevel() {
        return lightLevel;
    }

    @Override
    public void setLightLevel(LightLevel lightLevel) {
        this.lightLevel = lightLevel;
    }

    @Override
    public Boolean getChangeLevel() {
        return changeLevel;
    }

    @Override
    public void setChangeLevel(Boolean changeLevel) {
        this.changeLevel = changeLevel;
    }
}
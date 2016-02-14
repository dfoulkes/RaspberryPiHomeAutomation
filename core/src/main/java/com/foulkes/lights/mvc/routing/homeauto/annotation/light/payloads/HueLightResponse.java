package com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads;

import com.foulkes.lights.common.events.HueDO;
import com.foulkes.lights.common.types.Hue;
import com.foulkes.lights.common.types.LightLevel;
import com.foulkes.lights.common.model.ComponentsModel;

/**
 * Created by danfoulkes on 06/02/2016.
 */
public class HueLightResponse extends LightResponse {

    Hue hue;
    LightLevel lightLevel;
    ComponentsModel componentsModel;


    public HueLightResponse(Hue hue, LightLevel lightLevel, ComponentsModel componentsModel) {
        this.hue = hue;
        this.lightLevel = lightLevel;
        this.componentsModel = componentsModel;
    }

    public HueLightResponse(HueDO hueDO, ComponentsModel componentsModel){
        this.hue = hueDO.getHue();
        this.lightLevel  = hueDO.getLightLevel();
        this.componentsModel = componentsModel;
    }

    public Hue getHue() {
        return hue;
    }

    public void setHue(Hue hue) {
        this.hue = hue;
    }

    public LightLevel getLightLevel() {
        return lightLevel;
    }

    public void setLightLevel(LightLevel lightLevel) {
        this.lightLevel = lightLevel;
    }

    public ComponentsModel getComponentsModel() {
        return componentsModel;
    }

    public void setComponentsModel(ComponentsModel componentsModel) {
        this.componentsModel = componentsModel;
    }

    @Override
    public String getMessage(){
        return "Hue Light:"+componentsModel.getUniquieId()+" state is:"+getEventState().toString()
                +", Hue:"+(hue != null ? hue.getHue():"not set")
                +", Dimmer:"+(lightLevel != null ? lightLevel.getLevel():"");
    }
}

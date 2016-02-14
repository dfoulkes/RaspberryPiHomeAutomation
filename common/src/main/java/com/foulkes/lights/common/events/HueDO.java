package com.foulkes.lights.common.events;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.types.Hue;
import com.foulkes.lights.common.types.LightLevel;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by danfoulkes on 06/02/2016.
 */
public interface HueDO {

    Hue getHue();
    void setHue(Hue hue);
    LightLevel getLightLevel();
    Boolean getChangeHue();
    Boolean getChangeLevel();
    void setChangeLevel(Boolean changeLevel);
    void setLightLevel(LightLevel lightLevel);
    void setChangeHue(Boolean changeHue);
    String getDeviceId();
    Boolean isOn();
    void setOn(Boolean value);
}

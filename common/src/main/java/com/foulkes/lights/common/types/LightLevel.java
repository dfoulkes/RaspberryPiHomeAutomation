package com.foulkes.lights.common.types;

/**
 * Created by danfoulkes on 04/02/2016.
 */
public class LightLevel {

    final int MIN = 1;
    final int MAX = 254;
    Integer level;

    public LightLevel(Integer level){
        if(level < MIN)
            this.level = MIN;
        else if(level > MAX)
            this.level = MAX;
        else
            this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}

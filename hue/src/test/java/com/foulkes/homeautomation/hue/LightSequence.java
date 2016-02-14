package com.foulkes.homeautomation.hue;

import com.philips.lighting.model.PHLight;

/**
 * Created by danfoulkes on 04/02/2016.
 */
public class LightSequence {

    PHLight light;
    int currentColour;

    public LightSequence(PHLight light, int currentColour){
        this.light = light;
        this.currentColour = currentColour;
    }

    public int nextColour(){
        currentColour++;
        if(currentColour > 2){
            currentColour = 0;
        }
        return currentColour;
    }

    public PHLight getLight() {
        return light;
    }

    public int getCurrentColour() {
        return currentColour;
    }
}

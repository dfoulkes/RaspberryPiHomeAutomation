package com.foulkes.lights.common.interfaces;

/**
 * Created by danfoulkes on 03/10/15.
 */
public interface DimmerEntity extends ComponentInterface {

    Double getDimmerSetting();
    void setDimmerSetting(Double setting);
}

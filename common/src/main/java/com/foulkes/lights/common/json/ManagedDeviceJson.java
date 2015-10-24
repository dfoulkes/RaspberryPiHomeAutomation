package com.foulkes.lights.common.json;

import com.foulkes.lights.common.model.ManagedDeviceModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danfoulkes on 11/10/2015.
 */
public class ManagedDeviceJson implements Serializable {


    ArrayList<ManagedDeviceModel> data;

    public ManagedDeviceJson(){
        data = new ArrayList<ManagedDeviceModel>();
    }

    public void add(ManagedDeviceModel componentsModel){
        data.add(componentsModel);
    }

    public ArrayList<ManagedDeviceModel> getData() {
        return data;
    }

    public void setData(ArrayList<ManagedDeviceModel> data) {
        this.data = data;
    }
}

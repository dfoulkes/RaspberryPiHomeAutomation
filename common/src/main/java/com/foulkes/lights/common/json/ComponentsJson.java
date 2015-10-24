package com.foulkes.lights.common.json;

import com.foulkes.lights.common.model.ComponentsModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by danfoulkes on 10/10/2015.
 */
public class ComponentsJson  implements Serializable {

    ArrayList<ComponentsModel> data;

    public ComponentsJson(){
        data = new ArrayList<ComponentsModel>();
    }

    public void add(ComponentsModel componentsModel){
        data.add(componentsModel);
    }

    public ArrayList<ComponentsModel> getData() {
        return data;
    }

    public void setData(ArrayList<ComponentsModel> data) {
        this.data = data;
    }
}

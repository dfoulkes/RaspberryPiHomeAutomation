package com.foulkes.lights.common.model;

import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.domain.ManagedDevice;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.factory.ImplementationFactory;
import com.foulkes.lights.common.interfaces.ComponentInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class ManagedDeviceModel implements Serializable{

    Long id;
    Set<ComponentsModel> componentsModel;
    String room;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ComponentsModel> getComponentsModel() {
        return componentsModel;
    }

    public void setComponentsModel(Set<ComponentsModel> componentsModel) {
        this.componentsModel = componentsModel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void addComponent(ComponentsModel cm) throws AlreadyExists{
        //check if exists
        for(ComponentsModel x: componentsModel){
           if(x.getUniquieId().equals(cm.getUniquieId())){
               //already exists
               throw new AlreadyExists();
           }
        }
        componentsModel.add(cm);
    }

    public static ManagedDeviceModel build(ManagedDevice mod){
        ManagedDeviceModel managedDevice = new ManagedDeviceModel();
        managedDevice.setId(mod.getId());
        managedDevice.setRoom(mod.getRoom());
        HashSet<ComponentsModel> com = ComponentsModel.builds(mod. getComponents());
        managedDevice.setComponentsModel(com);
        return build(mod);
    }
}

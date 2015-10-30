package com.foulkes.lights.common.model;

import com.foulkes.lights.common.domain.ManagedDevice;
import com.foulkes.lights.common.factory.ImplementationFactory;
import com.foulkes.lights.common.interfaces.ComponentInterface;

import java.io.Serializable;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class ManagedDeviceModel implements Serializable{

    Long id;
    ComponentInterface componentsModel;
    String room;
    ComponentInterface entity;

    public ComponentInterface getEntity() {
        return entity;
    }

    public void setEntity(ComponentInterface entity) {
        this.entity = entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ComponentInterface getComponentsModel() {
        return componentsModel;
    }

    public void setComponentsModel(ComponentInterface componentsModel) {
        this.componentsModel = componentsModel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public static ManagedDeviceModel build(ManagedDevice mod){
        ManagedDeviceModel managedDevice = new ManagedDeviceModel();
        ComponentsModel com = ComponentsModel.build(mod.getComponents());
        managedDevice.setComponentsModel(ImplementationFactory.getInstance(com.getIp(), com.getComponentType()));
        managedDevice.setEntity(mod.getEntity());
        return build(mod);
    }
}

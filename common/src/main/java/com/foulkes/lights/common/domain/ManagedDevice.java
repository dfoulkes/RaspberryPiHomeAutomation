package com.foulkes.lights.common.domain;

import com.foulkes.lights.common.interfaces.ComponentInterface;

import javax.persistence.*;

/**
 * Created by danfoulkes on 03/10/15.
 */

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "ManagedDevice.getAll", query = "SELECT managedDevice FROM ManagedDevice managedDevice"),
        @NamedQuery(name = "ManagedDevice.getById", query = "SELECT managedDevice FROM ManagedDevice managedDevice WHERE managedDevice.id = :id"),
})
public class ManagedDevice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    Components componentsModel;
    String room;
    ComponentInterface entity;

    public Components getComponentsModel() {
        return componentsModel;
    }

    public void setComponentsModel(Components componentsModel) {
        this.componentsModel = componentsModel;
    }

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

    public Components getComponents() {
        return componentsModel;
    }

    public void setComponents(Components componentsModel) {
        this.componentsModel = componentsModel;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

}

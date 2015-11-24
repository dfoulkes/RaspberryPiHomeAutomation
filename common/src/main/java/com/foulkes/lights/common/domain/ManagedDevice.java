package com.foulkes.lights.common.domain;

import com.foulkes.lights.common.interfaces.ComponentInterface;
import com.foulkes.lights.common.model.ManagedDeviceModel;

import javax.persistence.*;
import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */

@Entity
@NamedQueries(value = {
        @NamedQuery(name = "ManagedDevice.getAll", query = "SELECT managedDevice FROM ManagedDevice managedDevice"),
        @NamedQuery(name = "ManagedDevice.getByRoom", query = "SELECT managedDevice FROM ManagedDevice managedDevice WHERE managedDevice.room =:id"),
        @NamedQuery(name = "ManagedDevice.getById", query = "SELECT managedDevice FROM ManagedDevice managedDevice WHERE managedDevice.id = :id"),
})
public class ManagedDevice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Long id;
    @OneToMany
    @JoinColumn(name="uniquieId")
    List<Components> components;

    String room;


    public List<Components> getComponents() {
        return components;
    }

    public void setComponents(List<Components> components) {
        this.components = components;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public static ManagedDevice build(ManagedDeviceModel model) {
        ManagedDevice device = new ManagedDevice();
        device.setId(model.getId());
        device.setRoom(model.getRoom());
        device.setComponents(Components.builds(model.getComponentsModel()));
        return device;
    }
}

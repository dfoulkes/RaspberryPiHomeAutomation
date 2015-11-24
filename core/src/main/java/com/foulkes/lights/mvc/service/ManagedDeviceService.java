package com.foulkes.lights.mvc.service;

/**
 * Created by danfoulkes on 03/10/15.
 */
import com.foulkes.lights.common.domain.ManagedDevice;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */

public interface ManagedDeviceService {

    ManagedDeviceModel addDevice(ComponentsModel newComponent, ManagedDeviceModel room) throws AlreadyExists;
    ManagedDeviceModel add(ManagedDeviceModel model);
    ManagedDeviceModel getDevice(Long uniqueId) throws NotFound;
    ManagedDeviceModel update(ManagedDeviceModel device);
    ManagedDeviceModel getByRoom(String room);
    List<ManagedDeviceModel> getAll();

    void removeDeviceFromRoom(ComponentsModel component, ManagedDeviceModel room);

    Boolean isManaged(ComponentsModel component);
}
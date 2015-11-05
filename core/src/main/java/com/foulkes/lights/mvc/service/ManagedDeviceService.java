package com.foulkes.lights.mvc.service;

/**
 * Created by danfoulkes on 03/10/15.
 */
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */

public interface ManagedDeviceService {

    ManagedDeviceModel add(ComponentsModel newComponent, String room);
    ManagedDeviceModel getDevice(Long uniqueId) throws NotFound;
    ManagedDeviceModel update(ManagedDeviceModel device);
    List<ManagedDeviceModel> getAllDevicesInARoom(String room);
    List<ManagedDeviceModel> getAll();

}
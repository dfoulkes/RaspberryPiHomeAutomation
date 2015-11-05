package com.foulkes.lights.mvc.service;

import com.foulkes.lights.common.doa.ManageDeviceServiceDao;

import com.foulkes.lights.common.domain.ManagedDevice;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.factory.ImplementationFactory;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Component
public class ManagedDeviceServiceImpl implements ManagedDeviceService {

    @Autowired
    private ManageDeviceServiceDao manageDeviceServiceDao;

    @Override
    public ManagedDeviceModel add(ComponentsModel newComponent, String room) {
        ManagedDevice device = new ManagedDevice();
        device.setEntity(ImplementationFactory.getInstance(newComponent.getIp()  , newComponent.getComponentType()));
        return manageDeviceServiceDao.add(newComponent, room);
    }

    @Override
    public ManagedDeviceModel getDevice(Long uniqueId) throws NotFound {
        return manageDeviceServiceDao.getByDevice(uniqueId);
    }

    @Override
    public ManagedDeviceModel update(ManagedDeviceModel device) {
        return manageDeviceServiceDao.update(device);
    }

    @Override
    public List<ManagedDeviceModel> getAllDevicesInARoom(String room) {
        return manageDeviceServiceDao.getByRoom(room);
    }

    @Override
    public List<ManagedDeviceModel> getAll() {
        return manageDeviceServiceDao.getAll();
    }
}

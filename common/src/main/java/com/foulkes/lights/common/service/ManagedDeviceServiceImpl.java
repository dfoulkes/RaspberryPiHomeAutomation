package com.foulkes.lights.common.service;

import com.foulkes.lights.common.doa.ManageDeviceServiceDao;

import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Component
public class ManagedDeviceServiceImpl implements ManagedDeviceService {


    public ManagedDeviceServiceImpl(ManageDeviceServiceDao manageDeviceServiceDao){
        this.manageDeviceServiceDao = manageDeviceServiceDao;
    }

    public ManagedDeviceServiceImpl(){

    }

    @Autowired
    private ManageDeviceServiceDao manageDeviceServiceDao;

    private Logger logger = Logger.getLogger(ManagedDeviceServiceImpl.class);

    @Override
    public ManagedDeviceModel addDevice(ComponentsModel newComponent, ManagedDeviceModel room) throws AlreadyExists{

        ManagedDeviceModel roomModel =manageDeviceServiceDao.getByRoom(room.getRoom());
        if(room == null){
            roomModel = new ManagedDeviceModel();
            roomModel.setRoom(room.getRoom());
            roomModel = add(roomModel);
        }
        roomModel.addComponent(newComponent);
        return manageDeviceServiceDao.update(roomModel);
    }

    @Override
    public ManagedDeviceModel add(ManagedDeviceModel model) {
        if(model != null){
            manageDeviceServiceDao.add(model);
            return manageDeviceServiceDao.getByRoom(model.getRoom());
        }
        else{
            return null;
        }
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
    public ManagedDeviceModel getByRoom(String room) {
        return manageDeviceServiceDao.getByRoom(room);
    }

    @Override
    public List<ManagedDeviceModel> getAll() {
        return manageDeviceServiceDao.getAll();
    }

    @Override
    public void removeDeviceFromRoom(ComponentsModel component, ManagedDeviceModel room) {
        room.getComponentsModel().remove(component);
        manageDeviceServiceDao.update(room);
    }

    @Override
    public Boolean isManaged(ComponentsModel component) {
        List<ManagedDeviceModel> allDevices = manageDeviceServiceDao.getAll();
        for(ManagedDeviceModel x : allDevices){
            if(x.getComponentsModel().contains(component)){
                return true;
            }
        }
        return false;
    }
}

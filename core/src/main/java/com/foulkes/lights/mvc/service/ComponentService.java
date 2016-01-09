package com.foulkes.lights.mvc.service;


import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */

public interface ComponentService {

     List<ComponentsModel> getAll();
     ComponentsModel getById(String uniqueId, String address) throws NotFound;
     ComponentsModel add(String uniqueId, ServiceTypes type, String ip, String address, GenericType genericType) throws AlreadyExists, FailedToAdd;
     ComponentsModel update(ComponentsModel componentsModel) throws FailedToAdd;
}

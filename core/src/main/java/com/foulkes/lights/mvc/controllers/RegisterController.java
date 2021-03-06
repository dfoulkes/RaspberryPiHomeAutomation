package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.RegistrationStatus;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.service.ComponentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by danfoulkes on 03/10/15.
 */

@RestController
public class RegisterController {

    @Autowired
    private ComponentService componentService;

    private Logger logger = Logger.getLogger(RegisterController.class);

    @RequestMapping("/register")
    public RegistrationStatus
    registerComponent(String componentId, ServiceTypes type, String ip, String address, GenericType genericType) {
        try {
            logger.info("registering"+componentId+":"+type+":"+ip+": address: "+address);
            componentService.add(componentId,type,ip, address, genericType);
        } catch (AlreadyExists alreadyExists) {
            return RegistrationStatus.ALREADY_EXISTS;
        } catch (FailedToAdd failedToAdd) {
            return RegistrationStatus.ERROR;
        }
        return RegistrationStatus.OK;
    }

}

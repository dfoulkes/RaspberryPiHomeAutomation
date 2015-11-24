package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.common.json.ManagedDeviceJson;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import com.foulkes.lights.mvc.service.ComponentService;
import com.foulkes.lights.mvc.service.ManagedDeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by danfoulkes on 09/10/2015.
 */


@RestController
public class ManagedDeviceController {

    @Autowired
    private ManagedDeviceService managedDeviceService;


    private Logger logger = Logger.getLogger(ManagedDeviceController.class);

    @RequestMapping("/roomGetAll.json")
    public List<ManagedDeviceModel> GetAll() {
        logger.debug("generating room data.");
        List<ManagedDeviceModel> coms = managedDeviceService.getAll();
        ManagedDeviceJson finalList = new ManagedDeviceJson();
        for(ManagedDeviceModel i : coms){
            finalList.add(i);
        }
        logger.debug("romm data size is:"+finalList.size());
        return finalList;
    }

    @RequestMapping("/updateRoom.json")
    public void update(@RequestParam ManagedDeviceModel managedDeviceModel){
        logger.info("updating managed device");
        managedDeviceService.update(managedDeviceModel);
        logger.info("device updated");
    }


    @RequestMapping("/addRoom.json")
    public void add(@RequestParam ManagedDeviceModel managedDeviceModel){
        logger.info("updating managed device");
        managedDeviceService.add(managedDeviceModel);
        logger.info("device updated");
    }


}

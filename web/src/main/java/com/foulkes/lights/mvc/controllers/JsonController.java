package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.json.ComponentsJson;
import com.foulkes.lights.common.json.ManagedDeviceJson;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.WebEvent;
import com.foulkes.lights.mvc.routing.event.ComponentAction;
import com.foulkes.lights.mvc.routing.event.EventState;
import com.foulkes.lights.mvc.service.ComponentService;
import com.foulkes.lights.mvc.service.ManagedDeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class JsonController {


    @Autowired
    private ComponentService componentService;
    @Autowired
    private ManagedDeviceService managedDeviceService;
    @Autowired
    private WebEvent order;



    private Logger logger = Logger.getLogger(JsonController.class);


    /**
     * Call the spring integration gateway with the component event
     * @param status
     * @param componentId
     * @return
     */
    @RequestMapping("/setComponent.json")
    public EventState getComponentData(@RequestParam String status, @RequestParam String componentId) {
        logger.info("calling json");

        EventState state;
        if(status.compareTo("ON") == 0){
            state= EventState.ON;
        }
        else{
            state= EventState.OFF;
        }
        ComponentAction action = new ComponentAction();
        action.setState(state);
        action.setComponentId(componentId);

        try {
            ComponentsModel com = componentService.getById(componentId);
            ComponentEvent message = new ComponentEvent(com.getIp(), state, com.getComponentType(), com.getAddressDetails());
            EventState x = order.process(message);
            state = x;
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

        return state;
    }



    @RequestMapping("/getAllComponents.json")
    public ComponentsJson getComponentData() {
        logger.info("calling json");
        List<ComponentsModel> coms = componentService.getAll();
        ComponentsJson finalList = new ComponentsJson();
        for(ComponentsModel i : coms){
            finalList.add(i);
        }
        return finalList;
    }

    @RequestMapping("/getDevices.json")
    public ManagedDeviceJson getDevicesData() {
        logger.info("calling json");
        List<ManagedDeviceModel> coms = managedDeviceService.getAll();
        ManagedDeviceJson finalList = new ManagedDeviceJson();
        for(ManagedDeviceModel i : coms){
            finalList.add(i);
        }
        return finalList;
    }

}
package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.common.doa.EventDao;
import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.domain.Event;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.json.ComponentsJson;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.mvc.routing.factory.ProcessEvent;
import com.foulkes.lights.mvc.routing.event.ComponentAction;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads.HueLightResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads.LightResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import com.foulkes.lights.common.service.ComponentService;
import com.foulkes.lights.common.service.ManagedDeviceService;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.payloads.SwitchResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.payloads.WemoResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


@RestController
public class ComponentController {


    @Autowired
    private ComponentService componentService;
    @Autowired
    private ManagedDeviceService managedDeviceService;
    @Autowired
    private EventDao eventDao;
    @Autowired
    private ProcessEvent processEvent;


    private Logger logger = Logger.getLogger(ComponentController.class);


    /**
     * Call the spring integration gateway with the component event
     *
     * @param id
     * @param subcommand
     * @return
     */
    @RequestMapping("/getById.json")
    public ComponentsModel getComponentData(@RequestParam String id, @RequestParam String subcommand) {
        ComponentsModel com = null;
        try {
            com = componentService.getById(id, subcommand);
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return com;
    }


    @RequestMapping("/setComponent.json")
    public EventState getComponentData(@RequestParam String status, @RequestParam String componentId, @RequestParam String subcommand) {
        logger.info("calling json");

        EventState state;

        switch (status) {
            case "ON":
                state = EventState.ON;
                break;
            case "OFF":
                state = EventState.OFF;
                break;
            case "STATUS":
                state = EventState.STATUS;
                break;
            default:
                state = EventState.STATUS;
        }

        ComponentAction action = new ComponentAction();
        action.setState(state);
        action.setComponentId(componentId);

        try {
            ComponentsModel com = componentService.getById(componentId, subcommand);

            Response x = processEvent.processComponentEvent(com, state);
            Event event = new Event();
            event.setComponents(Components.build(com));
            event.setOnDate(new Date(Calendar.getInstance().getTimeInMillis()));
            event.setDescription(x.getMessage());
            eventDao.add(event);
            return processResponse(x);

        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }
        return null;
    }


    private EventState processResponse(Response response){

        if(response instanceof LightResponse){
            return ((LightResponse) response).getEventState();
        }
        else if(response instanceof SwitchResponse){
            return ((SwitchResponse) response).getEventState();
        }
        return null;
    }

    @RequestMapping("/getAllComponents.json")
    public ComponentsJson getComponentData() {
        List<ComponentsModel> coms = componentService.getAll();
        ComponentsJson finalList = new ComponentsJson();
        for (ComponentsModel i : coms) {
            finalList.add(i);
        }
        return finalList;
    }


    @RequestMapping("/updateComponent.json")
    public ComponentsJson updateComponent(@RequestParam String oldComponent, @RequestParam String componentId,
                                          @RequestParam String ip, @RequestParam String address) {
        List<ComponentsModel> coms = null;
        ComponentsJson finalList = new ComponentsJson();
        try {
            coms = componentService.getByUniqueId(oldComponent);

            for (ComponentsModel i : coms) {
                i.setUniquieId(componentId);
                i.setIp(ip);
                i.setAddressDetails(address);
                componentService.update(i);
                return finalList;
            }
        } catch (NotFound notFound) {

        } catch (FailedToAdd failedToAdd) {
            failedToAdd.printStackTrace();
        }
        return finalList;
    }

    @RequestMapping("/addCom.html")
    public String addComponent(@RequestParam String ip, @RequestParam String type, @RequestParam String address) {
        Random ran = new Random();
        ServiceTypes serviceTypes = ServiceTypes.valueOf(type);
        if (serviceTypes == serviceTypes.SWITCH_WEMO) {

            try {
                try {
                    componentService.add(ip, serviceTypes, ip, address, GenericType.LIGHT);
                } catch (AlreadyExists alreadyExists) {
                    ComponentsModel cm = componentService.getById(ip, address);
                    componentService.update(cm);

                } catch (FailedToAdd failedToAdd) {
                    failedToAdd.printStackTrace();
                }
            } catch (FailedToAdd failedToAdd) {
                logger.error(failedToAdd.getMessage());

            } catch (NotFound notFound) {
                logger.error(notFound.getMessage());
            }
        }
        /*        try {
            componentService.add(uniqueId, ServiceTypes.LIGHT_WEMO, ip, "");
        } catch (AlreadyExists alreadyExists) {
            alreadyExists.printStackTrace();
        } catch (FailedToAdd failedToAdd) {
            failedToAdd.printStackTrace();
        }*/
        return "component";
    }


}
package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.mvc.service.ComponentService;
import com.foulkes.lights.mvc.service.ManagedDeviceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by danfoulkes on 09/10/2015.
 */

@Controller
public class ManagedDeviceController {

    @Autowired
    private ComponentService componentService;
    @Autowired
    private ManagedDeviceService  managedDeviceService;


    private Logger logger = Logger.getLogger(ManagedDeviceController.class);

    /**
     * Display a list all all components that need assigning
     * @return
     */
    @RequestMapping(value = "/manage", method = {RequestMethod.GET})
    public ModelAndView displayAllUnassigned(){
        ModelAndView page = new ModelAndView("allComponents");
        page.addObject("components", componentService.getAll());
        page.addObject("devices", managedDeviceService.getAll());
        return page;
    }


    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public ModelAndView controlCentre(){
        ModelAndView page = new ModelAndView("controlCentre");
        return page;
    }

    /**
     * A
     * @param uniqueId
     * @return
     */
    @RequestMapping(value = "/assignComponent", method = {RequestMethod.GET})
    public String assignComponent(@RequestParam(value="id") String uniqueId){
        return null;
    }

}

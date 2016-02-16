package com.foulkes.homeautomation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by danfoulkes on 12/11/15.
 */

@Controller
public class HomeController {


    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public String dashboardPage() {
        return "dashboard";
    }

    @RequestMapping(value = "/manage", method = {RequestMethod.GET})
    public String managePage() {
        return "manage";}

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String addComPage() {
        return "add";}

    @RequestMapping(value = "/deviceDetails", method = {RequestMethod.GET})
    public String componentsDetails() {
        return "deviceDetails";
    }

    @RequestMapping(value = "/rooms", method = {RequestMethod.GET})
    public String roomDetials() {
        return "rooms";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.GET})
    public String updateetials() {
        return "update";
    }
    @RequestMapping(value = "/updateDevice", method = {RequestMethod.GET})
    public String updateDetialsCom() {
        return "updateDevice";
    }

}

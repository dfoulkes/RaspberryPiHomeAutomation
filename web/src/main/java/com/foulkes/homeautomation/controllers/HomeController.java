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
    public String homePage(ModelMap model) {
        return "home";
    }

    @RequestMapping(value = "/dashboard", method = {RequestMethod.GET})
    public String dashboardPage(ModelMap model) {
        return "dashboard";
    }

    @RequestMapping(value = "/manage", method = {RequestMethod.GET})
    public String managePage(ModelMap model) {
        return "manage";}

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String addComPage(ModelMap model) {
        return "add";}

    @RequestMapping(value = "/deviceDetails", method = {RequestMethod.GET})
    public String componentsDetails(ModelMap model) {
        return "deviceDetails";
    }

    @RequestMapping(value = "/rooms", method = {RequestMethod.GET})
    public String roomDetials(ModelMap model) {
        return "rooms";
    }

}

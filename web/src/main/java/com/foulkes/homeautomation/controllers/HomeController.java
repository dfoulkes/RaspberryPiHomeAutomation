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
}

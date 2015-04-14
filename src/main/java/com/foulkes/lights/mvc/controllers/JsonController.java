package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.factory.CommandFactory;
import com.foulkes.lights.mvc.json.Light;
import com.foulkes.lights.mvc.json.ServerStatus;
import com.foulkes.lights.mvc.service.GPIOService;
import com.foulkes.lights.mvc.setup.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class JsonController {


    @Autowired
    private AppConfig appConfig;

    @Autowired
    private GPIOService gpioService;

    //@Autowired
    //private GPIOService gpioService;

    private CommandFactory commandFactory = new CommandFactory();

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    /**
     * Turn On a Light switch
     * @param socket
     * @return
     */
    @RequestMapping(value = "/turnOn", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light turnOn(@PathVariable("socket") String socket) {
       // logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        Socket soc = Socket.find(socket);
        gpioService.executeCommand(soc,true);
        return dummy;
    }

    /**
     * Turn Off a Light switch
     * @param socket
     * @return
     */
    @RequestMapping(value = "/turnOff", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light turnOff(@PathVariable("socket") String socket) {
        // logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        Socket s = Socket.find(socket);

        gpioService.executeCommand(s, false);
        return dummy;
    }


    @RequestMapping(value = "/getStatus", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light getStatus(@PathVariable("socket") String socket) {
        // logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        return dummy;
    }


    @RequestMapping(value = "/getServerStatus", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    ServerStatus getServerDetails() {
        // logger.info("Start getEmployee. ID="+empId);
        ServerStatus dummy = new ServerStatus();
        dummy.setCreatedOn(new Date());
        dummy.setServerName(appConfig.getServerName());
        dummy.setDisplayName(appConfig.getDisplayName());
        dummy.setActive(true);
        return dummy;
    }

}
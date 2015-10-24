package com.foulkes.lights.mvc.controllers;

import com.foulkes.lights.common.enums.Socket;
import com.foulkes.lights.common.exception.StatusException;
import com.foulkes.lights.common.factory.CommandFactory;
import com.foulkes.lights.common.json.Light;
import com.foulkes.lights.common.json.ServerStatus;
import com.foulkes.lights.common.service.GPIOService;
import com.foulkes.lights.mvc.setup.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileNotFoundException;
import java.util.Date;

@Controller
public class JsonWebSerivce {

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private GPIOService gpioService;

    private CommandFactory commandFactory = new CommandFactory();


    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", appConfig.getServerName());
        return "hello";
    }

    @RequestMapping(value = "/turnOn", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light turnOn(@RequestParam(value="socket") String socket) {
        // logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        Socket soc = Socket.find(socket);
        dummy.setSocket(soc.name());
        gpioService.executeCommand(soc,true);
        dummy.setSocket(socket);
        dummy.setStatus(true);
        return dummy;
    }

    /**
     * Turn Off a Light switch
     * @param socket
     * @return
     */
    @RequestMapping(value = "/turnOff", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light turnOff(@RequestParam(value="socket") String socket) {
        // logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        Socket s = Socket.find(socket);
        gpioService.executeCommand(s, false);
        dummy.setSocket(socket);
        dummy.setStatus(false);
        return dummy;
    }


    @RequestMapping(value = "/getStatus", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody
    Light getStatus(@RequestParam(value="socket") String socket) {
        //logger.info("Start getEmployee. ID="+empId);
        Light dummy = new Light();
        try {
            Boolean status = gpioService.getLightStatus(Socket.find(socket));
            dummy.setSocket(socket);
            dummy.setStatus(status);
            dummy.setCreatedOn(new Date());
            dummy.setServerName(appConfig.getServerName());
        } catch (FileNotFoundException e) {

        } catch (StatusException e) {

        }
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
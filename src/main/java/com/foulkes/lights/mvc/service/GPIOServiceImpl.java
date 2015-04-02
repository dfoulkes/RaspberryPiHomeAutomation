package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.factory.CommandFactory;
import com.foulkes.lights.mvc.models.GpioPins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by dfoulkes on 30/03/2015.
 */
public class GPIOServiceImpl implements GPIOService {

    CommandFactory commandFactory = new CommandFactory();

    @Autowired
    PortService portSerivce;
    private Logger logger = LogManager.getLogger(GPIOServiceImpl.class);

    public GPIOServiceImpl(){
        try {
            portSerivce.setup();
        }catch(Exception e){
            logger.error("error in the setup of portService");
            logger.error(e.getMessage());
            e.getStackTrace();
        }
    }

    @Override
    public Boolean executeCommand(Socket socket, Boolean on) {

        GpioPins pins;

        if(on)
            pins= CommandFactory.getturnOnCommand(socket);
        else
            pins= CommandFactory.getturnOffCommand(socket);
        //TODO send pin settings to low lever implementing class

        exeuteCommand(pins);
        return true;
    }


    private void exeuteCommand(GpioPins pins){
        portSerivce.shutdownPorts();
        portSerivce.setup();
        portSerivce.setP11(pins.getD0().getStatus());
        portSerivce.setP15(pins.getD1().getStatus());
        portSerivce.setP16(pins.getD2().getStatus());
        portSerivce.setP13(pins.getD3().getStatus());
        try {
            Thread.sleep(100);
            portSerivce.setP22(true);
            Thread.sleep(250);
            //this might need removing to hold a light, this will only be known once tested.
            portSerivce.setP22(false);

        } catch (InterruptedException e) {
            logger.info("failed to wait for pin 22");
        }



    }

    private void resetGPIO(){

    }
}


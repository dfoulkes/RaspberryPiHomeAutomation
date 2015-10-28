package com.foulkes.lights.mvc.endpoints;

import com.foulkes.lights.mvc.setup.AppConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by danfoulkes on 24/10/2015.
 */
@Component
public class UpdateParent {

    Logger logger = Logger.getLogger(UpdateParent.class);

    @Autowired
    private AppConfig appConifg;


    public void call() {
        logger.info("Job was called");
    }
}
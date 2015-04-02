package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.factory.CommandFactory;
import com.foulkes.lights.mvc.models.GpioPins;
import org.springframework.stereotype.Service;

/**
 * Created by dfoulkes on 30/03/2015.
 */

@Service
public interface GPIOService {

    public Boolean executeCommand(Socket socket, Boolean status);

}


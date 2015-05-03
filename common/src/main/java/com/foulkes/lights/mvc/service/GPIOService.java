package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.factory.CommandFactory;
import com.foulkes.lights.mvc.models.GpioPins;
import com.foulkes.lights.mvc.service.exception.StatusException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * Created by dfoulkes on 30/03/2015.
 */

@Service
public interface GPIOService {

    public Boolean executeCommand(Socket socket, Boolean status);

    public Boolean getLightStatus(Socket soc) throws FileNotFoundException, StatusException;

}


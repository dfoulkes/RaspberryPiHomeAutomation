package com.foulkes.lights.common.service;

import com.foulkes.lights.common.enums.Socket;
import com.foulkes.lights.common.exception.StatusException;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * Created by dfoulkes on 30/03/2015.
 */

@Service
public interface GPIOService {

    Boolean executeCommand(Socket socket, Boolean status);

    Boolean getLightStatus(Socket soc) throws FileNotFoundException, StatusException;

}


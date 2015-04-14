package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.enums.Socket;
import com.foulkes.lights.mvc.service.exception.StatusException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.springframework.util.FileCopyUtils;

import java.io.*;

/**
 * Created by danfoulkes on 14/04/15.
 */
public class FileService implements GPIOService {

    File on;
    File off;
    File socket1;
    // Logger logger  = LogManager.getLogger(FileService.class);

    public FileService() {
        //load on and off files
        on = new File("/lights/outputFiles/on.txt");
        off = new File("/lights/outputFiles/off.txt");
        socket1 = new File("/lights/init.txt");
    }

    @Override
    public Boolean executeCommand(Socket socket, Boolean status) {
        switch (socket) {
            case SOC1:
                return sendToSocket1(status);
            default:
                return true;
        }
    }

    public Boolean sendToSocket1(Boolean state) {

        if (state) {
            try {
                FileCopyUtils.copy(on, socket1);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        } else {
            try {
                FileCopyUtils.copy(off, socket1);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public Boolean getLightStatus(Socket soc) throws StatusException {

        try {
            BufferedReader reader = new BufferedReader(new FileReader (socket1));
            String         line = null;
            StringBuilder  stringBuilder = new StringBuilder();
            String         ls = System.getProperty("line.separator");
            Boolean status;
            try {
                while( ( line = reader.readLine() ) != null ) {
                    stringBuilder.append(line);
                }
                String currentCommand = stringBuilder.toString();
                if(currentCommand.compareToIgnoreCase("on") == 0){
                    return true;
                }
                else if(currentCommand.compareToIgnoreCase("off") == 1){
                    return false;
                }
            } catch (Exception e) {
                return false;
            } finally {
                reader.close();
            }
        } catch (Exception e) {

            throw new StatusException();
        }
        throw new StatusException();
    }
}

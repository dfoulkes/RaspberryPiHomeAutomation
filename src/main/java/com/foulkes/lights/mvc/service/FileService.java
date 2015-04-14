package com.foulkes.lights.mvc.service;

import com.foulkes.lights.mvc.enums.Socket;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by danfoulkes on 14/04/15.
 */
public class FileService implements GPIOService {

    File on;
    File off;
    File socket1;


    public FileService(){
        //load on and off files
        on = new File("/lights/outputFiles/on.txt");
        off = new File("/lights/outputFiles/off.txt");
        socket1 = new File("/lights/init.txt");
    }

    @Override
    public Boolean executeCommand(Socket socket, Boolean status) {
        switch(socket) {
            case SOC1: return sendToSocket1(status);
            default: return true;
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
        }
        else{
            try {
                FileCopyUtils.copy(off, socket1);
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}

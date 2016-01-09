package com.foulkes.homeAutomation.service.wemo.core;


import com.foulkes.homeAutomation.service.wemo.execution.Command;
import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;
import com.foulkes.homeAutomation.service.wemo.execution.xml.GetInsightParamsResponse;
import com.foulkes.homeAutomation.service.wemo.execution.xml.SetBinaryStateResponse;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class CustomPosts {


    String IP;

    @Before
    public void setup(){
     IP = "192.168.1.112";
    }

    @Test
    public void runCommmandUsagge(){
        Command command = new Command(CommandEnum.USAGE);
        GetInsightParamsResponse response = (GetInsightParamsResponse)command.execute(IP);
        System.out.println(response.getInsightParams());
        System.out.println("State: "+response.getState());
        System.out.println("Last Changed In Secs: "+response.getLastchange());
        System.out.println("Length Of Time since Change in Secs: "+response.getLengthOfTimeOnInSecs());
        System.out.println("Length Of Time Today Secs: "+response.getLengthOfTimeOnToday());
        System.out.println("Length Of Time Total Secs: "+response.getLengthOfTimeOnTotal());
        System.out.println("Current Watts: "+response.getCurrentmw());
        System.out.println("Total Watts: "+response.getTodaymw());

    }


    @Test
    public void turnOnTest(){
        Command command = new Command(CommandEnum.BINARY_ON);
        SetBinaryStateResponse response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), true);
    }

    @Test
    public void turnOffTest(){
        Command command = new Command(CommandEnum.BINARY_OFF);
        SetBinaryStateResponse response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), false);
    }


    @Test
    public void ComplexOnFFTest(){
        Command command = new Command(CommandEnum.BINARY_ON);
        SetBinaryStateResponse response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), true);

        command = new Command(CommandEnum.BINARY_OFF);
        response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), false);

        command = new Command(CommandEnum.BINARY_ON);
        response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), true);

        command = new Command(CommandEnum.BINARY_ON);
        response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), true);

        command = new Command(CommandEnum.BINARY_OFF);
        response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), false);

        command = new Command(CommandEnum.BINARY_ON);
        response = (SetBinaryStateResponse)command.execute(IP);
        assertEquals(response.getState(), true);

    }

}

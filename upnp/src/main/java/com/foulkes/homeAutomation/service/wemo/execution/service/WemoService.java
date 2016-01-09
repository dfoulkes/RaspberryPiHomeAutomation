package com.foulkes.homeAutomation.service.wemo.execution.service;

import com.foulkes.homeAutomation.service.wemo.execution.Command;
import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;
import com.foulkes.homeAutomation.service.wemo.execution.xml.GetInsightParamsResponse;
import com.foulkes.homeAutomation.service.wemo.execution.xml.SetBinaryStateResponse;


/**
 * Created by danfoulkes on 04/01/2016.
 */
public class WemoService {


    public GetInsightParamsResponse getServiceInfo(final String IP){
        Command command = new Command(CommandEnum.USAGE);
        GetInsightParamsResponse response = (GetInsightParamsResponse)command.execute(IP);
        return response;
    }

    public SetBinaryStateResponse turnOn(final String IP){
        Command command = new Command(CommandEnum.BINARY_ON);
        SetBinaryStateResponse response = (SetBinaryStateResponse)command.execute(IP);
        return response;
    }

    public SetBinaryStateResponse turnOff(final String IP){
        Command command = new Command(CommandEnum.BINARY_OFF);
        SetBinaryStateResponse response = (SetBinaryStateResponse)command.execute(IP);
        return response;
    }
}

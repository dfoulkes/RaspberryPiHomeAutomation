package com.foulkes.homeAutomation.service.wemo.execution.xml;

import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class Response {

CommandEnum command;


    public Response(CommandEnum command){
        this.command = command;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public void setCommand(CommandEnum command) {
        this.command = command;
    }
}

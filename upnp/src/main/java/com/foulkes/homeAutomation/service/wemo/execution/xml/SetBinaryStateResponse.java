package com.foulkes.homeAutomation.service.wemo.execution.xml;

import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;

/**
 * Created by danfoulkes on 04/01/2016.
 */
public class SetBinaryStateResponse extends Response {



    Boolean state;

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public SetBinaryStateResponse() {
        super(CommandEnum.BINARY_ON);
    }


}

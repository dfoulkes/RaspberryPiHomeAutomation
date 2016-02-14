package com.foulkes.lights.mvc.routing.homeauto.annotation.light.response;


import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;

import java.io.Serializable;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public class ResponseLight extends Response implements Serializable{

    EventState eventState;

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    @Override
    public String getMessage() {
        return getEventState().toString();
    }
}

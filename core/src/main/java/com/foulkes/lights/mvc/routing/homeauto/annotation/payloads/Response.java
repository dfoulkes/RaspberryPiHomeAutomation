package com.foulkes.lights.mvc.routing.homeauto.annotation.payloads;

import com.foulkes.lights.common.enums.EventState;

import java.io.Serializable;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public class Response implements Serializable {

    EventState eventState;

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }
}

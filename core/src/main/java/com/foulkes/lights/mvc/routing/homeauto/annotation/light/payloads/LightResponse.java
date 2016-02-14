package com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;

/**
 * Created by danfoulkes on 06/02/2016.
 */
public class LightResponse extends Response {

    EventState eventState;

    public EventState getEventState() {
        return eventState;
    }

    public void setEventState(EventState eventState) {
        this.eventState = eventState;
    }

    @Override
    public String getMessage() {
        return eventState.toString();
    }
}

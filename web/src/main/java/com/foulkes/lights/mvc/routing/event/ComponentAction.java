package com.foulkes.lights.mvc.routing.event;

import com.foulkes.lights.common.routing.event.*;

/**
 * Created by danfoulkes on 15/10/2015.
 */
public class ComponentAction {

    private String componentId;
    private com.foulkes.lights.common.routing.event.EventState state;

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public com.foulkes.lights.common.routing.event.EventState getState() {
        return state;
    }

    public void setState(com.foulkes.lights.common.routing.event.EventState state) {
        this.state = state;
    }
}

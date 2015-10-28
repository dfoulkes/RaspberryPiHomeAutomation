package com.foulkes.lights.mvc.routing.event;


/**
 * Created by danfoulkes on 15/10/2015.
 */
public class ComponentAction {

    private String componentId;
    private EventState state;


    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }
}

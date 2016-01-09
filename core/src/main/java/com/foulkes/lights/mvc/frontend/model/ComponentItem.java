package com.foulkes.lights.mvc.frontend.model;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.enums.EventState;

/**
 * Created by danfoulkes on 30/10/15.
 */
public class ComponentItem {

    private String componentIcon;
    private EventState state;
    private String componentId;
    private ServiceTypes type;

    public String getComponentIcon() {
        return componentIcon;
    }

    public void setComponentIcon(String componentIcon) {
        this.componentIcon = componentIcon;
    }

    public EventState getState() {
        return state;
    }

    public void setState(EventState state) {
        this.state = state;
    }

    public String getComponentId() {
        return componentId;
    }

    public void setComponentId(String componentId) {
        this.componentId = componentId;
    }

    public ServiceTypes getType() {
        return type;
    }

    public void setType(ServiceTypes type) {
        this.type = type;
    }
}

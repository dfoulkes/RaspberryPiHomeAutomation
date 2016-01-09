package com.foulkes.lights.mvc.routing.factory;

import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.domain.Event;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.WebEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by danfoulkes on 09/01/2016.
 */

@Component
public class ProcessEvent {

    @Autowired
    private WebEvent order;


    public Response processComponentEvent(ComponentsModel com, EventState state){
        ComponentEvent message = new ComponentEvent(com.getIp(), state, com.getComponentType(),com.getGenericType(), com.getAddressDetails());
        Response  event = order.process(message);
        return event;
    }
}

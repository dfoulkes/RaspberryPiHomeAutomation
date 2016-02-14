package com.foulkes.lights.mvc.routing.factory;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.events.HueComponentEvent;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.WebEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by danfoulkes on 09/01/2016.
 */

@Component
public class ProcessEvent {

    @Autowired
    private WebEvent order;


    public Response processComponentEvent(ComponentsModel com, EventState state){

        ComponentEvent message;
        if(com.getComponentType().equals(ServiceTypes.LIGHT_HUE)){
            message = new HueComponentEvent(com.getIp(),state,com.getComponentType(), com.getGenericType(), com.getAddressDetails());
        }
        else{
            message = new ComponentEvent(com.getIp(), state, com.getComponentType(),com.getGenericType(), com.getAddressDetails());
        }

        Response  event = order.process(message);
        return event;
    }

    public Response processComponentEvent(ComponentEvent event){
        Response response = order.process(event);
        return response;
    }


}

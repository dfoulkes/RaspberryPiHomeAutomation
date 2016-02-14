package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.router;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.mvc.routing.factory.ProcessEvent;
import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.lights.LightHueTransformerTest;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.SwitchWemoTransformerTest;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.response.ResponseWemo;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by danfoulkes on 09/01/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/routing-context.xml")
public class WemoTest {


    @Autowired
    private ProcessEvent processEvent;
    @Autowired
    private SwitchWemoTransformerTest switchWemoTransformer;
    @Autowired
    private LightHueTransformerTest lightHueTransformerTest;

    private Response wemoResponse(EventState event){
        ResponseWemo responseWemo = new ResponseWemo();
        responseWemo.setEventState(event);
        return responseWemo;
    }

    @Test
    public void wemoTestOff() {
        switchWemoTransformer.setup(wemoResponse(EventState.OFF));
        Response result = processEvent.processComponentEvent(getWemo(), EventState.OFF);
        if(result instanceof ResponseWemo){
            ResponseWemo response = (ResponseWemo) result;
            assertEquals(response.getEventState(), EventState.OFF);
        }
        else{
            fail("incorrect response type received.");
        }

    }

    @Test
    public void wemoTestOn() {
        switchWemoTransformer.setup(wemoResponse(EventState.ON));
        final String IP ="FAKE";
        final EventState state = EventState.ON;
        final ServiceTypes type = ServiceTypes.SWITCH_WEMO;
        final String address = "";
        GenericType genericType = GenericType.SWITCH;
        ComponentEvent event = new ComponentEvent(IP, state, type, genericType, address);
        Response result = processEvent.processComponentEvent(getWemo(), EventState.ON);
        if(result instanceof ResponseWemo){
            ResponseWemo response = (ResponseWemo) result;
            assertEquals(response.getEventState(), EventState.ON);
        }
        else{
            fail("incorrect response type received.");
        }
    }

    private ComponentsModel getWemo(){
        ComponentsModel wemo = new ComponentsModel();
        wemo.setComponentType(ServiceTypes.SWITCH_WEMO);
        wemo.setGenericType(GenericType.SWITCH);
        wemo.setIp("192.168.1.100");
        wemo.setAddressDetails("");
        wemo.setAssigned(false);
        wemo.setUniquieId("FOULKES_WEMO");
        return wemo;
    }

    @Test
    public void homeBrewTest(){

    }


}
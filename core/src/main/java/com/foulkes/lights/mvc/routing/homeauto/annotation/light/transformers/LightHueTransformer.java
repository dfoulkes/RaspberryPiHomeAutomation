package com.foulkes.lights.mvc.routing.homeauto.annotation.light.transformers;

import com.foulkes.homeautomation.hue.controller.HueService_;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.events.ComponentEvent;

import com.foulkes.lights.common.events.HueComponentEvent;
import com.foulkes.lights.common.events.HueDO;
import com.foulkes.lights.common.exception.DeviceNotFound;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.service.ComponentService;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.payloads.HueLightResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 04/02/2016.
 */

@MessageEndpoint
public class LightHueTransformer implements LightTransformer_ {


    @Autowired
    private HueService_ hueService;
    @Autowired
    private ComponentService componentService;


    @Override
    @Transformer(inputChannel="light_hue", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException, DeviceNotFound {

        if(message.getPayload() instanceof HueComponentEvent){
          HueComponentEvent event = (HueComponentEvent)message.getPayload();

            HueDO  hue = hueService.getDevice(event.getDeviceId());

            if(event.getChangeLevel() && event.getChangeHue()){
                hue.setLightLevel(event.getLightLevel());
                hue.setHue(event.getHue());

            }
            if(event.getChangeHue()){
                hue.setHue(event.getHue());
                hue = hueService.updateDevice(hue);
            }
            //when the event is
             if(event.getChangeLevel()){
                hue.setLightLevel(event.getLightLevel());
                hue = hueService.updateDevice(hue);

            }

             if(event.getEventState().equals(EventState.STATUS)){
                //TODO do we need do anything in this state?
            }

            if(event.getEventState().equals(EventState.ON) || event.getEventState().equals(EventState.OFF)){
                hue.setOn((event.getEventState().equals(EventState.ON) ?true:false));
                hue = hueService.updateDevice(hue);
            }

            try {
                String bridgeId = hueService.getBridgeMacAddress();
                ComponentsModel com =  componentService.getById(bridgeId, event.getAddress());
                HueLightResponse response =  new HueLightResponse(hue, com);
                response.setEventState((hue.isOn()?EventState.ON:EventState.OFF));
                return response;

            } catch (NotFound notFound) {
                notFound.printStackTrace();
            }
        }
        throw new DeviceNotFound();
    }


    private EventState currentState(HueComponentEvent event) throws DeviceNotFound {
           HueDO hueDO =  hueService.getStatus(event);
            return (hueDO.isOn() ? EventState.ON : EventState.OFF);
    }
}

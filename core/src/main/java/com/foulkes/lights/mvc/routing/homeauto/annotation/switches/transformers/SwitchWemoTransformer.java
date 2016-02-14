package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.transformers;

import com.foulkes.homeAutomation.service.wemo.execution.Command;
import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;
import com.foulkes.homeAutomation.service.wemo.execution.xml.GetInsightParamsResponse;
import com.foulkes.homeAutomation.service.wemo.execution.xml.SetBinaryStateResponse;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.payloads.SwitchResponse;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.payloads.WemoResponse;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 09/01/2016.
 */
@MessageEndpoint
public class SwitchWemoTransformer implements SwitchTransformer_ {



    @Override
    @Transformer(inputChannel="switch_wemo_insight", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException {
        //TODO communicate with WEMO device
        EventState event = message.getPayload().getEventState();
        Command command;
        switch (event){
            case ON: command = new Command(CommandEnum.BINARY_ON); break;
            case OFF: command = new Command(CommandEnum.BINARY_OFF); break;
            default: command = new Command(CommandEnum.USAGE);
        }
        return generateResponse(command.execute(message.getPayload().getIp(), message.getPayload().getAddress()));
    }



    private Response generateResponse(com.foulkes.homeAutomation.service.wemo.execution.xml.Response wemo){

        if( wemo instanceof GetInsightParamsResponse){
            GetInsightParamsResponse insight  = (GetInsightParamsResponse)wemo;
            return new WemoResponse(insight.getInsightParams(), insight.getState(), insight.getLastchange(), insight.getLengthOfTimeOnInSecs(),
                    insight.getLengthOfTimeOnToday(), insight.getLengthOfTimeOnTotal(), insight.getTimeperiod(), insight.getUnknownValue(), insight.getCurrentmw(),
                    insight.getTodaymw(), insight.getTotalmw(), insight.getPowerthreshold());
        }
        else if(wemo instanceof SetBinaryStateResponse){
            SetBinaryStateResponse leaver = (SetBinaryStateResponse) wemo;
            SwitchResponse response = new SwitchResponse();
            response.setEventState((leaver.getState()? EventState.ON : EventState.OFF));
            return response;
        }

        return null;
    }


}

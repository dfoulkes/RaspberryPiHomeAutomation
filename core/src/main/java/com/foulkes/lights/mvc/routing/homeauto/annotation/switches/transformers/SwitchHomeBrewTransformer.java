/*
 * Copyright 2007-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.transformers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.json.Light;
import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.events.LightEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.response.ResponseLight;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * The endpoint for a light restful
 *
 * @author David Winterfeldt
 */
@MessageEndpoint
public class SwitchHomeBrewTransformer implements SwitchTransformer_ {

    final Logger logger = Logger.getLogger(SwitchTransformer_.class);

    @Autowired
    private Client client;
    /**
     * Process a light rest command and send the new state back to the response queue for updating
     * the web page.
     */

    @Override
    @Transformer(inputChannel="switch_homebrew", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException {
        ComponentEvent ce = message.getPayload();
        LightEvent order = new LightEvent(ce.getIp(),ce.getEventState(),ce.getComponentType(),ce.getGenericType(),ce.getAddress());
        logger.debug("starting light rest transformer");

        String extension;

        switch (order.getEventState()){
            case ON: extension = "turnOn"; break;
            case OFF: extension = "turnOff"; break;
            case STATUS: extension = "getStatus"; break;
            default: extension = "getStatus"; break;
        }


        logger.info("calling server:"+"http://"+order.getIp()+":8080/lightService/"+extension+"?socket="+order.getAddress());

        WebResource webResource;

        //added for debugging local
/*        if(appConfig.getServerName() == "DEV"){
            webResource = client
                    .resource("http://localhost:8080/lightService/"+extension+"?socket="+order.getAddress());

        }else {*/
        logger.info("calling client");
        webResource = client
                .resource("http://"+order.getIp()+":8080/lightService/"+extension+"?socket="+order.getAddress());

 /*       }*/
        logger.debug("call service");
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        logger.debug("response received");
        if(response.getStatus() != 200){
            logger.error("error return code was "+response.getStatus());
        }

        ObjectMapper mapper = new ObjectMapper();
        String JsonResponse = response.getEntity(String.class);

        //unmarshal json back into a light object
        Light lightResponse = mapper.readValue(JsonResponse, Light.class);
        EventState returnState = (lightResponse.getStatus() ? EventState.ON : EventState.OFF);
        //create the return message
        logger.info("returning new state: "+returnState.toString());
        ResponseLight rl = new ResponseLight();
        rl.setEventState(returnState);
        return rl;
    }


}

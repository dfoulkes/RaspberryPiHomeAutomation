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

package com.foulkes.lights.mvc.routing.homeauto.annotation.light;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foulkes.lights.common.json.Light;

import com.foulkes.lights.mvc.routing.event.EventState;
import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.LightEvent;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
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
public class LightRestTransformer {

    final Logger logger = Logger.getLogger(LightRestTransformer.class);

    /**
     * Process a light rest command and send the new state back to the response queue for updating
     * the web page.
     */

    @Transformer(inputChannel="post", outputChannel="processResponse")
	public ComponentEvent processMessage(Message<LightEvent> message) throws IOException {
		LightEvent order =  message.getPayload();

        String extention = (order.getEventState() == EventState.OFF?"turnOff" : "turnOn");
        Client client = Client.create();
        WebResource webResource = client
                .resource("http://"+order.getIp()+":8080/webservice/"+extention+"?socket="+order.getAddress());

        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);

        if(response.getStatus() != 200){
            logger.error("error return code was "+response.getStatus());
        }

        ObjectMapper mapper = new ObjectMapper();
        String JsonResponse = response.getEntity(String.class);

        //unmarshal json back into a light object
        Light lightResponse = mapper.readValue(JsonResponse, Light.class);
        EventState returnState = (lightResponse.getStatus() ? EventState.ON : EventState.OFF);
            //create the return message
        ComponentEvent rtn = new ComponentEvent(order.getIp(), returnState, order.getServiceTypes(), order.getAddress());
        return rtn;
	}

}

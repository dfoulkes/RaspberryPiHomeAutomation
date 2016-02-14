package com.foulkes.lights.mvc.routing.homeauto.annotation.switches;

import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.transformers.LightTransformer_;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 09/01/2016.
 */

@MessageEndpoint
public class SwitchWemoTransformerTest extends TestState implements LightTransformer_ {


    @Override
    @Transformer(inputChannel="switch_wemo_insight", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException {
        return super.getResponse();
    }
}
package com.foulkes.lights.mvc.routing.homeauto.annotation.lights;

import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.light.transformers.LightTransformer_;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import com.foulkes.lights.mvc.routing.homeauto.annotation.switches.TestState;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 06/02/2016.
 */
@MessageEndpoint
public class LightHueTransformerTest extends TestState implements LightTransformer_ {


    @Override
    @Transformer(inputChannel="light_hue", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException {
        return getResponse();
    }
}

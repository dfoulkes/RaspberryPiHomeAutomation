package com.foulkes.lights.mvc.routing.homeauto.annotation.light;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.LightEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public class LightHomeBrewTransformerTest extends TestState implements LightHomeBrewTransformer_ {

    @Override
    @Transformer(inputChannel="light_homebrew", outputChannel="processResponse")
    public Response processHomeBrew(Message<ComponentEvent> message) throws IOException {
        return getResponse();
    }

}
package com.foulkes.lights.mvc.routing.homeauto.annotation.light;

import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.LightEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.integration.annotation.Transformer;
import org.springframework.messaging.Message;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public class LightWemoTransformerTest extends TestState implements LightWemoTransformer_ {


    @Override
    @Transformer(inputChannel="light_wemo_insight", outputChannel="processResponse")
    public Response processMessage(Message<ComponentEvent> message) throws IOException {
        return super.getResponse();
    }
}
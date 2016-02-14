package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.transformers;

import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 06/02/2016.
 */
public interface SwitchTransformer_ {

    Response processMessage(Message<ComponentEvent> message) throws IOException;
}

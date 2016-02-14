package com.foulkes.lights.mvc.routing.homeauto.annotation.light.transformers;

import com.foulkes.lights.common.events.ComponentEvent;
import com.foulkes.lights.common.exception.DeviceNotFound;
import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;
import org.springframework.messaging.Message;

import java.io.IOException;

/**
 * Created by danfoulkes on 04/02/2016.
 */
public interface LightTransformer_ {

    Response processMessage(Message<ComponentEvent> message) throws IOException, DeviceNotFound;
}

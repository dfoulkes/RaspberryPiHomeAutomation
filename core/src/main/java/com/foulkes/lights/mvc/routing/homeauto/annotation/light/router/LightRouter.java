package com.foulkes.lights.mvc.routing.homeauto.annotation.light.router;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.events.ComponentEvent;
import org.apache.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Created by danfoulkes on 06/02/2016.
 */
@MessageEndpoint
public class LightRouter {


    final Logger logger = Logger.getLogger(LightRouter.class);

    /**
     * Transforms a <code>ComponentEvent</code> into a <code>LightEvent</code>
     */
    @Router(inputChannel="light")
    public String processMessage(ComponentEvent order) {
        logger.info("light transfomer");
        if(order.getComponentType().equals(ServiceTypes.LIGHT_HUE)){
            return "light_hue";
        }
        else{
            return "error_channel";
        }
    }
}

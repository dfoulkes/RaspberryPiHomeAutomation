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

package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.router;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.events.ComponentEvent;
import org.apache.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Transforms a componentEvent into a light event
 * that contains an address.
 * 
 * @author Dan Foulkes
 */
@MessageEndpoint
public class SwitchRouter {


    final Logger logger = Logger.getLogger(SwitchRouter.class);
    
    /**
     * Transforms a <code>ComponentEvent</code> into a <code>LightEvent</code>
     */
    @Router(inputChannel="switch")
	public String processMessage(ComponentEvent order) {
        logger.info("Switch transfomer");
        if(order.getComponentType().equals(ServiceTypes.SWITCH_WEMO)){
            return "switch_wemo_insight";
        }
        else if(order.getComponentType().equals(ServiceTypes.SWITCH_HOMEBREW)){
            return "switch_homebrew";
        }
        else{
            return "error_channel";
        }
	}

}

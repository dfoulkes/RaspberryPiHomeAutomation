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

import com.foulkes.lights.common.events.ComponentEvent;

import org.apache.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Router;

/**
 * Routes order based on order type.
 * 
 * @author David Winterfeldt
 */
@MessageEndpoint
public class ComponentRouter{

    final Logger logger = Logger.getLogger(ComponentRouter.class);

    /**
     * Process order.  Routes based on whether or 
     * not the order is a delivery or pickup.
     */
	@Router(inputChannel="processOrder")
	public String processOrder(ComponentEvent order) {
	    String result = null;
	    
	    logger.info("processing route");
	    
	    switch (order.getGenericType()) {
	        case SWITCH:
	            result = "switch";
	            break;
			case LIGHT:
                result = "light";
	    }
		return result;
	}

}

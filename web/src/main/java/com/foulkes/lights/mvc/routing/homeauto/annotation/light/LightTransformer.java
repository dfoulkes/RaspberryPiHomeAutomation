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

import com.foulkes.lights.mvc.routing.homeauto.ComponentEvent;
import com.foulkes.lights.mvc.routing.homeauto.LightEvent;
import com.foulkes.lights.mvc.service.ComponentService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.Transformer;

/**
 * Transforms a componentEvent into a light event
 * that contains an address.
 * 
 * @author Dan Foulkes
 */
@MessageEndpoint
public class LightTransformer {

    @Autowired
    private ComponentService componentService;

    final Logger logger = Logger.getLogger(LightTransformer.class);
    
    /**
     * Transforms a <code>ComponentEvent</code> into a <code>LightEvent</code>
     */
    @Transformer(inputChannel="delivery", outputChannel="post")
	public LightEvent processMessage(ComponentEvent order) {
        logger.debug("In LightTransformer: "+
                new Object[] { order.getEventState(),
                               order.getIp(),
                               order.getServiceTypes() });

        return new LightEvent(order.getIp(), order.getEventState(),
                order.getServiceTypes(),
                "SOC1");
	}

}

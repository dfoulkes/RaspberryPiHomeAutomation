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

package com.foulkes.lights.mvc.routing.homeauto;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.enums.EventState;
import com.foulkes.lights.common.enums.GenericType;

/**
 * Book order information.
 *
 * @author David Winterfeldt
 */
public class ComponentEvent {


    private final String ip;
    private final EventState eventState;
    private final ServiceTypes componentType;
    private final GenericType genericType;
    private final String address;

    public GenericType getGenericType() {
        return genericType;
    }

    /**
     * Constructor
     */



    public ComponentEvent(String ip, EventState eventState, ServiceTypes serviceTypes, GenericType genericType, String address) {
        this.ip = ip;
        this.eventState = eventState;
        this.componentType = serviceTypes;
        this.genericType = genericType;
        this.address = address;

    }

    public ServiceTypes getComponentType() {
        return componentType;
    }

    public String getAddress() {
        return address;
    }

    /**
     * Gets title.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Gets quantity.
     */
    public EventState getEventState() {
        return eventState;
    }

    /**
     * Gets order type.
     */
    public ServiceTypes getServiceTypes() {
        return componentType;
    }


}

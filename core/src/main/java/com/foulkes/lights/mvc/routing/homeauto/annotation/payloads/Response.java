package com.foulkes.lights.mvc.routing.homeauto.annotation.payloads;

import com.foulkes.lights.common.enums.EventState;

import java.io.Serializable;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public abstract class Response implements Serializable {


    public abstract String getMessage();
}

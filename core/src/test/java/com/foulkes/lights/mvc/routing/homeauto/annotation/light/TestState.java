package com.foulkes.lights.mvc.routing.homeauto.annotation.light;

import com.foulkes.lights.mvc.routing.homeauto.annotation.payloads.Response;

/**
 * Created by danfoulkes on 09/01/2016.
 */
public class TestState {


    Response response;


    public void setup(Response response){
        this.response = response;
    }


    public Response getResponse() {
        return response;
    }
}

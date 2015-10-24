package com.foulkes.lights.common.scheduler;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class Lights implements ComponentInterface, Serializable {

    private Long jobId;
    private String room;
    private ServiceTypes description;
    private Calendar lastRan;
    private Date startTime;
    private Date endTime;


    public Lights(Long jobId, String room, ServiceTypes description, Calendar lastRan, Date startTime, Date endTime){
        this.jobId = jobId;
        this.room = room;
        this.description = description;
        this.lastRan = lastRan;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    @Override
    public String getRoom() {
        return room;
    }

    @Override
    public ServiceTypes getDescription() {
        return description;
    }


    @Override
    public void start() {
        //call web service to turn on light
    }

    @Override
    public void stop() {

    }


}

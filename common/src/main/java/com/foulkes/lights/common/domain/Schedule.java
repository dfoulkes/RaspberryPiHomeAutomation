package com.foulkes.lights.common.domain;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;

import javax.persistence.*;
import java.util.Calendar;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long jobId;
    private ServiceTypes serviceType;
    private String room;
    private ComponentInterface classImpl;
    private String startTime;
    private String endTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastRun;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public ServiceTypes getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceTypes serviceType) {
        this.serviceType = serviceType;
    }

    public Calendar getLastRun() {
        return lastRun;
    }

    public void setLastRun(Calendar lastRun) {
        this.lastRun = lastRun;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public ComponentInterface getClassImpl() {
        return classImpl;
    }

    public void setClassImpl(ComponentInterface classImpl) {
        this.classImpl = classImpl;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

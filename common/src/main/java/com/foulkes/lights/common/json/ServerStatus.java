package com.foulkes.lights.common.json;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by dfoulkes on 30/03/2015.
 */


public class ServerStatus implements Serializable {


    private static final long serialVersionUID = -5011243366131339994L;
    private String serverName;
    private String displayName;
    private List<Light> node;
    private Boolean active;
    private Date CreatedOn;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public List<Light> getNode() {
        return node;
    }

    public void setNode(List<Light> node) {
        this.node = node;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @JsonSerialize(using=DateSerializer.class)
    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}

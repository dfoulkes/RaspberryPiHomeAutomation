package com.foulkes.lights.common.model;

import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.enums.ServiceTypes;

import java.io.Serializable;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class ComponentsModel implements Serializable{
    private static final long serialVersionUID = 5464166116243968076L;
    private String uniquieId;
    private ServiceTypes componentType;
    private Boolean assigned;
    private String addressDetails;
    private String ip;

    public ComponentsModel(){

    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUniquieId(String uniquieId) {
        this.uniquieId = uniquieId;
    }

    public void setComponentType(ServiceTypes componentType) {
        this.componentType = componentType;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public String getUniquieId() {
        return uniquieId;
    }

    public ServiceTypes getComponentType() {
        return componentType;
    }

    public static ComponentsModel build(Components model){
        ComponentsModel c = new ComponentsModel();
        c.setAssigned(model.getAssigned());
        c.setUniquieId(model.getUniquieId());
        c.setComponentType(model.getComponentType());
        c.setIp(model.getIp());
        c.setAddressDetails(model.getAddressDetails());
        return c;
    }


}

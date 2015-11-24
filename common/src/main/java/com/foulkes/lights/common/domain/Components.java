package com.foulkes.lights.common.domain;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.model.ComponentsModel;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by danfoulkes on 03/10/15.
 */

@Entity
@Table(name="Components")
@NamedQueries(value = {
        @NamedQuery(name = "Components.getAll", query = "SELECT components FROM Components components"),
        @NamedQuery(name = "Components.getById", query = "SELECT components FROM Components components WHERE components.uniquieId = :id AND components.addressDetails =  :address"),
})
public class Components {

    @Id
    private String uniquieId;
    private ServiceTypes componentType;
    private String ip;
    private Boolean assigned;
    @Id
    private String addressDetails;

    public Components(String uniqueId, ServiceTypes componentType, String ip){
        this.uniquieId = uniqueId;
        this.componentType = componentType;
        this.ip = ip;
    }

    public Components(){

    }

    public String getAddressDetails() {
        return addressDetails;
    }

    public void setAddressDetails(String addressDetails) {
        this.addressDetails = addressDetails;
    }

    public void setUniquieId(String uniquieId) {
        this.uniquieId = uniquieId;
    }

    public void setComponentType(ServiceTypes componentType) {
        this.componentType = componentType;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUniquieId() {
        return uniquieId;
    }

    public ServiceTypes getComponentType() {
        return componentType;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public static Components build(ComponentsModel components) {
        Components cm = new Components();
        cm.setUniquieId(components.getUniquieId());
        cm.setComponentType(components.getComponentType());
        cm.setIp(components.getIp());
        cm.setAssigned(components.getAssigned());
        cm.setAddressDetails(components.getAddressDetails());
        return cm;
    }

    public static List<Components> builds(Set<ComponentsModel> componentsModel) {
        ArrayList<Components> componentsList = new ArrayList<>();

        Iterator<ComponentsModel> it = componentsModel.iterator();

        while(it.hasNext()){
            ComponentsModel next = it.next();
            componentsList.add(Components.build(next));
        }
        return componentsList;
    }
}

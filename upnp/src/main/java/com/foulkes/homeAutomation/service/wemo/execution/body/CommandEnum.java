package com.foulkes.homeAutomation.service.wemo.execution.body;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public enum CommandEnum {

    USAGE("urn:Belkin:service:insight:1","\"urn:Belkin:service:insight:1#GetInsightParams\"","<?xml version=\"1.0\"?><s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:GetInsightParams xmlns:u=\"urn:Belkin:service:insight:1\"></u:GetInsightParams></s:Body></s:Envelope>", "upnp/control/insight1"),
    BINARY_ON("urn:Belkin:service:basicevent:1","\"urn:Belkin:service:basicevent:1#SetBinaryState\"","<?xml version=\"1.0\"?><s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:SetBinaryState xmlns:u=\"urn:Belkin:service:basicevent:1\"><BinaryState>1</BinaryState></u:SetBinaryState></s:Body></s:Envelope>", "upnp/control/basicevent1"),
    BINARY_OFF("urn:Belkin:service:basicevent:1","\"urn:Belkin:service:basicevent:1#SetBinaryState\"","<?xml version=\"1.0\"?><s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\" s:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><s:Body><u:SetBinaryState xmlns:u=\"urn:Belkin:service:basicevent:1\"><BinaryState>0</BinaryState></u:SetBinaryState></s:Body></s:Envelope>", "upnp/control/basicevent1");

    String urn;
    String soapAction;
    String payload;
    String action;
    String control;

    private CommandEnum(String urn, String soapAction, String payload, String control){
        this.payload = payload;
        this.soapAction = soapAction;
        this.urn = urn;
        this.control = control;
    }

    public String getControl() {
        return control;
    }

    public String getUrn() {
        return urn;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public String getPayload() {
        return payload;
    }

    public String getAction() {
        return action;
    }
}

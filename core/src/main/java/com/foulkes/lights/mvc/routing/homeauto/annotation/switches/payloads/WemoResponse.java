package com.foulkes.lights.mvc.routing.homeauto.annotation.switches.payloads;

import com.foulkes.lights.common.enums.EventState;

import java.math.BigDecimal;

/**
 * Created by danfoulkes on 10/01/2016.
 */
public class WemoResponse extends SwitchResponse {

    String InsightParams;

    Long lastchange;
    Long lengthOfTimeOnInSecs;
    Long lengthOfTimeOnToday;
    Long lengthOfTimeOnTotal;
    Long timeperiod;
    Long unknownValue;
    BigDecimal currentmw;
    BigDecimal todaymw;
    BigDecimal totalmw;
    BigDecimal powerthreshold;

    public WemoResponse(String insightParams, Boolean state, Long lastchange, Long lengthOfTimeOnInSecs, Long lengthOfTimeOnToday, Long lengthOfTimeOnTotal, Long timeperiod, Long unknownValue, BigDecimal currentmw, BigDecimal todaymw, BigDecimal totalmw, BigDecimal powerthreshold) {
        InsightParams = insightParams;

        if(state){
            setEventState(EventState.ON);
        }
        else{
            setEventState(EventState.OFF);
        }
        this.lastchange = lastchange;
        this.lengthOfTimeOnInSecs = lengthOfTimeOnInSecs;
        this.lengthOfTimeOnToday = lengthOfTimeOnToday;
        this.lengthOfTimeOnTotal = lengthOfTimeOnTotal;
        this.timeperiod = timeperiod;
        this.unknownValue = unknownValue;
        this.currentmw = currentmw;
        this.todaymw = todaymw;
        this.totalmw = totalmw;
        this.powerthreshold = powerthreshold;
    }

    public WemoResponse(Boolean state) {
        if(state){
            setEventState(EventState.ON);
        }
        else{
            setEventState(EventState.OFF);
        }
    }

    public String getInsightParams() {
        return InsightParams;
    }

    public void setInsightParams(String insightParams) {
        InsightParams = insightParams;
    }


    public Long getLastchange() {
        return lastchange;
    }

    public void setLastchange(Long lastchange) {
        this.lastchange = lastchange;
    }

    public Long getLengthOfTimeOnInSecs() {
        return lengthOfTimeOnInSecs;
    }

    public void setLengthOfTimeOnInSecs(Long lengthOfTimeOnInSecs) {
        this.lengthOfTimeOnInSecs = lengthOfTimeOnInSecs;
    }

    public Long getLengthOfTimeOnToday() {
        return lengthOfTimeOnToday;
    }

    public void setLengthOfTimeOnToday(Long lengthOfTimeOnToday) {
        this.lengthOfTimeOnToday = lengthOfTimeOnToday;
    }

    public Long getLengthOfTimeOnTotal() {
        return lengthOfTimeOnTotal;
    }

    public void setLengthOfTimeOnTotal(Long lengthOfTimeOnTotal) {
        this.lengthOfTimeOnTotal = lengthOfTimeOnTotal;
    }

    public Long getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(Long timeperiod) {
        this.timeperiod = timeperiod;
    }

    public Long getUnknownValue() {
        return unknownValue;
    }

    public void setUnknownValue(Long unknownValue) {
        this.unknownValue = unknownValue;
    }

    public BigDecimal getCurrentmw() {
        return currentmw;
    }

    public void setCurrentmw(BigDecimal currentmw) {
        this.currentmw = currentmw;
    }

    public BigDecimal getTodaymw() {
        return todaymw;
    }

    public void setTodaymw(BigDecimal todaymw) {
        this.todaymw = todaymw;
    }

    public BigDecimal getTotalmw() {
        return totalmw;
    }

    public void setTotalmw(BigDecimal totalmw) {
        this.totalmw = totalmw;
    }

    public BigDecimal getPowerthreshold() {
        return powerthreshold;
    }

    public void setPowerthreshold(BigDecimal powerthreshold) {
        this.powerthreshold = powerthreshold;
    }
}

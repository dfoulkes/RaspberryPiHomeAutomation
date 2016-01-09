package com.foulkes.homeAutomation.service.wemo.execution.xml;

import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class GetInsightParamsResponse extends Response implements Serializable {

    String InsightParams;

    Boolean state;
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


    public  GetInsightParamsResponse(){
        super(CommandEnum.USAGE);
    }

    public String getInsightParams() {
        return InsightParams;
    }

    public Long getTimeperiod() {
        return timeperiod;
    }

    public void setTimeperiod(Long timeperiod) {
        this.timeperiod = timeperiod;
    }

    public void setInsightParams(String insightParams) {
        InsightParams = insightParams;
        parse();
    }

    private void parse(){
        String[] values = getInsightParams().split("\\|");
        for(int i=0; i <values.length; i++){
            switch(i){
                case 0: state = (new Integer(values[i]) == 0 ? false : true); break;
                case 1: lastchange = new Long(values[i]); break;
                case 2: lengthOfTimeOnInSecs = new Long(values[i]); break;
                case 3: lengthOfTimeOnToday = new Long(values[i]); break;
                case 4: lengthOfTimeOnTotal = new Long(values[i]); break;
                case 5: timeperiod  = new Long(values[i]); break;
                case 6: unknownValue = new Long(values[i]); break;
                case 7: currentmw = new BigDecimal(values[i]); break;
                case 8: todaymw = new BigDecimal(values[i]); break;
                case 9: totalmw = new BigDecimal(values[i]); break;
                case 10: powerthreshold = new BigDecimal(values[i]); break;
            }
        }
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
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

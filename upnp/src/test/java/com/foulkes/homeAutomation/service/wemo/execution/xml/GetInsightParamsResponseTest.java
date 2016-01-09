package com.foulkes.homeAutomation.service.wemo.execution.xml;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class GetInsightParamsResponseTest {

    GetInsightParamsResponse getInsightParamsResponse;



    @Before
    public void setup(){
        getInsightParamsResponse = new GetInsightParamsResponse();
    }

    @Test
    public void testSetInsightParams() throws Exception {
        getInsightParamsResponse.setInsightParams("0|1451838593|0|982|26427|286727|36|0|593935|16225516.000000|8000");
        assertEquals(getInsightParamsResponse.getState(),false);
        assertEquals(getInsightParamsResponse.getLastchange(), new Long("1451838593"));
        assertEquals(getInsightParamsResponse.getLengthOfTimeOnInSecs(), new Long(0));
        assertEquals(getInsightParamsResponse.getLengthOfTimeOnToday(), new Long(982));
        assertEquals(getInsightParamsResponse.getLengthOfTimeOnTotal(), new Long(26427));
        assertEquals(getInsightParamsResponse.getTimeperiod(), new Long(286727));
        assertEquals(getInsightParamsResponse.getUnknownValue(), new Long(36));
        assertEquals(getInsightParamsResponse.getCurrentmw(), new BigDecimal(0));
        assertEquals(getInsightParamsResponse.getTodaymw(), new BigDecimal(593935));
        assertEquals(getInsightParamsResponse.getTotalmw(), new BigDecimal(16225516).setScale(6));

    }
}
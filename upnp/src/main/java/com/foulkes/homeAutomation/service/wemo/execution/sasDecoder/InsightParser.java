package com.foulkes.homeAutomation.service.wemo.execution.sasDecoder;

import com.foulkes.homeAutomation.service.wemo.execution.xml.Response;
import com.foulkes.homeAutomation.service.wemo.execution.xml.GetInsightParamsResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import java.io.IOException;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class InsightParser extends SasDecoder {


    GetInsightParamsResponse getInsightParamsResponseTmp;


    public InsightParser(String xml) throws IOException {
        super(xml);
        parseDocument();
        getXml().delete();
    }


    @Override
    public Response printDatas() {
        return getInsightParamsResponseTmp;
    }


    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        if (elementName.equalsIgnoreCase("GetInsightParamsResponse")) {
            getInsightParamsResponseTmp = new GetInsightParamsResponse();
        }
    }

    @Override
    public void endElement (String s, String s1, String element)throws SAXException {
        if (element.equalsIgnoreCase("InsightParams")) {
            if(getInsightParamsResponseTmp == null)
                getInsightParamsResponseTmp = new GetInsightParamsResponse();

            getInsightParamsResponseTmp.setInsightParams(tmpValue);
            }
        }


    }





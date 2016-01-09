package com.foulkes.homeAutomation.service.wemo.execution.sasDecoder;

import com.foulkes.homeAutomation.service.wemo.execution.xml.GetInsightParamsResponse;
import com.foulkes.homeAutomation.service.wemo.execution.xml.Response;
import com.foulkes.homeAutomation.service.wemo.execution.xml.SetBinaryStateResponse;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import java.io.IOException;

/**
 * Created by danfoulkes on 04/01/2016.
 */
public class BinaryStateParser extends SasDecoder {

    SetBinaryStateResponse setBinaryStateResponse;


    public BinaryStateParser(String xml) throws IOException {
        super(xml);
        parseDocument();
        getXml().delete();
    }


    @Override
    public Response printDatas() {
        return setBinaryStateResponse;
    }


    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        if (elementName.equalsIgnoreCase("SetBinaryStateResponse")) {
            setBinaryStateResponse = new SetBinaryStateResponse();
        }
    }

    @Override
    public void endElement (String s, String s1, String element)throws SAXException {
        if (element.equalsIgnoreCase("BinaryState")) {
            if(setBinaryStateResponse == null)
                setBinaryStateResponse = new SetBinaryStateResponse();

            String stateStr = tmpValue.split("\\|")[0];
            Boolean state;
            if(new Integer(stateStr) == 8){
                state = true;
            }
            else{
                state = false;
            }
            setBinaryStateResponse.setState(state);
        }
    }
}

package com.foulkes.homeAutomation.service.wemo.execution.sasDecoder;

import com.foulkes.homeAutomation.service.wemo.execution.xml.Response;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import org.apache.commons.io.FileUtils;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public abstract class SasDecoder extends DefaultHandler {

    File xml;
    String tmpValue;

    public SasDecoder(String xml) throws IOException {
        File xmlFile = new File(Long.toString(Calendar.getInstance().getTimeInMillis()));
        FileUtils.writeStringToFile(xmlFile, xml);
        this.xml = xmlFile;
    }

    public File getXml() {
        return xml;
    }

    public void setXml(File xml) {
        this.xml = xml;
    }

    public void parseDocument() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            parser.parse(getXml(), this);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract Response printDatas();

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }

}

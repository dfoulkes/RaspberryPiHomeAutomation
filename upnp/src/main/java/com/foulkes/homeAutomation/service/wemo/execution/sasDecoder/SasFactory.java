package com.foulkes.homeAutomation.service.wemo.execution.sasDecoder;

import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;

import java.io.IOException;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class SasFactory {


    public static SasDecoder getParser(CommandEnum command, String xml) throws IOException {
        switch (command){
            case USAGE: return new InsightParser(xml);
            case BINARY_ON: return new BinaryStateParser(xml);
            case BINARY_OFF: return new BinaryStateParser(xml);
        }
        return null;
    }
}

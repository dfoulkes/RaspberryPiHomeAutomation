package com.foulkes.homeAutomation.service.wemo.execution;

import com.foulkes.homeAutomation.service.wemo.execution.body.CommandEnum;
import com.foulkes.homeAutomation.service.wemo.execution.xml.Response;
import com.foulkes.homeAutomation.service.wemo.execution.sasDecoder.SasDecoder;
import com.foulkes.homeAutomation.service.wemo.execution.sasDecoder.SasFactory;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * Created by danfoulkes on 03/01/2016.
 */
public class Command {


    CommandEnum commandValues;



    public Command(CommandEnum commandValues){
       this.commandValues = commandValues;
    }

    public Response execute(final String IP){
        String soapXml =  commandValues.getPayload();
        System.out.println(soapXml);
        java.net.URL url = null;
        try {
            url = new java.net.URL("http://"+IP+":49153/"+commandValues.getControl());
            java.net.URLConnection conn = url.openConnection();
// Set the necessary header fields

            conn.setRequestProperty("CONTENT-TYPE","text/xml; charset=\"utf-8\"");
            conn.setRequestProperty("SOAPACTION", commandValues.getSoapAction());
            conn.setRequestProperty("Accept","text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2");
            conn.setDoOutput(true);
// Send the request
            java.io.OutputStreamWriter wr = new java.io.OutputStreamWriter(conn.getOutputStream());
            wr.write(soapXml);
            wr.flush();
// Read the response
            java.io.BufferedReader rd = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream()));
            String line;
            String output = "";
            while ((line = rd.readLine()) != null) {
                output += line;
            }
            System.out.println(output);
            SasDecoder decoder = SasFactory.getParser(commandValues, output);
            return decoder.printDatas();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}

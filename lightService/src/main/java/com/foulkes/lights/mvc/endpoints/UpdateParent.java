package com.foulkes.lights.mvc.endpoints;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.RegistrationStatus;
import com.foulkes.lights.common.enums.ServiceTypes;

import com.foulkes.lights.mvc.setup.AppConfig;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 * Created by danfoulkes on 24/10/2015.
 */
public class UpdateParent{

    Logger logger = Logger.getLogger(UpdateParent.class);

    @Autowired
    private AppConfig appConifg;


    //find active ip address
    private String currentIp(){
        String ip = "";
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface iface = interfaces.nextElement();
                // filters out 127.0.0.1 and inactive interfaces
                if (iface.isLoopback() || !iface.isUp())
                    continue;

                Enumeration<InetAddress> addresses = iface.getInetAddresses();
                while(addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    ip = addr.getHostAddress();
                    System.out.println(iface.getDisplayName() + " " + ip);
                }
            }
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        return ip;
    }

    public void execute() throws UnknownHostException {

        logger.info("updating server");

        String currentIp = currentIp();

        Client client = Client.create();

        //TODO call twice
        int counter = 1;
        while(counter < 3) {

            String soc= "";

            if(counter == 1){
                soc = "1";
            }
            else if(counter == 2)
            {
                soc = "2";
            }

            String url = "http://" + appConifg.getServerUrl() + ":" + appConifg.getServerPort() + "/" + appConifg.getProjectName() + "/register?componentId=" + appConifg.getComponentId() + "&type=" + ServiceTypes.SWITCH_HOMEBREW + "&ip=" + currentIp + "&address="+soc+"&genericType="+ GenericType.LIGHT;

            logger.info("calling: " + url);
            WebResource webResource = client
                    .resource(url);

            ClientResponse response = webResource.accept("application/json")
                    .get(ClientResponse.class);

            if (response.getStatus() != 200) {
                logger.error("error return code was " + response.getStatus());
            }

            ObjectMapper mapper = new ObjectMapper();
            String JsonResponse = response.getEntity(String.class);

            //unmarshal json back into a light object
            try {
                RegistrationStatus lightResponse = mapper.readValue(JsonResponse, RegistrationStatus.class);
            } catch (IOException e) {
                logger.error("error parsing response:");
                logger.error(e.getMessage());
            }
            counter++;
        }
    }
}

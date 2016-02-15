package com.foulkes.homeautomation.ssdp.discover;

import com.foulkes.homeautomation.ssdp.controllers.SSDPHandlerControler;
import com.jerabi.ssdp.handler.SSDPDefaultResponseHandler;
import com.jerabi.ssdp.listener.SSDPMulticastListener;
import com.jerabi.ssdp.util.SSDPContants;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;

/**
 * Created by danfoulkes on 01/02/2016.
 */
public class DiscoveryPhaseTest {



    @Test
    public void handleResponsesTest() throws Exception {
        List<NetworkInterface> networkInterfaceList = new ArrayList<NetworkInterface>();
        networkInterfaceList.add(NetworkInterface.getByName("en0"));
        SSDPMulticastListener listener = new SSDPMulticastListener(new InetSocketAddress(SSDPContants.DEFAULT_IP, SSDPContants.DEFAULT_PORT), networkInterfaceList);
        SSDPHandlerControler controler = new SSDPHandlerControler();
      //  listener.setSSDPResponseHandler(new SSDPDefaultResponseHandler(controler));
        listener.setSSDPResponseHandler(new SSDPDefaultResponseHandler(controler));
        listener.setBlocking(false);
        new Thread(listener).start();
        while(true){
            Thread.sleep(1000);
        }

    }

}

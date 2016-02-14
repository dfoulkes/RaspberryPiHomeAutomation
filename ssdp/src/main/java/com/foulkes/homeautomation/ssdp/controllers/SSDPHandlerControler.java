package com.foulkes.homeautomation.ssdp.controllers;

import com.jerabi.ssdp.ISSDPControler;
import com.jerabi.ssdp.SSDPControler;
import com.jerabi.ssdp.handler.ISSDPResponseHandler;
import com.jerabi.ssdp.message.AliveMessage;
import com.jerabi.ssdp.message.ISSDPMessage;

import java.net.NetworkInterface;
import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * Created by danfoulkes on 01/02/2016.
 */
public class SSDPHandlerControler extends SSDPControler {


    public SSDPHandlerControler() throws Exception {
    }

    public SSDPHandlerControler(List<NetworkInterface> networkInterfaceList) throws Exception {
        super(networkInterfaceList);
    }

    public SSDPHandlerControler(String ssdpHost, int ssdpPort) throws Exception {
        super(ssdpHost, ssdpPort);
    }

    public SSDPHandlerControler(String ssdpHost, int ssdpPort, List<NetworkInterface> networkInterfaceList) throws Exception {
        super(ssdpHost, ssdpPort, networkInterfaceList);
    }

    public SSDPHandlerControler(String ssdpHost, int ssdpPort, ExecutorService threadPool) throws Exception {
        super(ssdpHost, ssdpPort, threadPool);
    }

    public SSDPHandlerControler(String ssdpHost, int ssdpPort, List<NetworkInterface> networkInterfaceList, ExecutorService threadPool) throws Exception {
        super(ssdpHost, ssdpPort, networkInterfaceList, threadPool);
    }

    @Override
    public void processSSDPMessage(ISSDPMessage message) throws Exception {
        super.processSSDPMessage(message);
        System.out.println(message.toString());
    }

    @Override
    public void processSSDPMessage(String remoteAddr, int remotePort, ISSDPMessage message) throws Exception{
        super.processSSDPMessage(remoteAddr,remotePort,message);
        if(message instanceof AliveMessage){
            AliveMessage alive = (AliveMessage)message;
            System.out.println("Server:"+alive.getServer());
            System.out.println("Host:"+alive.getHost());
            System.out.println("Location:"+alive.getLocation());
            System.out.println("usn:"+alive.getUsn());
            System.out.println("port:"+alive.getPort());

        }
    }
}


package com.foulkes.lights.mvc.service;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
public interface RoomService {

    List<ComponentInterface> getAllServices(String room);
    List<ComponentInterface> getServicesByType(String room, ServiceTypes type);
    List<String> getAllRooms();

}

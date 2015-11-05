package com.foulkes.lights.mvc.service;

import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.interfaces.ComponentInterface;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Override
    public List<ComponentInterface> getAllServices(String room) {
        return null;
    }

    @Override
    public List<ComponentInterface> getServicesByType(String room,ServiceTypes type) {
        return null;
    }

    @Override
    public List<String> getAllRooms() {
        return null;
    }

}

package com.foulkes.lights.mvc.registration;

import com.foulkes.lights.mvc.components.SimpleLight;
import com.foulkes.lights.mvc.controllers.RegisterController;
import com.foulkes.lights.mvc.enums.RegistrationStatus;
import com.foulkes.lights.mvc.enums.ServiceTypes;
import com.foulkes.lights.mvc.exception.NotFound;
import com.foulkes.lights.mvc.interfaces.ComponentInterface;
import com.foulkes.lights.mvc.model.ComponentsModel;
import com.foulkes.lights.mvc.model.ManagedDeviceModel;
import com.foulkes.lights.mvc.service.*;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by danfoulkes on 03/10/15.
 */
public class MockImplementationFactory {


    @Test
    public void registerService() throws NotFound {

        final String UNIQUE_ID = "ID123456";
        ComponentsModel demo = new ComponentsModel();
        demo.setComponentType(ServiceTypes.LIGHT);
        demo.setUniquieId(UNIQUE_ID);
        demo.setAssigned(false);

        RegisterController mockReg = mock(RegisterController.class);
        ComponentService componentService = mock(ComponentServiceImpl.class);

        when(mockReg.registerComponant(UNIQUE_ID, ServiceTypes.LIGHT, "192.168.1.198")).thenReturn(RegistrationStatus.OK);
        when(componentService.getById(UNIQUE_ID)).thenReturn(demo);


        //service registered
        assertEquals(mockReg.registerComponant(UNIQUE_ID, ServiceTypes.LIGHT, "192.168.1.198"), RegistrationStatus.OK);
        assertEquals(componentService.getById(UNIQUE_ID).getUniquieId(), demo.getUniquieId());
        assertEquals(componentService.getById(UNIQUE_ID).getComponentType(), ServiceTypes.LIGHT);
    }

    @Test
    public void addComponentToManagedDevices(){

        final Long UNIQUE_ID = new Long("123456");
        final String ROOM = "Living Room";

        ComponentsModel demo = new ComponentsModel();
        demo.setComponentType(ServiceTypes.LIGHT);
        demo.setUniquieId("ID123");
        demo.setAssigned(false);
        demo.setIp("192.168.1.197");
        ComponentInterface fakeComponent = mock(SimpleLight.class);


        final ManagedDeviceModel managedDeviceModel = new ManagedDeviceModel();
        managedDeviceModel.setComponentsModel(fakeComponent);
        managedDeviceModel.setId(UNIQUE_ID);
        managedDeviceModel.setRoom(ROOM);

        ManagedDeviceService managedDeviceService = mock(ManagedDeviceServiceImpl.class);
        when(managedDeviceService.add(demo, ROOM)).thenReturn(managedDeviceModel);
        when(managedDeviceService.add(demo,ROOM)).thenReturn(managedDeviceModel);
        demo.setAssigned(true);

        assertEquals(managedDeviceService.add(demo,ROOM).getRoom(), ROOM);
        assertEquals(managedDeviceService.add(demo,ROOM).getId(), UNIQUE_ID);

    }




}

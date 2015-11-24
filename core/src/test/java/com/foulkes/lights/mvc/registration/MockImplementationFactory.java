package com.foulkes.lights.mvc.registration;


import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import com.foulkes.lights.mvc.controllers.RegisterController;
import com.foulkes.lights.common.enums.RegistrationStatus;

import com.foulkes.lights.mvc.service.*;
import org.junit.Test;

import java.util.ArrayList;

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

        when(mockReg.registerComponent(UNIQUE_ID,ServiceTypes.LIGHT, "192.168.1.198", "SOC1")).thenReturn(RegistrationStatus.OK);
        when(componentService.getById(UNIQUE_ID,"SOC1")).thenReturn(demo);


        //service registered
        assertEquals(mockReg.registerComponent(UNIQUE_ID,ServiceTypes.LIGHT, "192.168.1.198", "SOC1"), RegistrationStatus.OK);
        assertEquals(componentService.getById(UNIQUE_ID,"SOC1").getUniquieId(), demo.getUniquieId());
        assertEquals(componentService.getById(UNIQUE_ID,"SOC1").getComponentType(), ServiceTypes.LIGHT);
    }

    @Test
    public void addComponentToManagedDevices(){


    }




}

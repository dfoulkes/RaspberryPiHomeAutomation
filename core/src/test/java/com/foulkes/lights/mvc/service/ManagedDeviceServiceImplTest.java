package com.foulkes.lights.mvc.service;

import com.foulkes.lights.common.doa.ManageDeviceServiceDao;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import junit.framework.TestCase;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by danfoulkes on 23/11/15.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:/managedService-config.xml" })
public class ManagedDeviceServiceImplTest extends TestCase {

    final String ROOM = "livingRoom";

    @Mock
    private ManageDeviceServiceDao manageDeviceServiceDao = mock(ManageDeviceServiceDao.class);

    private ManagedDeviceService managedDeviceService = new ManagedDeviceServiceImpl(manageDeviceServiceDao);


    @Test
    public void testDuplicateDevice(){

        ManagedDeviceModel fakeModel = getTestModel();
        when(manageDeviceServiceDao.getByRoom(ROOM)).thenReturn(fakeModel);

        Boolean errorThrown = false;
        try {
            managedDeviceService.addDevice(getComponent(), fakeModel);
        } catch (AlreadyExists alreadyExists) {
            errorThrown = true;

        }
        assertTrue(errorThrown);
    }

    @Test
    public void testAddNewDevice(){

        ManagedDeviceModel fakeModel = getTestModel();

        when(manageDeviceServiceDao.getByRoom(ROOM)).thenReturn(fakeModel);
        ComponentsModel newComponent = new ComponentsModel();
        newComponent.setAddressDetails("1");
        newComponent.setUniquieId("2");
        newComponent.setComponentType(ServiceTypes.LIGHT);


        try {
            ManagedDeviceModel returnedModel = managedDeviceService.getByRoom(ROOM);
            assertFalse(returnedModel.getComponentsModel().contains(newComponent));
            managedDeviceService.addDevice(newComponent, fakeModel);
            returnedModel = managedDeviceService.getByRoom(ROOM);
            assertTrue(returnedModel.getComponentsModel().contains(newComponent));
        } catch (AlreadyExists alreadyExists) {
            fail("should not throw an exception for duplicate");
        }

    }

    @Test
    public void createNewManagedRoom(){
        ManagedDeviceModel fakeModel = getTestModel();
        when(manageDeviceServiceDao.add(fakeModel)).thenReturn(fakeModel);
        managedDeviceService.add(fakeModel);
    }

    @Test
    public void testRemovingDevice(){
        ManagedDeviceModel fakeModel = getTestModel();

        when(manageDeviceServiceDao.getByRoom(ROOM)).thenReturn(fakeModel);
        //simulate adding to the database
        when(manageDeviceServiceDao.update(fakeModel)).thenReturn(fakeModel);

        ManagedDeviceModel returnedModel = managedDeviceService.getByRoom(ROOM);
        assertTrue(returnedModel.getComponentsModel().contains(getComponent()));
        managedDeviceService.removeDeviceFromRoom(getComponent(), fakeModel);
        returnedModel = managedDeviceService.getByRoom(ROOM);
        assertFalse(returnedModel.getComponentsModel().contains(getComponent()));

    }

    @Test
    public void testIfManaged(){
        ManagedDeviceModel fakeModel = getTestModel();
        ArrayList<ManagedDeviceModel> all = new ArrayList<>();
        all.add(fakeModel);
        when(manageDeviceServiceDao.getAll()).thenReturn(all);
        assertTrue(managedDeviceService.isManaged(getComponent()));
    }



    private ComponentsModel getComponent(){

        ComponentsModel mod = new ComponentsModel();
        mod.setAddressDetails("1");
        mod.setUniquieId("1");
        mod.setComponentType(ServiceTypes.LIGHT);
        return mod;
    }


    private ManagedDeviceModel getTestModel(){
        ManagedDeviceModel model = new ManagedDeviceModel();
        model.setRoom(ROOM);
        model.setId(new Long("1"));

        HashSet<ComponentsModel> components = new HashSet<>();
        components.add(getComponent());
        model.setComponentsModel(components);
        return  model;
    }
}
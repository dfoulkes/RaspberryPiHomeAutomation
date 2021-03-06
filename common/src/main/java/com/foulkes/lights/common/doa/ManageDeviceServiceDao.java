package com.foulkes.lights.common.doa;

import com.foulkes.lights.common.domain.ManagedDevice;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import com.foulkes.lights.common.model.ManagedDeviceModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Repository
public class ManageDeviceServiceDao extends DaoInstance{


    public ManagedDeviceModel add(ManagedDeviceModel room){
        ManagedDevice transactionModel = ManagedDevice.build(room);
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(transactionModel);
        getEntityManager().getTransaction().commit();
        return ManagedDeviceModel.build(transactionModel);
    }

    public ManagedDeviceModel getByRoom(String room){
        ManagedDevice devices =  getEntityManager().createNamedQuery("ManagedDevice.getByRoom", ManagedDevice.class).setParameter("id",room).getSingleResult();
        return ManagedDeviceModel.build(devices);
    }

    public List<ManagedDeviceModel> getAll(){
        List<ManagedDeviceModel> returnList = new ArrayList<ManagedDeviceModel>();
        List<ManagedDevice> data = getEntityManager().createNamedQuery("ManagedDevice.getAll", ManagedDevice.class).getResultList();
        data = (data ==null? new ArrayList<ManagedDevice>(): data);

        for(ManagedDevice i : data){
            returnList.add(ManagedDeviceModel.build(i));
        }
        return returnList;
    }

    public ManagedDeviceModel update(ManagedDeviceModel model){
        getEntityManager().getTransaction().begin();
        ManagedDevice toUpdate = ManagedDevice.build(model);
        getEntityManager().merge(toUpdate);
        getEntityManager().getTransaction().commit();
        return ManagedDeviceModel.build(toUpdate);
    }

    public ManagedDeviceModel getByDevice(Long id) throws NotFound {
        try {
            return ManagedDeviceModel.build(getEntityManager().createNamedQuery("ManagedDevice.getByDevice", ManagedDevice.class).setParameter("id", id).getSingleResult());
        }
        catch (Exception e){
            throw new NotFound();
        }
    }

}

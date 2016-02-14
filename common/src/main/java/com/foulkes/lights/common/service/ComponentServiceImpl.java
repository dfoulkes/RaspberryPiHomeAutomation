package com.foulkes.lights.common.service;

import com.foulkes.lights.common.doa.ComponantDao;
import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.enums.GenericType;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Service
public class ComponentServiceImpl implements ComponentService
{

    @Autowired
    private ComponantDao componantDao;
    private Logger logger = Logger.getLogger(ComponentServiceImpl.class);


    @Override
    public List<ComponentsModel> getAll() {

        List<Components> allComponents = componantDao.getAll();
        allComponents = (allComponents != null? allComponents : new ArrayList<Components>());

        ArrayList<ComponentsModel> returnList = new ArrayList<>();
        for(Components i : allComponents){
            returnList.add(ComponentsModel.build(i));
        }
        return returnList;
    }

    @Override
    public ComponentsModel getById(String uniqueId, String address) throws NotFound {
        try {
            return ComponentsModel.build(componantDao.getById(uniqueId, address));
        }catch (Exception e){
            throw new NotFound();
        }
    }

    @Override
    public ComponentsModel add(String uniqueId, ServiceTypes type, String ip, String address, GenericType genericType) throws AlreadyExists, FailedToAdd {
       //check if the device exists if so then update the ip address
        try {
            ComponentsModel c = ComponentsModel.build(componantDao.getById(uniqueId, address));
            c.setIp(ip);
            c.setComponentType(type);
            c.setUniquieId(uniqueId);
            c.setAddressDetails(address);
            c.setGenericType(genericType);
            logger.info("updated component "+c.getUniquieId()+" @ "+c.getIp());

            if(componantDao.getById(c.getUniquieId(), address) != null){
               return update(c);
            }
            else{
                return componantDao.add(uniqueId,type,ip, address, genericType);
            }
        } catch (NotFound notFound) {
            logger.info("adding new");
            return componantDao.add(uniqueId,type,ip, address, genericType);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new FailedToAdd();
        }

    }

    @Override
    public ComponentsModel update(ComponentsModel componentsModel) throws FailedToAdd {
        return componantDao.update(componentsModel);

    }

    @Override
    public List<ComponentsModel> getByUniqueId(String componentId) throws NotFound {
        return componantDao.getByUniqueId(componentId);
    }


}

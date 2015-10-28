package com.foulkes.lights.mvc.service;

import com.foulkes.lights.common.doa.ComponantDao;
import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.model.ComponentsModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public ComponentsModel getById(String uniqueId) throws NotFound {
        try {
            return ComponentsModel.build(componantDao.getById(uniqueId));
        }catch (Exception e){
            throw new NotFound();
        }
    }

    @Override
    public ComponentsModel add(String uniqueId, ServiceTypes type, String ip) throws AlreadyExists, FailedToAdd {
       //check if the device exists if so then update the ip address
        try {

            ComponentsModel c = update(ComponentsModel.build(componantDao.getById(uniqueId)));
            logger.info("updated component "+c.getUniquieId()+" @ "+c.getIp());
            return c;
        } catch (NotFound notFound) {
            logger.info("adding new");
            return componantDao.add(uniqueId,type,ip);
        }catch(Exception e){
            logger.error(e.getMessage());
            throw new FailedToAdd();
        }

    }

    @Override
    public ComponentsModel update(ComponentsModel componentsModel) throws FailedToAdd {
            return componantDao.update(componentsModel);

    }


}

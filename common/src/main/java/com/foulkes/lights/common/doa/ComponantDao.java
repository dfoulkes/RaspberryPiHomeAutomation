package com.foulkes.lights.common.doa;

import com.foulkes.lights.common.domain.Components;
import com.foulkes.lights.common.enums.ServiceTypes;
import com.foulkes.lights.common.exception.FailedToAdd;
import com.foulkes.lights.common.exception.NotFound;
import com.foulkes.lights.common.exception.AlreadyExists;
import com.foulkes.lights.common.model.ComponentsModel;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Created by danfoulkes on 03/10/15.
 */
@Repository
public class ComponantDao extends DaoInstance{

    private Logger logger = Logger.getLogger(ComponantDao.class);

    public ComponentsModel add(String id, ServiceTypes componentType, String ip) throws AlreadyExists, FailedToAdd {

        try {
            if(getById(id) != null){
                throw new AlreadyExists();
            }
        } catch (NotFound notFound) {
            //happy path now continue.
            logger.info("first time we've seen this component");
        }
        try {
            Components com = new Components();
            com.setUniquieId(id);
            com.setAssigned(false);
            com.setComponentType(componentType);
            com.setIp(ip);
            getEntityManager().getTransaction().begin();
            getEntityManager().persist(com);
            getEntityManager().getTransaction().commit();
            return ComponentsModel.build(com);
        }
        catch(Exception e){
            logger.error(e.getMessage());
            throw new FailedToAdd();
        }
    }

    public List<Components> getAll() {
        return getEntityManager().createNamedQuery("Components.getAll", Components.class).getResultList();
    }

    /**
     * Find a component via its device id, if no record found then return a NotFound Exception
     * @param uniqueId
     * @return
     * @throws NotFound
     */
    public Components getById(String uniqueId) throws NotFound{

        try {
            return getEntityManager().createNamedQuery("Components.getById", Components.class).setParameter("id", uniqueId).getSingleResult();
        }catch (NoResultException e){
            logger.info("record not found for:"+uniqueId);
            throw new NotFound();
        }catch (Exception e){
            logger.error(e.getMessage());
            throw new NotFound();
        }

        }

    public ComponentsModel update(ComponentsModel componentsModel) throws FailedToAdd {
        Components comp = Components.build(componentsModel);
        getEntityManager().getTransaction().begin();
        getEntityManager().merge(comp);
        getEntityManager().getTransaction().commit();
        return ComponentsModel.build(comp);
    }
}

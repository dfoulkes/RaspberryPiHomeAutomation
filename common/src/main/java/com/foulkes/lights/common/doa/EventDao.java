package com.foulkes.lights.common.doa;

import com.foulkes.lights.common.domain.Event;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by danfoulkes on 04/01/2016.
 */
@Repository
public class EventDao extends DaoInstance {

    public void add(Event event){
        EntityManager t = getNewManager();
        t.getTransaction().begin();
        t.persist(event);
        t.getTransaction().commit();
        t.close();
    }
    public void remove(Event event){
        EntityManager t = getNewManager();
        t.getTransaction().begin();
        t.remove(event);
        t.getTransaction().commit();
        t.getTransaction().commit();
        t.close();
    }

    public List<Event> getEvents(){
       return getEntityManager().createNamedQuery("Event.list",Event.class).getResultList();
    }
}

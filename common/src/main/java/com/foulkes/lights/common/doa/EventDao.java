package com.foulkes.lights.common.doa;

import com.foulkes.lights.common.domain.Event;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by danfoulkes on 04/01/2016.
 */
@Repository
public class EventDao extends DaoInstance {

    public void add(Event event){
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(event);
        getEntityManager().getTransaction().commit();
    }
    public void remove(Event event){
        getEntityManager().getTransaction().begin();
        getEntityManager().remove(event);
        getEntityManager().getTransaction().commit();
    }

    public List<Event> getEvents(){
       return getEntityManager().createNamedQuery("Event.list",Event.class).getResultList();
    }
}

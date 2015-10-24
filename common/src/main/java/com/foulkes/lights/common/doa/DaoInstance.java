package com.foulkes.lights.common.doa;

import org.apache.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

public abstract class DaoInstance {
	
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("sharedDomain");
	EntityManager em = emf.createEntityManager();
	Logger logger  = Logger.getLogger(DaoInstance.class);

	public EntityManager getEntityManager(){
		
		if(emf == null || !emf.isOpen()){
			createEntityManager();
			
		}
		 if(!em.isOpen())
			 em = emf.createEntityManager();  
		 
			return em;
	}
	
	public void createEntityManager(){
		logger.debug("emf create Manager is open:"+emf.isOpen());
		if(!emf.isOpen())
			createEntityManagerFactory();
		
		em = emf.createEntityManager();
		
		Map<String, Object> props = em.getProperties();
		if(props == null){
			logger.error("propertys of the entity manager were null");
			return;
		}
		for( String i: props.keySet()){
			logger.debug("property:"+i+":"+props.get(i).toString());
		}
	}
	
	public void createEntityManagerFactory(){
		emf = Persistence.createEntityManagerFactory("domain");
		logger.debug(emf.getPersistenceUnitUtil());
	}

}

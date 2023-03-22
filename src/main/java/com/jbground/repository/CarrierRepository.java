package com.jbground.repository;

import com.jbground.config.CommonEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class CarrierRepository {
    @PersistenceContext
    EntityManager em;

    public CarrierRepository(){
        em = CommonEntityManagerFactory.getInstance().createEntityManager();
    }

    public List findCarriers(){
        return em.createQuery("select c from Carrier c").getResultList();
    }

    public List findCarriersByMachineName(){
        return em.createQuery("select c from Carrier c").getResultList();
    }

    public List findAll(Class clazz){
        return em.createQuery("select o from "+clazz.getSimpleName()+" o").getResultList();
    }

}

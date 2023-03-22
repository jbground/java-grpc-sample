package com.jbground.repository;

import com.jbground.config.CommonEntityManagerFactory;

import javax.persistence.EntityManager;
import java.util.List;

public class MachineRepository {

    EntityManager em;

    public MachineRepository(){
        em = CommonEntityManagerFactory.getInstance().createEntityManager();
    }

    public List findMachines(){
        return em.createQuery("select m from Machine m").getResultList();
    }
}

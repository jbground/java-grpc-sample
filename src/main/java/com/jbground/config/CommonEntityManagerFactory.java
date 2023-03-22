package com.jbground.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CommonEntityManagerFactory {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getInstance() {
        if(emf == null) {
            synchronized (EntityManagerFactory.class) {
                if(emf == null) {
                    try {
                        emf = Persistence.createEntityManagerFactory("my-persistence");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return emf;
    }
}

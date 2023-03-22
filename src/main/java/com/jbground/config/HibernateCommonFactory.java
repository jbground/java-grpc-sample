package com.jbground.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateCommonFactory {
	private static SessionFactory sessionFactory;
	
	public static SessionFactory getInstance() {
		if(sessionFactory == null) {
			synchronized (SessionFactory.class) {
				if(sessionFactory==null) {
					try {
						sessionFactory = new Configuration().configure("/META-INF/hibernate/hibernate.cfg.xml")
								.buildSessionFactory();
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return sessionFactory;
	}
}

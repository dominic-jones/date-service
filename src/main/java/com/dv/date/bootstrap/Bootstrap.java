package com.dv.date.bootstrap;

import com.dv.date.impl.Location;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//TODO 2013-11-10 Dom - Should be a test-only class
@Singleton
@Startup
public class Bootstrap {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    void bootstrap() {

        entityManager.persist(new Location("UTC"));
        entityManager.persist(new Location("Europe/London"));
        entityManager.persist(new Location("Europe/Berlin"));
    }

}

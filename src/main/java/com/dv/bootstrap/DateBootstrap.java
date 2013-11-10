package com.dv.bootstrap;

import com.dv.date.Location;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class DateBootstrap {

    @PersistenceContext
    private EntityManager entityManager;

    @PostConstruct
    void bootstrap() {

        entityManager.persist(new Location("UTC"));
        entityManager.persist(new Location("Europe/London"));
        entityManager.persist(new Location("Europe/Berlin"));
    }

}

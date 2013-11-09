package com.dv.date;

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

        entityManager.persist(new Timezone("UTC"));
        entityManager.persist(new Timezone("Europe/London"));
        entityManager.persist(new Timezone("Europe/Berlin"));
    }

}

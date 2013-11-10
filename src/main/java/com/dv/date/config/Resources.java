package com.dv.date.config;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class Resources {

    @Produces
    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager em;
}

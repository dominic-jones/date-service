package com.dv.date.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;

public class CreateDateHandler {

    @Inject
    private EntityManager em;

    void handle(String location) {

        em.persist(new Location(location));
    }
}

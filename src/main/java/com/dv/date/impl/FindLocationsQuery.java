package com.dv.date.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class FindLocationsQuery {

    @Inject
    private EntityManager em;

    Iterable<Location> execute() {

        CriteriaQuery<Location> query = em.getCriteriaBuilder()
                .createQuery(Location.class);

        query.from(Location.class);

        return em.createQuery(query)
                .getResultList();
    }
}

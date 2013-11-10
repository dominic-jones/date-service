package com.dv.date.impl;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

/**
 * JPA's EntityManager is a realization of the Domain Store pattern. As such, applying a Data Access Object (DAO)
 * pattern on top of it feels like unnecessary indirection. However code can be refactored for better SRP,
 * so the Query has been located in its own object.
 */
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

package com.dv.date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;

public class FindDateQuery {

    @Inject
    private EntityManager em;

    Iterable<Timezone> execute() {

        CriteriaQuery<Timezone> query = em.getCriteriaBuilder()
                .createQuery(Timezone.class);

        query.from(Timezone.class);

        return em.createQuery(query)
                .getResultList();
    }
}

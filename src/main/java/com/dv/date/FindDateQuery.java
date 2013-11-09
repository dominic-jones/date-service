package com.dv.date;

import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.Map;

import static org.joda.time.DateTimeZone.UTC;
import static org.joda.time.DateTimeZone.forID;

public class FindDateQuery {

    @Inject
    private EntityManager em;

    Map<String, DateTime> execute() {

        CriteriaQuery<Timezone> query = em.getCriteriaBuilder()
                .createQuery(Timezone.class);

        query.from(Timezone.class);

        Iterable<Timezone> timezones = em.createQuery(query)
                .getResultList();

        DateTime rawDate = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0);

        Map<String, DateTime> dates = new HashMap<>();

        for (Timezone timezone : timezones) {
            dates.put(timezone.getTimezone(), rawDate.withZone(forID(timezone.getTimezone())));
        }

        return dates;
    }
}

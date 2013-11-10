package com.dv.date;

import org.joda.time.DateTime;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTimeZone.UTC;

@Stateless
@Path("/dates")
public class DateService {

    @Inject
    FindLocationsQuery findLocationsQuery;

    @GET
    @Produces(APPLICATION_JSON)
    public DatesVO dates() {

        final DateTime sourceDate = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0);

        return new DatesVO(sourceDate, findLocationsQuery.execute());
    }
}

package com.dv.date;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.google.common.collect.Lists.newArrayList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTimeZone.UTC;

@Path("dates")
public class DateService {

    @GET
    @Produces(APPLICATION_JSON)
    public Iterable<DateTime> dates() {

        DateTime rawDate = new DateTime(UTC).withDate(2010, 10, 20).withTime(0, 0, 0, 0);

        return newArrayList(rawDate);
    }
}

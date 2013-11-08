package com.dv.date;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.google.common.collect.Lists.newArrayList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTime.parse;
import static org.joda.time.format.DateTimeFormat.forPattern;

@Path("dates")
public class DateService {

    @GET
    @Produces(APPLICATION_JSON)
    public Iterable<DateTime> dates() {

        return newArrayList(parse("2010-10-20", forPattern("yyyy-MM-dd")));
    }
}

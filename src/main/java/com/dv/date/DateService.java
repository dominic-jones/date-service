package com.dv.date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import static com.google.common.collect.Lists.newArrayList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("dates")
public class DateService {

    @GET
    @Produces(APPLICATION_JSON)
    public Iterable<String> dates() {

        return newArrayList("10/10/2010");
    }
}

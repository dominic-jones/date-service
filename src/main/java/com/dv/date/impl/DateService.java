package com.dv.date.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTime.parse;

@Stateless
@Path("/dates")
public class DateService {

    @Inject
    FindLocationsQuery findLocationsQuery;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{sourceTime}")
    public DatesModel dates(@PathParam("sourceTime") String sourceTime) {

        return new DatesModel(parse(sourceTime), findLocationsQuery.execute());
    }
}

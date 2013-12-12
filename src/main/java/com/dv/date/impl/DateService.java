package com.dv.date.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTime.parse;

/**
 * Simple EJB/JAX-RS service.
 */
@Stateless
@Path("/dates")
public class DateService {

    @Inject
    private LocationRepository locationRepository;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{sourceTime}")
    public DatesModel dates(@PathParam("sourceTime") String sourceTime) {

        return new DatesModel(parse(sourceTime), locationRepository.findLocation());
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public void createLocation(String location) {

        locationRepository.createLocation(location);
    }
}

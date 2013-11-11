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
 * <p/>
 * This is not pattern'd as I have historically done, but tries to display some of my preferred thinking.
 * <p/>
 * Firstly, I prefer a lean, thin controller. Following SRP, I prefer a controller to be responsible for the service
 * wiring. Sourcing from /dates, accepting JSON, and so forth. Other responsibilities have been handed out to the
 * query object and the view model object.
 * <p/>
 * For more complicated cases, I would prefer to relocate the method logic (currently only findLocationsQuery) to
 * another handler class for better SRP.
 */
@Stateless
@Path("/dates")
public class DateService {

    @Inject
    private CreateDateHandler createDateHandler;

    @Inject
    private FindLocationsQuery findLocationsQuery;

    @GET
    @Produces(APPLICATION_JSON)
    @Path("{sourceTime}")
    public DatesModel dates(@PathParam("sourceTime") String sourceTime) {

        return new DatesModel(parse(sourceTime), findLocationsQuery.execute());
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public void createDate(String location) {

        createDateHandler.handle(location);
    }
}

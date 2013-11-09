package com.dv.date;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("dates")
public class DateService {

    DateRepository dateRepository = new DateRepository();

    @GET
    @Produces(APPLICATION_JSON)
    public Map<String, DateTime> dates() {

        return dateRepository.getDates();
    }

}

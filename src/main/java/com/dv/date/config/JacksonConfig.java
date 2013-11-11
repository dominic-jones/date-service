package com.dv.date.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.ext.Provider;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Provider
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class JacksonConfig extends JacksonJaxbJsonProvider {

    public JacksonConfig() {

        super();
        _mapperConfig.configure(INDENT_OUTPUT, true);
        ObjectMapper mapper = _mapperConfig.getConfiguredMapper();
        mapper.registerModule(new Iso8601JodaModule())
                .configure(WRITE_DATES_AS_TIMESTAMPS, false);
    }
}

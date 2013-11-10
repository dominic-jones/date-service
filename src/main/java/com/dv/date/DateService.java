package com.dv.date;

import com.google.common.base.Function;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;

import static com.dv.date.Location.toDateTime;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.uniqueIndex;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.joda.time.DateTimeZone.UTC;

@Stateless
@Path("dates")
public class DateService {

    @Inject
    FindLocationsQuery findLocationsQuery;

    @GET
    @Produces(APPLICATION_JSON)
    public Map<String, DateTime> dates() {

        final DateTime sourceDate = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0);

        Iterable<DateTime> dates = transform(findLocationsQuery.execute(), toDateTime(sourceDate));

        return uniqueIndex(dates, new Function<DateTime, String>() {
            @Nullable
            @Override
            public String apply(@Nullable DateTime dateTime) {

                assert dateTime != null;
                return dateTime.getZone().getID();
            }
        });
    }

}

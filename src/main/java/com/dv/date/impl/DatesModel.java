package com.dv.date.impl;

import com.dv.date.config.Iso8601DateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Function;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.Map;

import static com.dv.date.impl.Location.toDateTime;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.uniqueIndex;

/**
 * View model for displaying dates. The intent for this is to provide one place to collect all the information
 * required to display in our specific dates view, without revealing our whole domain model.
 * <p/>
 * One direction would be for this is to map to exactly one View if using a Java view engine (JSF et al). However for
 * now this just maps (using default conventions) to json.
 */
public class DatesModel {

    @JsonDeserialize(contentUsing = Iso8601DateTimeDeserializer.class)
    private Map<String, DateTime> dates;

    protected DatesModel() {

    }

    public DatesModel(DateTime sourceTime,
                      Iterable<Location> locations) {

        Iterable<DateTime> iDates = transform(locations, toDateTime(sourceTime));

        dates = uniqueIndex(iDates, new Function<DateTime, String>() {
            @Nullable
            @Override
            public String apply(@Nullable DateTime dateTime) {

                assert dateTime != null;
                return dateTime.getZone().getID();
            }
        });
    }

    public Map<String, DateTime> getDates() {

        return dates;
    }
}

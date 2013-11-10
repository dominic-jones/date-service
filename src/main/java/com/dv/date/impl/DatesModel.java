package com.dv.date.impl;

import com.google.common.base.Function;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.Map;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.uniqueIndex;

/**
 * View model for displaying dates. The intent for this is to provide one place to collect all the information
 * required to display in our specific dates view, without revealing our whole domain model.
 */
public class DatesModel {

    private Map<String, DateTime> dates;

    protected DatesModel() {

    }

    public DatesModel(DateTime sourceTime,
                      Iterable<Location> locations) {

        Iterable<DateTime> iDates = transform(locations, Location.toDateTime(sourceTime));

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
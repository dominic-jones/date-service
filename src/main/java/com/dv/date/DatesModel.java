package com.dv.date;

import com.google.common.base.Function;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import java.util.Map;

import static com.dv.date.Location.toDateTime;
import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Maps.uniqueIndex;

public class DatesModel {

    private Map<String, DateTime> dates;

    protected DatesModel() {

    }

    public DatesModel(DateTime sourceDate,
                      Iterable<Location> locations) {

        Iterable<DateTime> iDates = transform(locations, toDateTime(sourceDate));

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

package com.dv.date;

import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static org.joda.time.DateTimeZone.UTC;
import static org.joda.time.DateTimeZone.forID;

public class DateRepository {

    Map<String, DateTime> getDates() {

        DateTime rawDate = new DateTime(UTC).withDate(2010, 10, 20).withTime(0, 0, 0, 0);

        Iterable<String> timezones = newArrayList(
                "UTC",
                "Europe/London",
                "Europe/Berlin"
        );

        Map<String, DateTime> dates = new HashMap<>();

        for (String timezone : timezones) {
            dates.put(timezone, rawDate.withZone(forID(timezone)));
        }
        return dates;
    }
}

package com.dv.date;

import com.google.common.base.Function;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static org.joda.time.DateTimeZone.forID;

@Entity
public class Location {

    @Id
    @GenericGenerator(name = "table-hilo-generator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @GeneratedValue(generator = "table-hilo-generator")
    private Long id;

    private String timezone;

    protected Location() {

    }

    public Location(final String timezone) {

        this.timezone = timezone;
    }

    public static Function<Location, DateTime> toDateTime(final DateTime sourceDate) {

        return new Function<Location, DateTime>() {
            @Override
            public DateTime apply(@Nullable Location location) {

                return sourceDate.withZone(forID(location.getTimezone()));
            }
        };
    }

    public Long getId() {

        return id;
    }

    public String getTimezone() {

        return timezone;
    }
}

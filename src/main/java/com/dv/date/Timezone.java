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
public class Timezone {

    @Id
    @GenericGenerator(name = "table-hilo-generator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @GeneratedValue(generator = "table-hilo-generator")
    private Long id;

    private String timezone;

    protected Timezone() {

    }

    public Timezone(final String timezone) {

        this.timezone = timezone;
    }

    public static Function<Timezone, DateTime> toDateTime(final DateTime sourceDate) {

        return new Function<Timezone, DateTime>() {
            @Override
            public DateTime apply(@Nullable Timezone timezone) {

                return sourceDate.withZone(forID(timezone.getTimezone()));
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

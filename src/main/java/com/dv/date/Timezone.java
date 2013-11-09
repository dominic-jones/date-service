package com.dv.date;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    public Long getId() {

        return id;
    }

    public String getTimezone() {

        return timezone;
    }
}

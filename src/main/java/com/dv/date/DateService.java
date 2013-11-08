package com.dv.date;

import java.util.Date;

import static com.google.common.collect.Lists.newArrayList;

public class DateService {

    public Iterable<Date> dates() {

        return newArrayList(new Date());
    }
}

package com.dv.date

import org.junit.Test

import static org.joda.time.DateTime.parse
import static org.joda.time.format.DateTimeFormat.forPattern

class DateServiceTest {

    @Test
    void test() {

        def result = new DateService().dates()

        assert parse('2010-10-20', forPattern('yyyy-MM-dd')) == result[0]
    }
}

package com.dv.date

import org.junit.Test

import static org.joda.time.DateTime.parse
import static org.joda.time.DateTimeZone.UTC
import static org.joda.time.format.DateTimeFormat.forPattern

class DateServiceTest {

    DateService service = new DateService()

    @Test
    void 'Should return dates'() {
        def rawDate = parse('2010-10-20', forPattern('yyyy-MM-dd')).withZoneRetainFields(UTC)

        def result = service.dates()

        assert [rawDate] == result
    }

    @Test
    void 'Should return timezones'() {
        def result = service.dates()

        assert UTC == result[0].zone
    }
}

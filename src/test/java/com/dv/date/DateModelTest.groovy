package com.dv.date

import org.joda.time.DateTime
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

import static org.joda.time.DateTimeZone.UTC

/**
 * Simple example of a groovy unit test.
 */
@RunWith(MockitoJUnitRunner)
class DateModelTest {

    def locations = [
            new Location('UTC')
    ]

    @Test
    void 'Given time and location, should return correct time'() {
        def time = new DateTime(UTC)
                .withDate(2010, 10, 20)
                .withTime(0, 0, 0, 0)

        def result = new DatesModel(time, locations)

        assert '2010-10-20T00:00:00.000Z' == result.dates.UTC.toString()
    }
}

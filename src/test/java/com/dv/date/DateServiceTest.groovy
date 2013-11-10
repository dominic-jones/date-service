package com.dv.date

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import static org.joda.time.DateTimeZone.UTC
import static org.mockito.BDDMockito.given

/**
 * Presently, this test does not grant much value. It stands as a basic example of a groovy/mockito test-case.
 */
@RunWith(MockitoJUnitRunner)
class DateServiceTest {

    @InjectMocks
    DateService service

    @Mock
    FindLocationsQuery query

    def dates = [
            new Location('UTC')
    ]

    @Before
    void setUp() {
        given(query.execute())
                .willReturn(dates)
    }

    @Test
    void 'Should return dates'() {
        def result = service.dates()

        assert UTC == result.dates.UTC.zone
    }
}

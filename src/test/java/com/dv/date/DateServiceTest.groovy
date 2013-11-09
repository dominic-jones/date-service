package com.dv.date

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import static org.joda.time.DateTime.parse
import static org.joda.time.DateTimeZone.UTC
import static org.joda.time.format.DateTimeFormat.forPattern
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner)
class DateServiceTest {

    @InjectMocks
    DateService service

    @Mock
    DateRepository dateRepository

    def dates = [
            UTC: parse('2010-10-20', forPattern('yyyy-MM-dd')).withZoneRetainFields(UTC)
    ]

    @Before
    void setUp() {
        given(dateRepository.getDates())
                .willReturn(dates)
    }

    @Test
    void 'Should return dates'() {
        def result = service.dates()

        assert dates.UTC == result.UTC
    }
}

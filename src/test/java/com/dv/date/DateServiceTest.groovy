package com.dv.date

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

import static org.joda.time.DateTimeZone.UTC
import static org.mockito.BDDMockito.given

@RunWith(MockitoJUnitRunner)
class DateServiceTest {

    @InjectMocks
    DateService service

    @Mock
    FindDateQuery findDateQuery

    def dates = [
            new Timezone('UTC')
    ]

    @Before
    void setUp() {
        given(findDateQuery.execute())
                .willReturn(dates)
    }

    @Test
    void 'Should return dates'() {
        def result = service.dates()

        assert UTC == result.UTC.zone
    }
}

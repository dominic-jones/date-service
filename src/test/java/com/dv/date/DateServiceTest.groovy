package com.dv.date

import org.junit.Test

class DateServiceTest {

	@Test
	void test() {

		def result = new DateService().dates()

		assert !result.empty
	}
}

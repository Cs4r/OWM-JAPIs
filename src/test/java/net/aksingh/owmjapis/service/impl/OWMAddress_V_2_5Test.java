/*
 * Copyright (c) 2013-2015 Ashutosh Kumar Singh <me@aksingh.net>
 * Copyright (c) 2015 Cesar Aguilera
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.aksingh.owmjapis.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import net.aksingh.owmjapis.service.OWMLanguage;
import net.aksingh.owmjapis.service.OWMUnits;

/**
 * Unit tests for {@link OWMAddress_V_2_5}
 * 
 * @author cs4r
 *
 */
public class OWMAddress_V_2_5Test {

	private static final OWMUnits UNITS = OWMUnits.METRIC;
	private static final OWMLanguage LANGUAGE = OWMLanguage.ENGLISH;
	private static final String APP_ID = "appId";
	private OWMAddress_V_2_5 subjectUnderTest;

	@Before
	public void setUp() {
		subjectUnderTest = new OWMAddress_V_2_5(UNITS, LANGUAGE, APP_ID);
	}

	@Test
	public void testCurrentWeatherByCityNameReturnsWellFormedURL() throws UnsupportedEncodingException {
		String currentWeatherByCityNameUrl = subjectUnderTest.currentWeatherByCityName("cityName");
		assertEquals(
				"http://api.openweathermap.org/data/2.5/weather?q=cityName&mode=json&units=metric&lang=en&appId=appId",
				currentWeatherByCityNameUrl);
	}

	@Test
	public void testCurrentWeatherByCityNameAndCountryCodeReturnsWellFormedURL() throws UnsupportedEncodingException {
		String currentWeatherByCityNameUrl = subjectUnderTest.currentWeatherByCityName("cityName", "countryCode");
		assertEquals(
				"http://api.openweathermap.org/data/2.5/weather?q=cityName%2CcountryCode&mode=json&units=metric&lang=en&appId=appId",
				currentWeatherByCityNameUrl);
	}

	@Test
	public void testCurrentWeatherByCityCodeReturnsWellFormedURL() {
		String currentWeatherByCityCodeUrl = subjectUnderTest.currentWeatherByCityCode(33);
		System.out.println(currentWeatherByCityCodeUrl);
		assertEquals("http://api.openweathermap.org/data/2.5/weather?id=33&mode=json&units=metric&lang=en&appId=appId",
				currentWeatherByCityCodeUrl);
	}

}

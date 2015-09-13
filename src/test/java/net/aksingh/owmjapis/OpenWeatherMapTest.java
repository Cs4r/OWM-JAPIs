/*
 * Copyright (c) 2013-2015 Ashutosh Kumar Singh <me@aksingh.net>
 * Copyright (c) 2015 Cesar Aguilera (@cs4r)
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
package net.aksingh.owmjapis;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.aksingh.owmjapis.exception.WeatherNotFoundException;
import net.aksingh.owmjapis.service.OWMLanguage;
import net.aksingh.owmjapis.service.OWMUnits;
import net.aksingh.owmjapis.service.OWMWeatherProvider;

/**
 * Unit tests for {@link OpenWeatherMap}
 * 
 * @author cs4r
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapTest {

	private static final String API_KEY = "";
	private static final String CITY_NAME = "cityName";
	private static final String COUNTRY_CODE = "cc";
	private static final long CITY_CODE = 0;
	private static final float LATITUDE = 1;
	private static final float LONGITUDE = 2;
	private static final byte COUNT = 0;

	@Mock
	private OWMWeatherProvider owmWeatherProvider;

	private OpenWeatherMap subjectUnderTest;

	@Before
	public void setUp() {
		subjectUnderTest = new OpenWeatherMap(API_KEY);
		subjectUnderTest.setOWMWeatherProvider(owmWeatherProvider);
	}

	@Test
	public void testDefaultUnitsIsSetToMetric() {
		subjectUnderTest = new OpenWeatherMap(API_KEY);
		assertEquals(OWMUnits.METRIC, subjectUnderTest.getUnits());
	}

	@Test
	public void testDefaulLanguageIsSetToEnglish() {
		subjectUnderTest = new OpenWeatherMap(API_KEY);
		assertEquals(OWMLanguage.ENGLISH, subjectUnderTest.getLang());
	}

	@Test
	public void testCurrentWeatherByCityNameDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.currentWeatherByCityName(CITY_NAME);
		verify(owmWeatherProvider, times(1)).currentWeatherByCityName(CITY_NAME);
	}

	@Test
	public void testCurrentWeatherByCityNameAndCountryCodeDelegatesToOWMWeatherProvider()
			throws WeatherNotFoundException {
		subjectUnderTest.currentWeatherByCityName(CITY_NAME, COUNTRY_CODE);
		verify(owmWeatherProvider, times(1)).currentWeatherByCityName(CITY_NAME, COUNTRY_CODE);
	}

	@Test
	public void testCurrentWeatherByCityCodeDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.currentWeatherByCityCode(CITY_CODE);
		verify(owmWeatherProvider, times(1)).currentWeatherByCityCode(CITY_CODE);
	}

	@Test
	public void testCurrentWeatherByCoordinatesDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.currentWeatherByCoordinates(LATITUDE, LONGITUDE);
		verify(owmWeatherProvider, times(1)).currentWeatherByCoordinates(LATITUDE, LONGITUDE);
	}

	@Test
	public void testHourlyForecastByCityNameDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.hourlyForecastByCityName(CITY_NAME);
		verify(owmWeatherProvider, times(1)).hourlyForecastByCityName(CITY_NAME);
	}

	@Test
	public void testHourlyWeatherByCityNameAndCountryCodeDelegatesToOWMWeatherProvider()
			throws WeatherNotFoundException {
		subjectUnderTest.hourlyForecastByCityName(CITY_NAME, COUNTRY_CODE);
		verify(owmWeatherProvider, times(1)).hourlyForecastByCityName(CITY_NAME, COUNTRY_CODE);
	}

	@Test
	public void testHourlyForecastByCityCodeDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.hourlyForecastByCityCode(CITY_CODE);
		verify(owmWeatherProvider, times(1)).hourlyForecastByCityCode(CITY_CODE);
	}

	@Test
	public void testHourlyForecastByCoordinatesDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.hourlyForecastByCoordinates(LATITUDE, LONGITUDE);
		verify(owmWeatherProvider, times(1)).hourlyForecastByCoordinates(LATITUDE, LONGITUDE);
	}

	@Test
	public void testDailyForecastByCityNameDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.dailyForecastByCityName(CITY_NAME, COUNT);
		verify(owmWeatherProvider, times(1)).dailyForecastByCityName(CITY_NAME, COUNT);
	}

	@Test
	public void testDailyWeatherByCityNameAndCountryCodeDelegatesToOWMWeatherProvider()
			throws WeatherNotFoundException {
		subjectUnderTest.dailyForecastByCityName(CITY_NAME, COUNTRY_CODE, COUNT);
		verify(owmWeatherProvider, times(1)).dailyForecastByCityName(CITY_NAME, COUNTRY_CODE, COUNT);
	}

	@Test
	public void testDailyForecastByCityCodeDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.dailyForecastByCityCode(CITY_CODE, COUNT);
		verify(owmWeatherProvider, times(1)).dailyForecastByCityCode(CITY_CODE, COUNT);
	}

	@Test
	public void testDailyForecastByCoordinatesDelegatesToOWMWeatherProvider() throws WeatherNotFoundException {
		subjectUnderTest.dailyForecastByCoordinates(LATITUDE, LONGITUDE, COUNT);
		verify(owmWeatherProvider, times(1)).dailyForecastByCoordinates(LATITUDE, LONGITUDE, COUNT);
	}

}

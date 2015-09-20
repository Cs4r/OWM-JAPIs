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
package net.aksingh.owmjapis.aceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.domain.CurrentWeather;
import net.aksingh.owmjapis.exception.WeatherNotFoundException;

/**
 * <p>
 * Tests the CurrentWeather's functionality.
 * </p>
 *
 * @author Ashutosh Kumar Singh
 * @author Cesar Aguilera
 * @version 2015/09/20
 * @since 2.5.0.3
 */
public class CurrentWeatherTest {

	private static final String LONDON_UK = "London, UK";
	private static final String EXPECTED_CITY_NAME = "London";
	private static final int EXPECTED_CITY_CODE = 2643743;

	@Test
	public void testCurrentWeatherByCityNameReturnsValidData() throws WeatherNotFoundException {
		OpenWeatherMap owm = new OpenWeatherMap("");
		CurrentWeather cw = owm.currentWeatherByCityName(LONDON_UK);

		boolean validResponse = cw.isValid();
		assertTrue(validResponse);

		if (!validResponse) {
			System.out.println("Reponse is inValid!");
		} else {
			System.out.println("Reponse is Valid!");
			System.out.println();

			cw.getBaseStation().ifPresent(baseStation -> {
				System.out.println("Base station: " + baseStation);
			});

			cw.getDateTime().ifPresent(dateTime -> {
				System.out.println("Date time: " + dateTime);
			});

			System.out.println();

			cw.getCityCode().ifPresent(cityCode -> {
				assertEquals(EXPECTED_CITY_CODE, cityCode.longValue());
				System.out.println("City code: " + cityCode);
			});

			cw.getCityName().ifPresent(cityName -> {
				assertEquals(EXPECTED_CITY_NAME, cityName);
				System.out.println("City name: " + cityName);
			});

			System.out.println();
		}
	}
}

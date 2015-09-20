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
package net.aksingh.owmjapis.service;

import java.io.UnsupportedEncodingException;

/**
 * Generates addresses for accessing the information from OWM.org
 *
 * @since 2.5.0.3
 */
public interface OWMAddress {

	/*
	Getters
	 */
	String getAppId();

	OWMUnits getUnits();

	String getMode();

	OWMLanguage getLang();
	
	/*
	 * Setters
	 */
	void setLang(OWMLanguage lang);

	void setAppId(String appId);

	void setUnits(OWMUnits units);

	/*
	Addresses for current weather
	 */
	String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException;

	String currentWeatherByCityName(String cityName, String countryCode) throws UnsupportedEncodingException;

	String currentWeatherByCityCode(long cityCode);

	String currentWeatherByCoordinates(float latitude, float longitude);

	/*
	Addresses for hourly forecasts
	 */
	String hourlyForecastByCityName(String cityName) throws UnsupportedEncodingException;

	String hourlyForecastByCityName(String cityName, String countryCode) throws UnsupportedEncodingException;

	String hourlyForecastByCityCode(long cityCode);

	String hourlyForecastByCoordinates(float latitude, float longitude);

	/*
	Addresses for daily forecasts
	 */
	String dailyForecastByCityName(String cityName, byte count) throws UnsupportedEncodingException;

	String dailyForecastByCityName(String cityName, String countryCode, byte count) throws UnsupportedEncodingException;

	String dailyForecastByCityCode(long cityCode, byte count);

	String dailyForecastByCoordinates(float latitude, float longitude, byte count);


}
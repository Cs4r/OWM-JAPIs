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
package net.aksingh.owmjapis.core;

import java.io.IOException;

import org.json.JSONException;

/**
 * Provides a set of methods to query weather information
 * 
 * @author cs4r
 *
 */
public interface WeatherProvider {

	CurrentWeather currentWeatherByCityName(String cityName) throws IOException, JSONException;

	CurrentWeather currentWeatherByCityName(String cityName, String countryCode) throws IOException, JSONException;

	CurrentWeather currentWeatherByCityCode(long cityCode) throws JSONException;

	CurrentWeather currentWeatherByCoordinates(float latitude, float longitude) throws JSONException;

	HourlyForecast hourlyForecastByCityName(String cityName) throws IOException, JSONException;

	HourlyForecast hourlyForecastByCityName(String cityName, String countryCode) throws IOException, JSONException;

	HourlyForecast hourlyForecastByCityCode(long cityCode) throws JSONException;

	HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude) throws JSONException;

	DailyForecast dailyForecastByCityName(String cityName, byte count) throws IOException, JSONException;

	DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws IOException, JSONException;

	DailyForecast dailyForecastByCityCode(long cityCode, byte count) throws JSONException;

	DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count) throws JSONException;

}
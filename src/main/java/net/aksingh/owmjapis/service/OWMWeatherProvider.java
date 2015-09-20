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

import net.aksingh.owmjapis.domain.CurrentWeather;
import net.aksingh.owmjapis.domain.DailyForecast;
import net.aksingh.owmjapis.domain.HourlyForecast;
import net.aksingh.owmjapis.exception.WeatherNotFoundException;

/**
 * Provides a set of methods to consult weather information with
 * http://openweathermap.org/
 * 
 * @author cs4r
 *
 */
public interface OWMWeatherProvider {

	CurrentWeather currentWeatherByCityName(String cityName) throws WeatherNotFoundException;

	CurrentWeather currentWeatherByCityName(String cityName, String countryCode) throws WeatherNotFoundException;

	CurrentWeather currentWeatherByCityCode(long cityCode) throws WeatherNotFoundException;

	CurrentWeather currentWeatherByCoordinates(float latitude, float longitude) throws WeatherNotFoundException;

	HourlyForecast hourlyForecastByCityName(String cityName) throws WeatherNotFoundException;

	HourlyForecast hourlyForecastByCityName(String cityName, String countryCode) throws WeatherNotFoundException;

	HourlyForecast hourlyForecastByCityCode(long cityCode) throws WeatherNotFoundException;

	HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude) throws WeatherNotFoundException;

	DailyForecast dailyForecastByCityName(String cityName, byte count) throws WeatherNotFoundException;

	DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws WeatherNotFoundException;

	DailyForecast dailyForecastByCityCode(long cityCode, byte count) throws WeatherNotFoundException;

	DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count)
			throws WeatherNotFoundException;
}
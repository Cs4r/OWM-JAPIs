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
package net.aksingh.owmjapis.service.impl;

import org.json.JSONObject;

import net.aksingh.owmjapis.domain.CurrentWeather;
import net.aksingh.owmjapis.domain.DailyForecast;
import net.aksingh.owmjapis.domain.HourlyForecast;
import net.aksingh.owmjapis.exception.WeatherNotFoundException;
import net.aksingh.owmjapis.service.OWMAddress;
import net.aksingh.owmjapis.service.OWMLanguage;
import net.aksingh.owmjapis.service.OWMProxy;
import net.aksingh.owmjapis.service.OWMUnits;
import net.aksingh.owmjapis.service.OWMWeatherProvider;
import net.aksingh.owmjapis.service.ProxyInfo;

/**
 * Implementation of {@link OWMWeatherProvider} for OpenWeatherMap API 2.5
 * 
 * @author cs4r
 *
 */
public class OWMWeatherProvider_V_2_5 implements OWMWeatherProvider {

	private static final String DAILY_FORECAST_EXCEPTION_MESSAGE = "Unable to retrieve daily forescast, check your method call";
	private static final String HOURLY_FORECAST_EXCEPTION_MESSAGE = "Unable to retrieve hourly forescast, check your method call";
	private static final String CURRENT_WEATHER_EXCEPTION_MESSAGE = "Unable to retrieve current weather, check your method call";
	private final OWMAddress owmAddress;
	private final OWMResponse owmResponse;
	private final OWMProxy owmProxy;

	public OWMWeatherProvider_V_2_5(OWMUnits units, OWMLanguage lang, String apiKey, ProxyInfo proxyInfo) {
		this.owmAddress = new OWMAddress_V_2_5(units, lang, apiKey);
		this.owmProxy = new OWMProxy(proxyInfo.ip, proxyInfo.port, proxyInfo.user, proxyInfo.pass);
		this.owmResponse = new OWMResponse(owmAddress, owmProxy);
	}

	public OWMWeatherProvider_V_2_5(OWMUnits units, OWMLanguage lang, String apiKey) {
		this.owmAddress = new OWMAddress_V_2_5(units, lang, apiKey);
		this.owmProxy = new OWMProxy(null, Integer.MIN_VALUE, null, null);
		this.owmResponse = new OWMResponse(owmAddress, owmProxy);
	}

	public CurrentWeather currentWeatherByCityName(String cityName) throws WeatherNotFoundException {
		try {
			String response = owmResponse.currentWeatherByCityName(cityName);
			return this.currentWeatherFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(CURRENT_WEATHER_EXCEPTION_MESSAGE);
		}
	}

	public CurrentWeather currentWeatherByCityName(String cityName, String countryCode)
			throws WeatherNotFoundException {
		try {
			String response = owmResponse.currentWeatherByCityName(cityName, countryCode);
			return this.currentWeatherFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(CURRENT_WEATHER_EXCEPTION_MESSAGE);
		}
	}

	public CurrentWeather currentWeatherByCityCode(long cityCode) throws WeatherNotFoundException {
		String response = owmResponse.currentWeatherByCityCode(cityCode);
		return this.currentWeatherFromRawResponse(response);
	}

	public CurrentWeather currentWeatherByCoordinates(float latitude, float longitude) throws WeatherNotFoundException {
		String response = owmResponse.currentWeatherByCoordinates(latitude, longitude);
		return this.currentWeatherFromRawResponse(response);
	}

	private CurrentWeather currentWeatherFromRawResponse(String response) throws WeatherNotFoundException {
		try {
			JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
			return new CurrentWeather(jsonObj);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(CURRENT_WEATHER_EXCEPTION_MESSAGE);
		}
	}

	public HourlyForecast hourlyForecastByCityName(String cityName) throws WeatherNotFoundException {
		try {
			String response = owmResponse.hourlyForecastByCityName(cityName);
			return this.hourlyForecastFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(HOURLY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

	public HourlyForecast hourlyForecastByCityName(String cityName, String countryCode)
			throws WeatherNotFoundException {
		try {
			String response = owmResponse.hourlyForecastByCityName(cityName, countryCode);
			return this.hourlyForecastFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(HOURLY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

	public HourlyForecast hourlyForecastByCityCode(long cityCode) throws WeatherNotFoundException {
		String response = owmResponse.hourlyForecastByCityCode(cityCode);
		return this.hourlyForecastFromRawResponse(response);
	}

	public HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude) throws WeatherNotFoundException {
		String response = owmResponse.hourlyForecastByCoordinates(latitude, longitude);
		return this.hourlyForecastFromRawResponse(response);
	}

	private HourlyForecast hourlyForecastFromRawResponse(String response) throws WeatherNotFoundException {
		try {
			JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
			return new HourlyForecast(jsonObj);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(HOURLY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

	public DailyForecast dailyForecastByCityName(String cityName, byte count) throws WeatherNotFoundException {
		try {
			String response = owmResponse.dailyForecastByCityName(cityName, count);
			return this.dailyForecastFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(DAILY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

	public DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws WeatherNotFoundException {
		try {
			String response = owmResponse.dailyForecastByCityName(cityName, countryCode, count);
			return this.dailyForecastFromRawResponse(response);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(DAILY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

	public DailyForecast dailyForecastByCityCode(long cityCode, byte count) throws WeatherNotFoundException {
		String response = owmResponse.dailyForecastByCityCode(cityCode, count);
		return this.dailyForecastFromRawResponse(response);
	}

	public DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count)
			throws WeatherNotFoundException {
		String response = owmResponse.dailyForecastByCoordinates(latitude, longitude, count);
		return this.dailyForecastFromRawResponse(response);
	}

	private DailyForecast dailyForecastFromRawResponse(String response) throws WeatherNotFoundException {
		try {
			JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
			return new DailyForecast(jsonObj);
		} catch (Exception exception) {
			throw new WeatherNotFoundException(DAILY_FORECAST_EXCEPTION_MESSAGE);
		}
	}

}

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
package net.aksingh.owmjapis.core.impl;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import net.aksingh.owmjapis.core.CurrentWeather;
import net.aksingh.owmjapis.core.DailyForecast;
import net.aksingh.owmjapis.core.HourlyForecast;
import net.aksingh.owmjapis.core.OWMAddress;
import net.aksingh.owmjapis.core.OWMLanguage;
import net.aksingh.owmjapis.core.OWMProxy;
import net.aksingh.owmjapis.core.OWMResponse;
import net.aksingh.owmjapis.core.OWMUnits;
import net.aksingh.owmjapis.core.ProxyInfo;
import net.aksingh.owmjapis.core.WeatherProvider;

/**
 * Default implementation of {@link WeatherProvider}
 * 
 * @author cs4r
 *
 */
public class WeatherProviderImpl implements WeatherProvider {

	private final OWMAddress owmAddress;
	private final OWMResponse owmResponse;
	private final OWMProxy owmProxy;

	public WeatherProviderImpl(OWMUnits units, OWMLanguage lang, String apiKey, ProxyInfo proxyInfo) {
		this.owmAddress = new OWMAddress_V_2_5(units, lang, apiKey);
		this.owmProxy = new OWMProxy(proxyInfo.ip, proxyInfo.port, proxyInfo.user, proxyInfo.pass);
		this.owmResponse = new OWMResponse(owmAddress, owmProxy);
	}

	public WeatherProviderImpl(OWMUnits units, OWMLanguage lang, String apiKey) {
		this.owmAddress = new OWMAddress_V_2_5(units, lang, apiKey);
		this.owmProxy = new OWMProxy(null, Integer.MIN_VALUE, null, null);
		this.owmResponse = new OWMResponse(owmAddress, owmProxy);
	}

	public CurrentWeather currentWeatherByCityName(String cityName) throws IOException, JSONException {
		String response = owmResponse.currentWeatherByCityName(cityName);
		return this.currentWeatherFromRawResponse(response);
	}

	public CurrentWeather currentWeatherByCityName(String cityName, String countryCode)
			throws IOException, JSONException {
		String response = owmResponse.currentWeatherByCityName(cityName, countryCode);
		return this.currentWeatherFromRawResponse(response);
	}

	public CurrentWeather currentWeatherByCityCode(long cityCode) throws JSONException {
		String response = owmResponse.currentWeatherByCityCode(cityCode);
		return this.currentWeatherFromRawResponse(response);
	}

	public CurrentWeather currentWeatherByCoordinates(float latitude, float longitude) throws JSONException {
		String response = owmResponse.currentWeatherByCoordinates(latitude, longitude);
		return this.currentWeatherFromRawResponse(response);
	}

	private CurrentWeather currentWeatherFromRawResponse(String response) throws JSONException {
		JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
		return new CurrentWeather(jsonObj);
	}

	public HourlyForecast hourlyForecastByCityName(String cityName) throws IOException, JSONException {
		String response = owmResponse.hourlyForecastByCityName(cityName);
		return this.hourlyForecastFromRawResponse(response);
	}

	public HourlyForecast hourlyForecastByCityName(String cityName, String countryCode)
			throws IOException, JSONException {
		String response = owmResponse.hourlyForecastByCityName(cityName, countryCode);
		return this.hourlyForecastFromRawResponse(response);
	}

	public HourlyForecast hourlyForecastByCityCode(long cityCode) throws JSONException {
		String response = owmResponse.hourlyForecastByCityCode(cityCode);
		return this.hourlyForecastFromRawResponse(response);
	}

	public HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude) throws JSONException {
		String response = owmResponse.hourlyForecastByCoordinates(latitude, longitude);
		return this.hourlyForecastFromRawResponse(response);
	}

	private HourlyForecast hourlyForecastFromRawResponse(String response) throws JSONException {
		JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
		return new HourlyForecast(jsonObj);
	}

	public DailyForecast dailyForecastByCityName(String cityName, byte count) throws IOException, JSONException {
		String response = owmResponse.dailyForecastByCityName(cityName, count);
		return this.dailyForecastFromRawResponse(response);
	}

	public DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws IOException, JSONException {
		String response = owmResponse.dailyForecastByCityName(cityName, countryCode, count);
		return this.dailyForecastFromRawResponse(response);
	}

	public DailyForecast dailyForecastByCityCode(long cityCode, byte count) throws JSONException {
		String response = owmResponse.dailyForecastByCityCode(cityCode, count);
		return this.dailyForecastFromRawResponse(response);
	}

	public DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count) throws JSONException {
		String response = owmResponse.dailyForecastByCoordinates(latitude, longitude, count);
		return this.dailyForecastFromRawResponse(response);
	}

	private DailyForecast dailyForecastFromRawResponse(String response) throws JSONException {
		JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
		return new DailyForecast(jsonObj);
	}

}

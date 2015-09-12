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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.aksingh.owmjapis.service.OWMAddress;
import net.aksingh.owmjapis.service.OWMLanguage;
import net.aksingh.owmjapis.service.OWMUnits;

/**
 * Implementation of {@link OWMAddress} for OpenWeatherMap API 2.5
 *
 * @since 2.5.0.3
 */
public class OWMAddress_V_2_5 implements OWMAddress {
	/*
	 * URLs and parameters for OWM.org
	 */
	private static final String MODE = "json";
	private static final String ENCODING = "UTF-8";
	private static final String URL_API = "http://api.openweathermap.org/data/2.5/";
	private static final String URL_CURRENT = "weather?";
	private static final String URL_HOURLY_FORECAST = "forecast?";
	private static final String URL_DAILY_FORECAST = "forecast/daily?";
	private static final String PARAM_COUNT = "cnt=";
	private static final String PARAM_CITY_NAME = "q=";
	private static final String PARAM_CITY_ID = "id=";
	private static final String PARAM_LATITUDE = "lat=";
	private static final String PARAM_LONGITUDE = "lon=";
	private static final String PARAM_MODE = "mode=";
	private static final String PARAM_UNITS = "units=";
	private static final String PARAM_APPID = "appId=";
	private static final String PARAM_LANG = "lang=";

	private String mode;
	private OWMUnits units;
	private String appId;
	private OWMLanguage lang;

	/*
	 * Constructors
	 */
	private OWMAddress_V_2_5(String appId) {
		this(OWMUnits.IMPERIAL, OWMLanguage.ENGLISH, appId);
	}

	private OWMAddress_V_2_5(OWMUnits units, String appId) {
		this(units, OWMLanguage.ENGLISH, appId);
	}

	public OWMAddress_V_2_5(OWMUnits units, OWMLanguage lang, String appId) {
		this.mode = MODE;
		this.units = units;
		this.lang = lang;
		this.appId = appId;
	}

	public String getAppId() {
		return this.appId;
	}

	public OWMUnits getUnits() {
		return this.units;
	}

	public String getMode() {
		return this.mode;
	}

	public OWMLanguage getLang() {
		return this.lang;
	}

	public void setUnits(OWMUnits units) {
		this.units = units;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public void setLang(OWMLanguage lang) {
		this.lang = lang;
	}

	public String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
		return new StringBuilder().append(URL_API).append(URL_CURRENT).append(PARAM_CITY_NAME)
				.append(URLEncoder.encode(cityName, ENCODING)).append("&").append(PARAM_MODE).append(this.mode)
				.append("&").append(PARAM_UNITS).append(this.units.getCode()).append("&").append(PARAM_LANG)
				.append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String currentWeatherByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
		return currentWeatherByCityName(
				new StringBuilder().append(cityName).append(",").append(countryCode).toString());
	}

	public String currentWeatherByCityCode(long cityCode) {
		return new StringBuilder().append(URL_API).append(URL_CURRENT).append(PARAM_CITY_ID)
				.append(Long.toString(cityCode)).append("&").append(PARAM_MODE).append(this.mode).append("&")
				.append(PARAM_UNITS).append(this.units.getCode()).append("&").append(PARAM_LANG)
				.append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String currentWeatherByCoordinates(float latitude, float longitude) {
		return new StringBuilder().append(URL_API).append(URL_CURRENT).append(PARAM_LATITUDE)
				.append(Float.toString(latitude)).append("&").append(PARAM_LONGITUDE).append(Float.toString(longitude))
				.append("&").append(PARAM_MODE).append(this.mode).append("&").append(PARAM_UNITS)
				.append(this.units.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String hourlyForecastByCityName(String cityName) throws UnsupportedEncodingException {
		return new StringBuilder().append(URL_API).append(URL_HOURLY_FORECAST).append(PARAM_CITY_NAME)
				.append(URLEncoder.encode(cityName, ENCODING)).append("&").append(PARAM_MODE).append(this.mode)
				.append("&").append(PARAM_UNITS).append(this.units.getCode()).append("&").append(PARAM_LANG)
				.append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String hourlyForecastByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
		return hourlyForecastByCityName(
				new StringBuilder().append(cityName).append(",").append(countryCode).toString());
	}

	public String hourlyForecastByCityCode(long cityCode) {
		return new StringBuilder().append(URL_API).append(URL_HOURLY_FORECAST).append(PARAM_CITY_ID)
				.append(Long.toString(cityCode)).append("&").append(PARAM_MODE).append(this.mode).append("&")
				.append(PARAM_UNITS).append(this.units.getCode()).append("&").append(PARAM_LANG)
				.append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String hourlyForecastByCoordinates(float latitude, float longitude) {
		return new StringBuilder().append(URL_API).append(URL_HOURLY_FORECAST).append(PARAM_LATITUDE)
				.append(Float.toString(latitude)).append("&").append(PARAM_LONGITUDE).append(Float.toString(longitude))
				.append("&").append(PARAM_MODE).append(this.mode).append("&").append(PARAM_UNITS)
				.append(this.units.getCode()).append("&").append(PARAM_LANG).append(this.lang.getCode()).append("&")
				.append(PARAM_APPID).append(this.appId).toString();
	}

	public String dailyForecastByCityName(String cityName, byte count) throws UnsupportedEncodingException {
		return new StringBuilder().append(URL_API).append(URL_DAILY_FORECAST).append(PARAM_CITY_NAME)
				.append(URLEncoder.encode(cityName, ENCODING)).append("&").append(PARAM_COUNT)
				.append(Byte.toString(count)).append("&").append(PARAM_MODE).append(this.mode).append("&")
				.append(PARAM_UNITS).append(this.units.getCode()).append("&").append(PARAM_LANG)
				.append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId).toString();
	}

	public String dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws UnsupportedEncodingException {
		return dailyForecastByCityName(new StringBuilder().append(cityName).append(",").append(countryCode).toString(),
				count);
	}

	public String dailyForecastByCityCode(long cityCode, byte count) {
		return new StringBuilder().append(URL_API).append(URL_DAILY_FORECAST).append(PARAM_CITY_ID)
				.append(Long.toString(cityCode)).append("&").append(PARAM_COUNT).append(Byte.toString(count))
				.append("&").append(PARAM_MODE).append(this.mode).append("&").append(PARAM_UNITS)
				.append(this.units.getCode()).append("&").append(PARAM_LANG).append(this.lang.getCode()).append("&")
				.append(PARAM_APPID).append(this.appId).toString();
	}

	public String dailyForecastByCoordinates(float latitude, float longitude, byte count) {
		return new StringBuilder().append(URL_API).append(URL_DAILY_FORECAST).append(PARAM_LATITUDE)
				.append(Float.toString(latitude)).append("&").append(PARAM_LONGITUDE).append(Float.toString(longitude))
				.append("&").append(PARAM_COUNT).append(Byte.toString(count)).append("&").append(PARAM_MODE)
				.append(this.mode).append("&").append(PARAM_UNITS).append(this.units.getCode()).append("&")
				.append(PARAM_LANG).append(this.lang.getCode()).append("&").append(PARAM_APPID).append(this.appId)
				.toString();
	}
}
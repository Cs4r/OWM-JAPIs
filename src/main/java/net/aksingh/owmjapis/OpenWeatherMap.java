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

package net.aksingh.owmjapis;

import net.aksingh.owmjapis.domain.CurrentWeather;
import net.aksingh.owmjapis.domain.DailyForecast;
import net.aksingh.owmjapis.domain.HourlyForecast;
import net.aksingh.owmjapis.exception.WeatherNotFoundException;
import net.aksingh.owmjapis.service.OWMLanguage;
import net.aksingh.owmjapis.service.OWMUnits;
import net.aksingh.owmjapis.service.OWMWeatherProvider;
import net.aksingh.owmjapis.service.ProxyInfo;
import net.aksingh.owmjapis.service.impl.OWMWeatherProvider_V_2_5;

/**
 * <p>
 * <b>The starting point for all API operations.</b> If you're new to this API,
 * read the docs for this class first.
 * </p>
 * <p>
 * Lets you access data from OpenWeatherMap.org using its Weather APIs.
 * Henceforth, it's shortened as OWM.org to ease commenting.
 * </p>
 * <p>
 * <b>Sample code:</b><br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap("your-api-key");</code><br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap(your-units, "your-api-key");</code>
 * <br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap(your-units, your-language, "your-api-key");</code>
 * </p>
 *
 * @author Ashutosh Kumar Singh <me@aksingh.net>
 * @author Cesar Aguilera
 * @version 2015-09-20
 * @see <a href="http://openweathermap.org/">OpenWeatherMap.org</a>
 * @see <a href="http://openweathermap.org/api">OpenWeatherMap.org API</a>
 * @since 2.5.0.1
 */
public final class OpenWeatherMap {
	private OWMWeatherProvider omwWeatherProvider;
	private final OWMUnits units;
	private final OWMLanguage lang;
	private final String apiKey;

	/**
	 * Constructor
	 *
	 * @param apiKey
	 *            API key from OWM.org
	 * @see <a href="http://openweathermap.org/appid">OWM.org API Key</a>
	 */
	public OpenWeatherMap(String apiKey) {
		this(OWMUnits.METRIC, OWMLanguage.ENGLISH, apiKey);
	}

	/**
	 * Constructor
	 *
	 * @param units
	 *            Any constant from Units
	 * @param apiKey
	 *            API key from OWM.org
	 * @see net.aksingh.owmjapis.service.OWMUnits
	 * @see <a href="http://openweathermap.org/appid">OWM.org API Key</a>
	 */
	public OpenWeatherMap(OWMUnits units, String apiKey) {
		this(units, OWMLanguage.ENGLISH, apiKey);
	}

	/**
	 * Constructor
	 *
	 * @param units
	 *            Any constant from Units
	 * @param lang
	 *            Any constant from Language
	 * @param apiKey
	 *            API key from OWM.org
	 * @see net.aksingh.owmjapis.service.OWMUnits
	 * @see net.aksingh.owmjapis.service.OWMLanguage
	 * @see <a href="http://openweathermap.org/current#multi">OWM.org's
	 *      Multilingual support</a>
	 * @see <a href="http://openweathermap.org/appid">OWM.org's API Key</a>
	 */
	public OpenWeatherMap(OWMUnits units, OWMLanguage lang, String apiKey) {
		this.omwWeatherProvider = new OWMWeatherProvider_V_2_5(units, lang, apiKey);
		this.units = units;
		this.lang = lang;
		this.apiKey = apiKey;
	}

	/**
	 * Constructor
	 *
	 * @param units
	 *            Any constant from Units
	 * @param lang
	 *            Any constant from Language
	 * @param apiKey
	 *            API key from OWM.org
	 * @param proxyInfo
	 *            Proxy and authentication details for getting data from OWM.org
	 * @see net.aksingh.owmjapis.service.OWMUnits
	 * @see net.aksingh.owmjapis.service.OWMLanguage
	 * @see <a href="http://openweathermap.org/current#multi">OWM.org's
	 *      Multilingual support</a>
	 * @see <a href="http://openweathermap.org/appid">OWM.org's API Key</a>
	 */
	public OpenWeatherMap(OWMUnits units, OWMLanguage lang, String apiKey, ProxyInfo proxyInfo) {
		this.omwWeatherProvider = new OWMWeatherProvider_V_2_5(units, lang, apiKey, proxyInfo);
		this.units = units;
		this.lang = lang;
		this.apiKey = apiKey;
	}

	public String getApiKey() {
		return apiKey;
	}

	public OWMUnits getUnits() {
		return units;
	}

	public OWMLanguage getLang() {
		return lang;
	}

	public CurrentWeather currentWeatherByCityName(String cityName) throws WeatherNotFoundException {
		return omwWeatherProvider.currentWeatherByCityName(cityName);
	}

	public CurrentWeather currentWeatherByCityName(String cityName, String countryCode)
			throws WeatherNotFoundException {
		return omwWeatherProvider.currentWeatherByCityName(cityName, countryCode);
	}

	public CurrentWeather currentWeatherByCityCode(long cityCode) throws WeatherNotFoundException {
		return omwWeatherProvider.currentWeatherByCityCode(cityCode);
	}

	public CurrentWeather currentWeatherByCoordinates(float latitude, float longitude) throws WeatherNotFoundException {
		return omwWeatherProvider.currentWeatherByCoordinates(latitude, longitude);
	}

	public HourlyForecast hourlyForecastByCityName(String cityName) throws WeatherNotFoundException {
		return omwWeatherProvider.hourlyForecastByCityName(cityName);
	}

	public HourlyForecast hourlyForecastByCityName(String cityName, String countryCode)
			throws WeatherNotFoundException {
		return omwWeatherProvider.hourlyForecastByCityName(cityName, countryCode);
	}

	public HourlyForecast hourlyForecastByCityCode(long cityCode) throws WeatherNotFoundException {
		return omwWeatherProvider.hourlyForecastByCityCode(cityCode);
	}

	public HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude) throws WeatherNotFoundException {
		return omwWeatherProvider.hourlyForecastByCoordinates(latitude, longitude);
	}

	public DailyForecast dailyForecastByCityName(String cityName, byte count) throws WeatherNotFoundException {
		return omwWeatherProvider.dailyForecastByCityName(cityName, count);
	}

	public DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws WeatherNotFoundException {
		return omwWeatherProvider.dailyForecastByCityName(cityName, countryCode, count);
	}

	public DailyForecast dailyForecastByCityCode(long cityCode, byte count) throws WeatherNotFoundException {
		return omwWeatherProvider.dailyForecastByCityCode(cityCode, count);
	}

	public DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count)
			throws WeatherNotFoundException {
		return omwWeatherProvider.dailyForecastByCoordinates(latitude, longitude, count);
	}

	/* Method used only for testing purposes */
	protected void setOWMWeatherProvider(final OWMWeatherProvider owmWeatherProvider) {
		this.omwWeatherProvider = owmWeatherProvider;
	}
}

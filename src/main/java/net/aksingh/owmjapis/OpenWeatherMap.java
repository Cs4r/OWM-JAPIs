/*
 * Copyright (c) 2013-2015 Ashutosh Kumar Singh <me@aksingh.net>
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

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import net.aksingh.owmjapis.core.OWMAddress;
import net.aksingh.owmjapis.core.OWMProxy;
import net.aksingh.owmjapis.core.OWMResponse;

/**
 * <p>
 *     <b>The starting point for all API operations.</b>
 *     If you're new to this API, read the docs for this class first.
 * </p>
 * <p>
 * Lets you access data from OpenWeatherMap.org using its Weather APIs.
 * Henceforth, it's shortened as OWM.org to ease commenting.
 * </p>
 * <p>
 * <b>Sample code:</b><br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap("your-api-key");</code><br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap(your-units, "your-api-key");</code><br>
 * <code>OpenWeatherMap.org owm = new OpenWeatherMap(your-units, your-language, "your-api-key");</code>
 * </p>
 *
 * @author Ashutosh Kumar Singh <me@aksingh.net>
 * @version 2015-01-17
 * @see <a href="http://openweathermap.org/">OpenWeatherMap.org</a>
 * @see <a href="http://openweathermap.org/api">OpenWeatherMap.org API</a>
 * @since 2.5.0.1
 */
public class OpenWeatherMap {
    /*
    URLs and parameters for OWM.org
     */
    public static final String URL_API = "http://api.openweathermap.org/data/2.5/";
    public static final String URL_CURRENT = "weather?";
    public static final String URL_HOURLY_FORECAST = "forecast?";
    public static final String URL_DAILY_FORECAST = "forecast/daily?";

    public static final String PARAM_COUNT = "cnt=";
    public static final String PARAM_CITY_NAME = "q=";
    public static final String PARAM_CITY_ID = "id=";
    public static final String PARAM_LATITUDE = "lat=";
    public static final String PARAM_LONGITUDE = "lon=";
    public static final String PARAM_MODE = "mode=";
    public static final String PARAM_UNITS = "units=";
    public static final String PARAM_APPID = "appId=";
    public static final String PARAM_LANG = "lang=";

    /*
    Instance Variables
     */
    private final OWMAddress owmAddress;
    private final OWMResponse owmResponse;
    private final OWMProxy owmProxy;

    /**
     * Constructor
     *
     * @param apiKey API key from OWM.org
     * @see <a href="http://openweathermap.org/appid">OWM.org API Key</a>
     */
    public OpenWeatherMap(String apiKey) {
        this(Units.IMPERIAL, Language.ENGLISH, apiKey);
    }

    /**
     * Constructor
     *
     * @param units  Any constant from Units
     * @param apiKey API key from OWM.org
     * @see net.aksingh.owmjapis.OpenWeatherMap.Units
     * @see <a href="http://openweathermap.org/appid">OWM.org API Key</a>
     */
    public OpenWeatherMap(Units units, String apiKey) {
        this(units, Language.ENGLISH, apiKey);
    }

    /**
     * Constructor
     *
     * @param units  Any constant from Units
     * @param lang   Any constant from Language
     * @param apiKey API key from OWM.org
     * @see net.aksingh.owmjapis.OpenWeatherMap.Units
     * @see net.aksingh.owmjapis.OpenWeatherMap.Language
     * @see <a href="http://openweathermap.org/current#multi">OWM.org's Multilingual support</a>
     * @see <a href="http://openweathermap.org/appid">OWM.org's API Key</a>
     */
    public OpenWeatherMap(Units units, Language lang, String apiKey) {
        this.owmAddress = new OWMAddress(units, lang, apiKey);
        this.owmProxy = new OWMProxy(null, Integer.MIN_VALUE, null, null);
        this.owmResponse = new OWMResponse(owmAddress, owmProxy);
    }

    /*
    Getters
     */
    public OWMAddress getOwmAddressInstance() {
        return owmAddress;
    }

    public String getApiKey() {
        return owmAddress.getAppId();
    }

    public Units getUnits() {
        return owmAddress.getUnits();
    }

    public String getMode() {
        return owmAddress.getMode();
    }

    public Language getLang() {
        return owmAddress.getLang();
    }

    /*
    Setters
     */

    /**
     * Set units for getting data from OWM.org
     *
     * @param units Any constant from Units
     * @see net.aksingh.owmjapis.OpenWeatherMap.Units
     */
    public void setUnits(Units units) {
        owmAddress.setUnits(units);
    }

    /**
     * Set API key for getting data from OWM.org
     *
     * @param appId API key from OWM.org
     * @see <a href="http://openweathermap.org/appid">OWM.org's API Key</a>
     */
    public void setApiKey(String appId) {
        owmAddress.setAppId(appId);
    }

    /**
     * Set language for getting data from OWM.org
     *
     * @param lang Any constant from Language
     * @see net.aksingh.owmjapis.OpenWeatherMap.Language
     * @see <a href="http://openweathermap.org/current#multi">OWM.org's Multilingual support</a>
     */
    public void setLang(Language lang) {
        owmAddress.setLang(lang);
    }

    /**
     * Set proxy for getting data from OWM.org
     *
     * @param ip IP address of the proxy
     * @param port Port address of the proxy
     */
    public void setProxy(String ip, int port) {
        owmProxy.setIp(ip);
        owmProxy.setPort(port);
        owmProxy.setUser(null);
        owmProxy.setPass(null);
    }

    /**
     * Set proxy and authentication details for getting data from OWM.org
     *
     * @param ip IP address of the proxy
     * @param port Port address of the proxy
     * @param user User name for the proxy if required
     * @param pass Password for the proxy if required
     */
    public void setProxy(String ip, int port, String user, String pass) {
        owmProxy.setIp(ip);
        owmProxy.setPort(port);
        owmProxy.setUser(user);
        owmProxy.setPass(pass);
    }

    public CurrentWeather currentWeatherByCityName(String cityName)
            throws IOException, JSONException {
        String response = owmResponse.currentWeatherByCityName(cityName);
        return this.currentWeatherFromRawResponse(response);
    }

    public CurrentWeather currentWeatherByCityName(String cityName, String countryCode)
            throws IOException, JSONException {
        String response = owmResponse.currentWeatherByCityName(cityName, countryCode);
        return this.currentWeatherFromRawResponse(response);
    }

    public CurrentWeather currentWeatherByCityCode(long cityCode)
            throws JSONException {
        String response = owmResponse.currentWeatherByCityCode(cityCode);
        return this.currentWeatherFromRawResponse(response);
    }

    public CurrentWeather currentWeatherByCoordinates(float latitude, float longitude)
            throws JSONException {
        String response = owmResponse.currentWeatherByCoordinates(latitude, longitude);
        return this.currentWeatherFromRawResponse(response);
    }

    public CurrentWeather currentWeatherFromRawResponse(String response)
            throws JSONException {
        JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
        return new CurrentWeather(jsonObj);
    }

    public HourlyForecast hourlyForecastByCityName(String cityName)
            throws IOException, JSONException {
        String response = owmResponse.hourlyForecastByCityName(cityName);
        return this.hourlyForecastFromRawResponse(response);
    }

    public HourlyForecast hourlyForecastByCityName(String cityName, String countryCode)
            throws IOException, JSONException {
        String response = owmResponse.hourlyForecastByCityName(cityName, countryCode);
        return this.hourlyForecastFromRawResponse(response);
    }

    public HourlyForecast hourlyForecastByCityCode(long cityCode)
            throws JSONException {
        String response = owmResponse.hourlyForecastByCityCode(cityCode);
        return this.hourlyForecastFromRawResponse(response);
    }

    public HourlyForecast hourlyForecastByCoordinates(float latitude, float longitude)
            throws JSONException {
        String response = owmResponse.hourlyForecastByCoordinates(latitude, longitude);
        return this.hourlyForecastFromRawResponse(response);
    }

    public HourlyForecast hourlyForecastFromRawResponse(String response)
            throws JSONException {
        JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
        return new HourlyForecast(jsonObj);
    }

    public DailyForecast dailyForecastByCityName(String cityName, byte count)
            throws IOException, JSONException {
        String response = owmResponse.dailyForecastByCityName(cityName, count);
        return this.dailyForecastFromRawResponse(response);
    }

    public DailyForecast dailyForecastByCityName(String cityName, String countryCode, byte count)
            throws IOException, JSONException {
        String response = owmResponse.dailyForecastByCityName(cityName, countryCode, count);
        return this.dailyForecastFromRawResponse(response);
    }

    public DailyForecast dailyForecastByCityCode(long cityCode, byte count)
            throws JSONException {
        String response = owmResponse.dailyForecastByCityCode(cityCode, count);
        return this.dailyForecastFromRawResponse(response);
    }

    public DailyForecast dailyForecastByCoordinates(float latitude, float longitude, byte count)
            throws JSONException {
        String response = owmResponse.dailyForecastByCoordinates(latitude, longitude, count);
        return this.dailyForecastFromRawResponse(response);
    }

    public DailyForecast dailyForecastFromRawResponse(String response)
            throws JSONException {
        JSONObject jsonObj = (response != null) ? new JSONObject(response) : null;
        return new DailyForecast(jsonObj);
    }

    /**
     * Units that can be set for getting data from OWM.org
     *
     * @since 2.5.0.3
     */
    public static enum Units {
        METRIC("metric"),
        IMPERIAL("imperial");

        private final String unit;

        Units(String unit) {
            this.unit = unit;
        }
    }

    /**
     * Languages that can be set for getting data from OWM.org
     *
     * @since 2.5.0.3
     */
    public static enum Language {
        ENGLISH("en"),
        RUSSIAN("ru"),
        ITALIAN("it"),
        SPANISH("es"),
        UKRAINIAN("uk"),
        GERMAN("de"),
        PORTUGUESE("pt"),
        ROMANIAN("ro"),
        POLISH("pl"),
        FINNISH("fi"),
        DUTCH("nl"),
        FRENCH("FR"),
        BULGARIAN("bg"),
        SWEDISH("sv"),
        CHINESE_TRADITIONAL("zh_tw"),
        CHINESE_SIMPLIFIED("zh"),
        TURKISH("tr"),
        CROATIAN("hr"),
        CATALAN("ca");

        private final String lang;

        Language(String lang) {
            this.lang = lang;
        }
        
        public String getCode(){
        	return lang;
        }
    }
}

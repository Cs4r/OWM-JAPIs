package net.aksingh.owmjapis.core;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import net.aksingh.owmjapis.OpenWeatherMap;
import net.aksingh.owmjapis.OpenWeatherMap.Language;
import net.aksingh.owmjapis.OpenWeatherMap.Units;

/**
 * Generates addresses for accessing the information from OWM.org
 *
 * @since 2.5.0.3
 */
public class OWMAddress {
    private static final String MODE = "json";
    private static final String ENCODING = "UTF-8";

    private String mode;
    private Units units;
    private String appId;
    private Language lang;

    /*
    Constructors
     */
    private OWMAddress(String appId) {
        this(Units.IMPERIAL, Language.ENGLISH, appId);
    }

    private OWMAddress(Units units, String appId) {
        this(units, Language.ENGLISH, appId);
    }

    public OWMAddress(Units units, Language lang, String appId) {
        this.mode = MODE;
        this.units = units;
        this.lang = lang;
        this.appId = appId;
    }

    /*
    Getters
     */
    public String getAppId() {
        return this.appId;
    }

    public Units getUnits() {
        return this.units;
    }

    public String getMode() {
        return this.mode;
    }

    public Language getLang() {
        return this.lang;
    }

    /*
    Setters
     */
    public void setUnits(Units units) {
        this.units = units;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setLang(Language lang) {
        this.lang = lang;
    }

    /*
    Addresses for current weather
     */
    public String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_CURRENT)
                .append(OpenWeatherMap.PARAM_CITY_NAME).append(URLEncoder.encode(cityName, ENCODING)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String currentWeatherByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
        return currentWeatherByCityName(new StringBuilder()
                .append(cityName).append(",").append(countryCode)
                .toString());
    }

    public String currentWeatherByCityCode(long cityCode) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_CURRENT)
                .append(OpenWeatherMap.PARAM_CITY_ID).append(Long.toString(cityCode)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String currentWeatherByCoordinates(float latitude, float longitude) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_CURRENT)
                .append(OpenWeatherMap.PARAM_LATITUDE).append(Float.toString(latitude)).append("&")
                .append(OpenWeatherMap.PARAM_LONGITUDE).append(Float.toString(longitude)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    /*
    Addresses for hourly forecasts
     */
    public String hourlyForecastByCityName(String cityName) throws UnsupportedEncodingException {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_HOURLY_FORECAST)
                .append(OpenWeatherMap.PARAM_CITY_NAME).append(URLEncoder.encode(cityName, ENCODING)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String hourlyForecastByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
        return hourlyForecastByCityName(new StringBuilder()
                .append(cityName).append(",").append(countryCode)
                .toString());
    }

    public String hourlyForecastByCityCode(long cityCode) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_HOURLY_FORECAST)
                .append(OpenWeatherMap.PARAM_CITY_ID).append(Long.toString(cityCode)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String hourlyForecastByCoordinates(float latitude, float longitude) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_HOURLY_FORECAST)
                .append(OpenWeatherMap.PARAM_LATITUDE).append(Float.toString(latitude)).append("&")
                .append(OpenWeatherMap.PARAM_LONGITUDE).append(Float.toString(longitude)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    /*
    Addresses for daily forecasts
     */
    public String dailyForecastByCityName(String cityName, byte count) throws UnsupportedEncodingException {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_DAILY_FORECAST)
                .append(OpenWeatherMap.PARAM_CITY_NAME).append(URLEncoder.encode(cityName, ENCODING)).append("&")
                .append(OpenWeatherMap.PARAM_COUNT).append(Byte.toString(count)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String dailyForecastByCityName(String cityName, String countryCode, byte count) throws UnsupportedEncodingException {
        return dailyForecastByCityName(new StringBuilder()
                .append(cityName).append(",").append(countryCode)
                .toString(), count);
    }

    public String dailyForecastByCityCode(long cityCode, byte count) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_DAILY_FORECAST)
                .append(OpenWeatherMap.PARAM_CITY_ID).append(Long.toString(cityCode)).append("&")
                .append(OpenWeatherMap.PARAM_COUNT).append(Byte.toString(count)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }

    public String dailyForecastByCoordinates(float latitude, float longitude, byte count) {
        return new StringBuilder()
                .append(OpenWeatherMap.URL_API).append(OpenWeatherMap.URL_DAILY_FORECAST)
                .append(OpenWeatherMap.PARAM_LATITUDE).append(Float.toString(latitude)).append("&")
                .append(OpenWeatherMap.PARAM_LONGITUDE).append(Float.toString(longitude)).append("&")
                .append(OpenWeatherMap.PARAM_COUNT).append(Byte.toString(count)).append("&")
                .append(OpenWeatherMap.PARAM_MODE).append(this.mode).append("&")
                .append(OpenWeatherMap.PARAM_UNITS).append(this.units).append("&")
                .append(OpenWeatherMap.PARAM_LANG).append(this.lang.getCode()).append("&")
                .append(OpenWeatherMap.PARAM_APPID).append(this.appId)
                .toString();
    }
}
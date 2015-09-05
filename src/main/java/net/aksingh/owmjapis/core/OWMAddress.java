package net.aksingh.owmjapis.core;

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
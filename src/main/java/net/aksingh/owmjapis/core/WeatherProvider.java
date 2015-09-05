package net.aksingh.owmjapis.core;

import java.io.IOException;

import org.json.JSONException;

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
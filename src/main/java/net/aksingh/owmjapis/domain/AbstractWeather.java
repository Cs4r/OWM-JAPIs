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

package net.aksingh.owmjapis.domain;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * Provides default behavior and implementations for:
 * <ol>
 * <li>{@link net.aksingh.owmjapis.domain.CurrentWeather}</li>
 * 
 * </ol>
 * It defines common methods like <code>get</code> and some others.
 * </p>
 *
 * @author Ashutosh Kumar Singh
 * @author Cesar Aguilera
 * @version 2015/09/20
 * @since 2.5.0.1
 */
public abstract class AbstractWeather extends AbstractResponse {
	/*
	 * JSON Keys
	 */
	protected static final String JSON_CLOUDS = "clouds";
	protected static final String JSON_COORD = "coord";
	protected static final String JSON_MAIN = "main";
	protected static final String JSON_WIND = "wind";
	private static final String JSON_WEATHER = "weather";
	private static final String JSON_DATE_TIME = "dt";

	/*
	 * Instance variables
	 */
	private final Date dateTime;

	private final int weatherCount;
	private final List<Weather> weatherList;

	/*
	 * Constructors
	 */
	AbstractWeather() {
		super();

		this.weatherCount = 0;
		this.weatherList = null;
		this.dateTime = null;
	}

	AbstractWeather(JSONObject jsonObj) {
		super(jsonObj);

		long sec = (jsonObj != null) ? jsonObj.optLong(JSON_DATE_TIME, Long.MIN_VALUE) : Long.MIN_VALUE;
		if (sec != Long.MIN_VALUE) { // converting seconds to Date object
			this.dateTime = new Date(sec * 1000);
		} else {
			this.dateTime = null;
		}

		JSONArray weatherArray = (jsonObj != null) ? jsonObj.optJSONArray(JSON_WEATHER) : new JSONArray();
		this.weatherList = (weatherArray != null) ? new ArrayList<Weather>(weatherArray.length())
				: Collections.<Weather> emptyList();
		if (weatherArray != null && this.weatherList != Collections.EMPTY_LIST) {
			for (int i = 0; i < weatherArray.length(); i++) {
				JSONObject weatherObj = weatherArray.optJSONObject(i);
				if (weatherObj != null) {
					this.weatherList.add(new Weather(weatherObj));
				}
			}
		}
		this.weatherCount = this.weatherList.size();
	}

	/**
	 * @return Date and time if available.
	 */
	public Optional<Date> getDateTime() {
		return Optional.ofNullable(this.dateTime);
	}

	/**
	 * @return Count of Weather instance(s) if available, otherwise 0.
	 */
	public int getWeatherCount() {
		return this.weatherCount;
	}

	/**
	 * @param index
	 *            Index of Weather instance in the list.
	 * @return Weather instance if available.
	 */
	public Optional<Weather> getWeatherInstance(int index) {
		return index > -1 && index < weatherCount ? Optional.of(this.weatherList.get(index))
				: Optional.<Weather> empty();
	}

	/**
	 * <p>
	 * Provides default behavior for Cloud
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	abstract public static class Clouds implements Serializable {
		private static final String JSON_CLOUDS_ALL = "all";

		private final float percentOfClouds;

		Clouds() {
			this.percentOfClouds = Float.NaN;
		}

		Clouds(JSONObject jsonObj) {
			this.percentOfClouds = (float) jsonObj.optDouble(JSON_CLOUDS_ALL, Double.NaN);
		}

		/**
		 * @return Percentage of all clouds if available.
		 */
		public Optional<Float> getPercentageOfClouds() {
			return !Float.isNaN(this.percentOfClouds) ? Optional.of(this.percentOfClouds) : Optional.<Float> empty();
		}
	}

	/**
	 * <p>
	 * Provides default behavior for Coord, i.e., coordinates.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	abstract public static class Coord implements Serializable {
		private static final String JSON_COORD_LATITUDE = "lat";
		private static final String JSON_COORD_LONGITUDE = "lon";

		private final float lat;
		private final float lon;

		Coord() {
			this.lat = Float.NaN;
			this.lon = Float.NaN;
		}

		Coord(JSONObject jsonObj) {
			this.lat = (float) jsonObj.optDouble(JSON_COORD_LATITUDE, Double.NaN);
			this.lon = (float) jsonObj.optDouble(JSON_COORD_LONGITUDE, Double.NaN);
		}

		/**
		 * @return Latitude of the city if available.
		 */
		public Optional<Float> getLatitude() {
			return !Float.isNaN(this.lat) ? Optional.of(this.lat) : Optional.<Float> empty();
		}

		/**
		 * @return Longitude of the city if available.
		 */
		public Optional<Float> getLongitude() {
			return !Float.isNaN(this.lon) ? Optional.of(this.lon) : Optional.<Float> empty();
		}
	}

	/**
	 * <p>
	 * Provides default behavior for Main, i.e., main weather elements like
	 * temperature, humidity, etc.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	abstract public static class Main implements Serializable {

		private static final String JSON_MAIN_TEMP = "temp";
		private static final String JSON_MAIN_TEMP_MIN = "temp_min";
		private static final String JSON_MAIN_TEMP_MAX = "temp_max";
		private static final String JSON_MAIN_PRESSURE = "pressure";
		private static final String JSON_MAIN_HUMIDITY = "humidity";

		private final float temp;
		private final float minTemp;
		private final float maxTemp;
		private final float pressure;
		private final float humidity;

		Main() {
			this.temp = Float.NaN;
			this.minTemp = Float.NaN;
			this.maxTemp = Float.NaN;
			this.pressure = Float.NaN;
			this.humidity = Float.NaN;
		}

		Main(JSONObject jsonObj) {
			this.temp = (float) jsonObj.optDouble(JSON_MAIN_TEMP, Double.NaN);
			this.minTemp = (float) jsonObj.optDouble(JSON_MAIN_TEMP_MIN, Double.NaN);
			this.maxTemp = (float) jsonObj.optDouble(JSON_MAIN_TEMP_MAX, Double.NaN);
			this.pressure = (float) jsonObj.optDouble(JSON_MAIN_PRESSURE, Double.NaN);
			this.humidity = (float) jsonObj.optDouble(JSON_MAIN_HUMIDITY, Double.NaN);
		}

		/**
		 * @return Temperature of the city if available.
		 */
		public Optional<Float> getTemperature() {
			return !Float.isNaN(this.temp) ? Optional.of(this.temp) : Optional.<Float> empty();
		}

		/**
		 * @return Minimum temperature of the city if available.
		 */
		public Optional<Float> getMinTemperature() {
			return !Float.isNaN(this.minTemp) ? Optional.of(this.minTemp) : Optional.<Float> empty();
		}

		/**
		 * @return Maximum temperature of the city if available.
		 */
		public Optional<Float> getMaxTemperature() {
			return !Float.isNaN(this.maxTemp) ? Optional.of(this.maxTemp) : Optional.<Float> empty();
		}

		/**
		 * @return Pressure of the city if available.
		 */
		public Optional<Float> getPressure() {
			return !Float.isNaN(this.pressure) ? Optional.of(this.pressure) : Optional.<Float> empty();
		}

		/**
		 * @return Humidity of the city if available.
		 */
		public Optional<Float> getHumidity() {
			return !Float.isNaN(this.humidity) ? Optional.of(this.humidity) : Optional.<Float> empty();
		}
	}

	/**
	 * <p>
	 * Parses weather data and provides methods to get/access the same
	 * information. This class <code>get</code> methods to access the
	 * information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.3
	 */
	public static class Weather implements Serializable {
		private static final String JSON_WEATHER_ID = "id";
		private static final String JSON_WEATHER_MAIN = "main";
		private static final String JSON_WEATHER_DESCRIPTION = "description";
		private static final String JSON_WEATHER_ICON = "icon";

		private final int id;
		private final String name;
		private final String description;
		private final String icon;

		Weather() {
			this.id = Integer.MIN_VALUE;
			this.name = null;
			this.description = null;
			this.icon = null;
		}

		Weather(JSONObject jsonObj) {
			this.id = jsonObj.optInt(JSON_WEATHER_ID, Integer.MIN_VALUE);
			this.name = jsonObj.optString(JSON_WEATHER_MAIN, null);
			this.description = jsonObj.optString(JSON_WEATHER_DESCRIPTION, null);
			this.icon = jsonObj.optString(JSON_WEATHER_ICON, null);
		}

		/**
		 * @return Code for weather of the city if available.
		 */
		public Optional<Integer> getWeatherCode() {
			return this.id != Integer.MIN_VALUE ? Optional.of(this.id) : Optional.<Integer> empty();
		}

		/**
		 * @return Name for weather of the city if available.
		 */
		public Optional<String> getWeatherName() {
			return this.name != null && (!"".equals(this.name)) ? Optional.of(this.name) : Optional.<String> empty();
		}

		/**
		 * @return Description for weather of the city if available.
		 */
		public Optional<String> getWeatherDescription() {
			return this.description != null && (!"".equals(this.description)) ? Optional.of(this.description)
					: Optional.<String> empty();
		}

		/**
		 * @return Name of icon for weather of the city if available.
		 */
		public Optional<String> getWeatherIconName() {
			return this.icon != null && (!"".equals(this.icon)) ? Optional.of(this.icon) : Optional.<String> empty();
		}
	}

	/**
	 * <p>
	 * Provides default behavior for Wind.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	abstract public static class Wind implements Serializable {
		private static final String JSON_WIND_SPEED = "speed";
		private static final String JSON_WIND_DEGREE = "deg";

		private final float speed;
		private final float degree;

		Wind() {
			this.speed = Float.NaN;
			this.degree = Float.NaN;
		}

		Wind(JSONObject jsonObj) {
			this.speed = (float) jsonObj.optDouble(JSON_WIND_SPEED, Double.NaN);
			this.degree = (float) jsonObj.optDouble(JSON_WIND_DEGREE, Double.NaN);
		}

		/**
		 * @return Speed of wind in the city if available.
		 */
		public Optional<Float> getWindSpeed() {
			return !Float.isNaN(this.speed) ? Optional.of(this.speed) : Optional.<Float> empty();
		}

		/**
		 * @return Degree of wind in the city if available.
		 */
		public Optional<Float> getWindDegree() {
			return !Float.isNaN(this.degree) ? Optional.of(this.degree) : Optional.<Float> empty();
		}
	}
}

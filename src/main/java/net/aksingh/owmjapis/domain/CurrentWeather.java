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

import java.io.Serializable;
import java.util.Date;
import java.util.Optional;

import org.json.JSONObject;

/**
 * <p>
 * Parses current weather data and provides methods to get/access the same
 * information. This class provides <code>get</code> methods to access the
 * information.
 * </p>
 * <p>
 * <code>get</code> methods can be used to retrieve the data if it exists.
 * </p>
 *
 * @author Ashutosh Kumar Singh
 * @author Cesar Aguilera
 * @version 2015/09/20
 * @see <a href="http://openweathermap.org/current">OWM's Current Weather
 *      API</a>
 * @since 2.5.0.1
 */
public class CurrentWeather extends AbstractWeather {
	/*
	 * JSON Keys
	 */
	private static final String JSON_RAIN = "rain";
	private static final String JSON_SNOW = "snow";
	private static final String JSON_SYS = "sys";
	private static final String JSON_BASE = "base";
	private static final String JSON_CITY_ID = "id";
	private static final String JSON_CITY_NAME = "name";

	/*
	 * Instance variables
	 */
	private final String base;
	private final long cityId;
	private final String cityName;

	private final Clouds clouds;
	private final Coord coord;
	private final Main main;
	private final Rain rain;
	private final Snow snow;
	private final Sys sys;
	private final Wind wind;

	/*
	 * Constructor
	 */
	public CurrentWeather(JSONObject jsonObj) {
		super(jsonObj);

		this.base = (jsonObj != null) ? jsonObj.optString(JSON_BASE, null) : null;
		this.cityId = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
		this.cityName = (jsonObj != null) ? jsonObj.optString(JSON_CITY_NAME, null) : null;

		JSONObject cloudsObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_CLOUDS) : null;
		this.clouds = (cloudsObj != null) ? new Clouds(cloudsObj) : null;

		JSONObject coordObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_COORD) : null;
		this.coord = (coordObj != null) ? new Coord(coordObj) : null;

		JSONObject mainObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_MAIN) : null;
		this.main = (mainObj != null) ? new Main(mainObj) : null;

		JSONObject rainObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_RAIN) : null;
		this.rain = (rainObj != null) ? new Rain(rainObj) : null;

		JSONObject snowObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_SNOW) : null;
		this.snow = (snowObj != null) ? new Snow(snowObj) : null;

		JSONObject sysObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_SYS) : null;
		this.sys = (sysObj != null) ? new Sys(sysObj) : null;

		JSONObject windObj = (jsonObj != null) ? jsonObj.optJSONObject(JSON_WIND) : null;
		this.wind = (windObj != null) ? new Wind(windObj) : null;
	}

	/**
	 * @return Base station if available.
	 */
	public Optional<String> getBaseStation() {
		return this.base != null && (!"".equals(this.base)) ? Optional.of(this.base) : Optional.<String> empty();
	}

	/**
	 * @return City code if available.
	 */
	public Optional<Long> getCityCode() {
		return this.cityId != Long.MIN_VALUE ? Optional.of(this.cityId) : Optional.<Long> empty();
	}

	/**
	 * @return City name if available.
	 */
	public Optional<String> getCityName() {
		return this.cityName != null && (!"".equals(this.cityName)) ? Optional.of(this.cityName)
				: Optional.<String> empty();
	}

	/**
	 * @return Clouds instance if available.
	 */
	public Optional<Clouds> getCloudsInstance() {
		return Optional.ofNullable(this.clouds);
	}

	/**
	 * @return Coord instance if available.
	 */
	public Optional<Coord> getCoordInstance() {
		return Optional.ofNullable(this.coord);
	}

	/**
	 * @return Main instance if available.
	 */
	public Optional<Main> getMainInstance() {
		return Optional.ofNullable(this.main);
	}

	/**
	 * @return Rain instance if available.
	 */
	public Optional<Rain> getRainInstance() {
		return Optional.ofNullable(this.rain);
	}

	/**
	 * @return Snow instance if available.
	 */
	public Optional<Snow> getSnowInstance() {
		return Optional.ofNullable(this.snow);
	}

	/**
	 * @return Sys instance if available.
	 */
	public Optional<Sys> getSysInstance() {
		return Optional.ofNullable(this.sys);
	}

	/**
	 * @return Wind instance if available.
	 */
	public Optional<Wind> getWindInstance() {
		return Optional.ofNullable(this.wind);
	}

	/**
	 * <p>
	 * Parses clouds data and provides methods to get/access the same
	 * information. This class provides <code>get</code> methods to access the
	 * information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Clouds extends AbstractWeather.Clouds {

		Clouds() {
			super();
		}

		Clouds(JSONObject jsonObj) {
			super(jsonObj);
		}
	}

	/**
	 * <p>
	 * Parses coordination data and provides methods to get/access the same
	 * information. This class provides <code>has</code> and <code>get</code>
	 * methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Coord extends AbstractWeather.Coord {

		Coord() {
			super();
		}

		Coord(JSONObject jsonObj) {
			super(jsonObj);
		}
	}

	/**
	 * <p>
	 * Parses main data and provides methods to get/access the same information.
	 * This class provides <code>get</code> methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Main extends AbstractWeather.Main {

		Main() {
			super();
		}

		Main(JSONObject jsonObj) {
			super(jsonObj);
		}
	}

	/**
	 * <p>
	 * Parses rain data and provides methods to get/access the same information.
	 * This class provides <code>get</code> methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Rain implements Serializable {

		private static final String JSON_RAIN_1HOUR = "1h";
		private static final String JSON_RAIN_3HOUR = "3h";

		private final float rain1h;
		private final float rain3h;

		Rain() {
			this.rain1h = Float.NaN;
			this.rain3h = Float.NaN;
		}

		Rain(JSONObject jsonObj) {
			this.rain1h = (float) jsonObj.optDouble(JSON_RAIN_1HOUR, Double.NaN);
			this.rain3h = (float) jsonObj.optDouble(JSON_RAIN_3HOUR, Double.NaN);
		}

		public Optional<Float> getRain1h() {
			return !Float.isNaN(this.rain1h) ? Optional.of(this.rain1h) : Optional.<Float> empty();
		}

		public Optional<Float> getRain3h() {
			return !Float.isNaN(this.rain3h) ? Optional.of(this.rain3h) : Optional.<Float> empty();
		}
	}

	/**
	 * <p>
	 * Parses snow data and provides methods to get/access the same information.
	 * This class provides <code>get</code> methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.4
	 */
	public static class Snow implements Serializable {

		private static final String JSON_SNOW_1HOUR = "1h";
		private static final String JSON_SNOW_3HOUR = "3h";

		private final float snow1h;
		private final float snow3h;

		Snow() {
			this.snow1h = Float.NaN;
			this.snow3h = Float.NaN;
		}

		Snow(JSONObject jsonObj) {
			this.snow1h = (float) jsonObj.optDouble(JSON_SNOW_1HOUR, Double.NaN);
			this.snow3h = (float) jsonObj.optDouble(JSON_SNOW_3HOUR, Double.NaN);
		}

		public Optional<Float> getSnow1h() {
			return !Float.isNaN(this.snow1h) ? Optional.of(this.snow1h) : Optional.<Float> empty();
		}

		public Optional<Float> getSnow3h() {
			return !Float.isNaN(this.snow3h) ? Optional.of(this.snow3h) : Optional.<Float> empty();
		}
	}

	/**
	 * <p>
	 * Parses sys data and provides methods to get/access the same information.
	 * This class provides <code>get</code> methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Sys implements Serializable {

		private static final String JSON_SYS_TYPE = "type";
		private static final String JSON_SYS_ID = "id";
		private static final String JSON_SYS_MESSAGE = "message";
		private static final String JSON_SYS_COUNTRY_CODE = "country";
		private static final String JSON_SYS_SUNRISE = "sunrise";
		private static final String JSON_SYS_SUNSET = "sunset";

		private final int type;
		private final int id;
		private final double message;
		private final String countryCode;
		private final Date sunrise;
		private final Date sunset;

		Sys() {
			this.type = Integer.MIN_VALUE;
			this.id = Integer.MIN_VALUE;
			this.message = Double.NaN;
			this.countryCode = null;
			this.sunrise = null;
			this.sunset = null;
		}

		Sys(JSONObject jsonObj) {
			this.type = jsonObj.optInt(JSON_SYS_TYPE, Integer.MIN_VALUE);
			this.id = jsonObj.optInt(JSON_SYS_ID, Integer.MIN_VALUE);
			this.message = jsonObj.optDouble(JSON_SYS_MESSAGE, Double.NaN);
			this.countryCode = jsonObj.optString(JSON_SYS_COUNTRY_CODE, null);

			long sr_secs = jsonObj.optLong(JSON_SYS_SUNRISE, Long.MIN_VALUE);
			if (sr_secs != Long.MIN_VALUE) {
				this.sunrise = new Date(sr_secs * 1000);
			} else {
				this.sunrise = null;
			}

			long ss_secs = jsonObj.optLong(JSON_SYS_SUNSET, Long.MIN_VALUE);
			if (ss_secs != Long.MIN_VALUE) {
				this.sunset = new Date(ss_secs * 1000);
			} else {
				this.sunset = null;
			}
		}

		public Optional<Integer> getType() {
			return this.type != Integer.MIN_VALUE ? Optional.of(this.type) : Optional.<Integer> empty();
		}

		public Optional<Integer> getId() {
			return this.id != Integer.MIN_VALUE ? Optional.of(this.id) : Optional.<Integer> empty();
		}

		public Optional<Double> getMessage() {
			return !Double.isNaN(this.message) ? Optional.of(this.message) : Optional.<Double> empty();
		}

		public Optional<String> getCountryCode() {
			return this.countryCode != null && (!"".equals(this.countryCode)) ? Optional.of(this.countryCode)
					: Optional.<String> empty();
		}

		public Optional<Date> getSunriseTime() {
			return Optional.ofNullable(this.sunrise);
		}

		public Optional<Date> getSunsetTime() {
			return Optional.ofNullable(this.sunset);
		}
	}

	/**
	 * <p>
	 * Parses wind data and provides methods to get/access the same information.
	 * This class provides <code>get</code> methods to access the information.
	 * </p>
	 * <p>
	 * <code>get</code> methods can be used to retrieve the data if it exists.
	 * </p>
	 *
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 * @version 2015/09/20
	 * @since 2.5.0.1
	 */
	public static class Wind extends AbstractWeather.Wind {

		private static final String JSON_WIND_GUST = "gust";

		private final float gust;

		Wind() {
			super();

			this.gust = Float.NaN;
		}

		Wind(JSONObject jsonObj) {
			super(jsonObj);

			this.gust = (float) jsonObj.optDouble(JSON_WIND_GUST, Double.NaN);
		}

		public Optional<Float> getWindGust() {
			return !Float.isNaN(this.gust) ? Optional.of(this.gust) : Optional.<Float> empty();
		}
	}
}

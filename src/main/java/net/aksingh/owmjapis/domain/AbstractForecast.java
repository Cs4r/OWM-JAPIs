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
import java.util.Optional;

import org.json.JSONObject;

/**
 * <p>
 * Provides default behavior and implementations for:
 * </p>
 * <ol>
 * <li>{@link net.aksingh.owmjapis.domain.HourlyForecast}</li>
 * <li>{@link net.aksingh.owmjapis.domain.DailyForecast}</li>
 * </ol>
 *
 * @author Ashutosh Kumar Singh
 * @author Cesar Aguilera
 * @version 2015/09/20
 * @since 2.5.0.3
 */
public abstract class AbstractForecast extends AbstractResponse {
	/*
	 * JSON Keys
	 */
	protected static final String JSON_FORECAST_LIST = "list";
	protected static final String JSON_MESSAGE = "message";
	protected static final String JSON_CITY = "city";
	protected static final String JSON_FORECAST_COUNT = "cnt";

	/*
	 * Instance variables
	 */
	private final double message;

	private final City city;
	private final int forecastCount;

	/*
	 * Constructors
	 */
	AbstractForecast() {
		super();
		this.message = Double.NaN;
		this.forecastCount = 0;
		this.city = null;
	}

	AbstractForecast(JSONObject jsonObj) {
		super(jsonObj);

		this.message = (jsonObj != null) ? jsonObj.optDouble(JSON_MESSAGE, Double.NaN) : Double.NaN;

		this.city = (jsonObj != null) ? new City(jsonObj.optJSONObject(JSON_CITY)) : null;

		this.forecastCount = (jsonObj != null) ? jsonObj.optInt(JSON_FORECAST_COUNT, 0) : 0;
	}

	/**
	 * @return Message if available.
	 */
	public Optional<Double> getMessage() {
		return this.message != Double.NaN ? Optional.of(this.message) : Optional.<Double> empty();
	}

	/**
	 * @return Count of forecasts if available, <code>0</code> otherwise.
	 */
	public int getForecastCount() {
		return this.forecastCount;
	}

	/**
	 * @return City's instance if available.
	 */
	public Optional<City> getCityInstance() {
		return Optional.ofNullable(this.city);
	}

	/**
	 * <p>
	 * Provides default behavior for City.
	 * </p>
	 *
	 * @author Ashutosh Kumar Singh
	 * @author Cesar Aguilera
	 */
	public static class City implements Serializable {
		private static final String JSON_CITY_ID = "id";
		private static final String JSON_CITY_NAME = "name";
		private static final String JSON_CITY_COUNTRY_CODE = "country";
		private static final String JSON_CITY_POPULATION = "population";
		private static final String JSON_CITY_COORD = "coord";

		private final long cityID;
		private final String cityName;
		private final String countryCode;
		private final long population;

		private final Coord coord;

		City() {
			this.cityID = Long.MIN_VALUE;
			this.cityName = null;
			this.countryCode = null;
			this.population = Long.MIN_VALUE;

			this.coord = new Coord();
		}

		City(JSONObject jsonObj) {
			this.cityID = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_ID, Long.MIN_VALUE) : Long.MIN_VALUE;
			this.cityName = (jsonObj != null) ? jsonObj.optString(JSON_CITY_NAME, null) : null;
			this.countryCode = (jsonObj != null) ? jsonObj.optString(JSON_CITY_COUNTRY_CODE, null) : null;
			this.population = (jsonObj != null) ? jsonObj.optLong(JSON_CITY_POPULATION, Long.MIN_VALUE)
					: Long.MIN_VALUE;

			JSONObject jsonObjCoord = (jsonObj != null) ? jsonObj.optJSONObject(JSON_CITY_COORD) : null;
			this.coord = (jsonObjCoord != null) ? new Coord(jsonObjCoord) : null;
		}

		public Optional<Long> getCityCode() {
			return this.cityID != Long.MIN_VALUE ? Optional.of(this.cityID) : Optional.<Long> empty();
		}

		public Optional<String> getCityName() {
			return Optional.ofNullable(this.cityName);
		}

		public Optional<String> getCountryCode() {
			return Optional.ofNullable(this.countryCode);
		}

		public Optional<Long> getCityPopulation() {
			return this.population != Long.MIN_VALUE ? Optional.of(this.population) : Optional.<Long> empty();
		}

		/**
		 * @return {@link Coord} instance if available.
		 */
		public Optional<Coord> getCoordInstance() {
			return Optional.ofNullable(this.coord);
		}

		public static class Coord extends AbstractWeather.Coord {

			Coord() {
				super();
			}

			Coord(JSONObject jsonObj) {
				super(jsonObj);
			}
		}
	}

	/**
	 * <p>
	 * Parses forecast data (one element in the forecastList) and provides
	 * methods to get/access the same information. This class provides
	 * <code>get</code> methods to access the information.
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
	public abstract static class Forecast extends AbstractWeather {
		Forecast() {
			super();
		}

		Forecast(JSONObject jsonObj) {
			super(jsonObj);
		}
	}
}

package net.aksingh.owmjapis.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/**
 * Requests OWM.org for data and provides back the incoming response.
 *
 * @since 2.5.0.3
 */
public class OWMResponse {
	private final OWMAddress owmAddress;
	private final OWMProxy owmProxy;

	public OWMResponse(OWMAddress owmAddress, OWMProxy owmProxy) {
		this.owmAddress = owmAddress;
		this.owmProxy = owmProxy;
	}

	/*
	 * Responses for current weather
	 */
	public String currentWeatherByCityName(String cityName) throws UnsupportedEncodingException {
		String address = owmAddress.currentWeatherByCityName(cityName);
		return httpGET(address);
	}

	public String currentWeatherByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
		String address = owmAddress.currentWeatherByCityName(cityName, countryCode);
		return httpGET(address);
	}

	public String currentWeatherByCityCode(long cityCode) {
		String address = owmAddress.currentWeatherByCityCode(cityCode);
		return httpGET(address);
	}

	public String currentWeatherByCoordinates(float latitude, float longitude) {
		String address = owmAddress.currentWeatherByCoordinates(latitude, longitude);
		return httpGET(address);
	}

	/*
	 * Responses for hourly forecasts
	 */
	public String hourlyForecastByCityName(String cityName) throws UnsupportedEncodingException {
		String address = owmAddress.hourlyForecastByCityName(cityName);
		return httpGET(address);
	}

	public String hourlyForecastByCityName(String cityName, String countryCode) throws UnsupportedEncodingException {
		String address = owmAddress.hourlyForecastByCityName(cityName, countryCode);
		return httpGET(address);
	}

	public String hourlyForecastByCityCode(long cityCode) {
		String address = owmAddress.hourlyForecastByCityCode(cityCode);
		return httpGET(address);
	}

	public String hourlyForecastByCoordinates(float latitude, float longitude) {
		String address = owmAddress.hourlyForecastByCoordinates(latitude, longitude);
		return httpGET(address);
	}

	/*
	 * Responses for daily forecasts
	 */
	public String dailyForecastByCityName(String cityName, byte count) throws UnsupportedEncodingException {
		String address = owmAddress.dailyForecastByCityName(cityName, count);
		return httpGET(address);
	}

	public String dailyForecastByCityName(String cityName, String countryCode, byte count)
			throws UnsupportedEncodingException {
		String address = owmAddress.dailyForecastByCityName(cityName, countryCode, count);
		return httpGET(address);
	}

	public String dailyForecastByCityCode(long cityCode, byte count) {
		String address = owmAddress.dailyForecastByCityCode(cityCode, count);
		return httpGET(address);
	}

	public String dailyForecastByCoordinates(float latitude, float longitude, byte count) {
		String address = owmAddress.dailyForecastByCoordinates(latitude, longitude, count);
		return httpGET(address);
	}

	/**
	 * Implements HTTP's GET method
	 *
	 * @param requestAddress
	 *            Address to be loaded
	 * @return Response if successful, else <code>null</code>
	 * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">HTTP
	 *      - (9.3) GET</a>
	 */
	private String httpGET(String requestAddress) {
		URL request;
		HttpURLConnection connection = null;
		BufferedReader reader = null;

		String tmpStr;
		String response = null;

		try {
			request = new URL(requestAddress);

			if (owmProxy.getProxy() != null) {
				connection = (HttpURLConnection) request.openConnection(owmProxy.getProxy());
			} else {
				connection = (HttpURLConnection) request.openConnection();
			}

			connection.setRequestMethod("GET");
			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
			connection.connect();

			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				String encoding = connection.getContentEncoding();

				try {
					if (encoding != null && "gzip".equalsIgnoreCase(encoding)) {
						reader = new BufferedReader(
								new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
					} else if (encoding != null && "deflate".equalsIgnoreCase(encoding)) {
						reader = new BufferedReader(new InputStreamReader(
								new InflaterInputStream(connection.getInputStream(), new Inflater(true))));
					} else {
						reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					}

					while ((tmpStr = reader.readLine()) != null) {
						response = tmpStr;
					}
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						}
					}
				}
			} else { // if HttpURLConnection is not okay
				try {
					reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
					while ((tmpStr = reader.readLine()) != null) {
						response = tmpStr;
					}
				} catch (IOException e) {
					System.err.println("Error: " + e.getMessage());
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							System.err.println("Error: " + e.getMessage());
						}
					}
				}

				// if response is bad
				System.err.println("Bad Response: " + response + "\n");
				return null;
			}
		} catch (IOException e) {
			System.err.println("Error: " + e.getMessage());
			response = null;
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}

		return response;
	}
}
package net.aksingh.owmjapis.core;

/**
 * Units that can be set for getting data from OWM.org
 *
 * @since 2.5.0.3
 */
public enum OWMUnits {
	//@formatter:off
	METRIC("metric"), 
	IMPERIAL("imperial");

	private final String unit;

	OWMUnits(String unit) {
		this.unit = unit;
	}

	public String getCode() {
		return unit;
	}
	//@formatter:on
}
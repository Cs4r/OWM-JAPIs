package net.aksingh.owmjapis.core;

/**
 * Languages that can be set for getting data from OWM.org
 *
 * @since 2.5.0.3
 */
public enum OWMLanguage {
	//@formatter:off
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
	//@formatter:on

	private final String lang;

	OWMLanguage(String lang) {
		this.lang = lang;
	}

	public String getCode() {
		return lang;
	}
}
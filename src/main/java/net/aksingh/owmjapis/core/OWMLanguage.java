/*
 * Copyright (c) 2013-2015 Ashutosh Kumar Singh <me@aksingh.net>
 * Copyright (c) 2015 Cesar Aguilera (@cs4r)
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
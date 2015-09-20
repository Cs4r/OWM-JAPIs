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
package net.aksingh.owmjapis.service;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

/**
 * Proxifies the default HTTP requests
 *
 * @since 2.5.0.5
 */
public class OWMProxy {
	private String ip;
	private int port;
	private String user;
	private String pass;

	public OWMProxy(String ip, int port, String user, String pass) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

	public Proxy getProxy() {
		Proxy proxy = null;

		if (ip != null && (!"".equals(ip)) && port != Integer.MIN_VALUE) {
			proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
		}

		if (user != null && (!"".equals(user)) && pass != null && (!"".equals(pass))) {
			Authenticator.setDefault(getAuthenticatorInstance(user, pass));
		}

		return proxy;
	}

	private Authenticator getAuthenticatorInstance(final String user, final String pass) {
		Authenticator authenticator = new Authenticator() {

			public PasswordAuthentication getPasswordAuthentication() {
				return (new PasswordAuthentication(user, pass.toCharArray()));
			}
		};

		return authenticator;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}
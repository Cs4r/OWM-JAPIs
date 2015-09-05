package net.aksingh.owmjapis.core;

public class ProxyInfo {
	public final String ip;
	public final int port;
	public final String user;
	public final String pass;

	public ProxyInfo(String ip, int port, String user, String pass) {
		this.ip = ip;
		this.port = port;
		this.user = user;
		this.pass = pass;
	}

}

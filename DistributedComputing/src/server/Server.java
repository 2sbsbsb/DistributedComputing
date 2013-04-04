package server;

/**
 * 
 */
public class Server {

	private String ip;
	private int port;

	/**
	 * @param ip
	 * @param port
	 */
	public Server(String ip, int port) {
		super();
		this.ip = ip;
		this.port = port;
	}

	/**
	 * @return
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @return
	 */
	public int getPort() {
		return port;
	}

}

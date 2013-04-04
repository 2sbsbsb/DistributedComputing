package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

/**
 * Prime Number Server : Responsible to start primary prime number service or
 * secondary prime number service to serve the clients.
 * 
 */
public class PrimeNumberServer {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		boolean listening = true;
		boolean isPrimary = false;
		int port = -1;
		List<Server> servers = new ArrayList<Server>();
		try {
			if (args.length > 0) {
				port = Integer.parseInt(args[0]);
				serverSocket = new ServerSocket(port);
				if (args.length == 1) {
					isPrimary = false;
				} else if (args.length > 1) {
					isPrimary = true;
					if (args.length > 7 || args.length % 2 == 0) {
						printErrorMessage();
					} else {
						for (int i = 1; i < args.length; i = i + 2) {
							Server server = new Server(args[i],
									Integer.parseInt(args[i + 1]));
							servers.add(server);
						}
					}
				}
			} else {
				printErrorMessage();
			}
			System.out
					.println("Connection successful. Total Number of Secondary Servers ="
							+ servers.size());
			while (listening) {
				if (isPrimary) {
					new PrimeNumberPrimaryService(serverSocket.accept(),
							servers).start();
				} else {
					new PrimeNumberService(serverSocket.accept()).start();
				}
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port" + port);
			System.exit(-1);
		} finally {
			serverSocket.close();
		}
	}

	/**
	 */
	private static void printErrorMessage() {
		// TODO Auto-generated method stub
		System.out.println("Usage:");
		System.out
				.println("Primary Prime Number Server: java PrimeNumberServer <port> Optional {<Secondary Server IP> <port>}n where n=1,2 or 3");
		System.out
				.println("Secondary Prime Number Server: java PrimeNumberServer <port>");
	}
}

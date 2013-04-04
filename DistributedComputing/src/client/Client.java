package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import server.Server;

/**
 * 
 *
 */
public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length == 4) {
			Server server = new Server(args[0], Integer.parseInt(args[1]));
			int fromNumber = Integer.parseInt(args[2]);
			int toNumber = Integer.parseInt(args[3]);
			Socket socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				socket = new Socket(server.getIp(), server.getPort());
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				out.println(fromNumber + " " + toNumber);
				out.flush();
				System.out.println("Problem - Input Range " + fromNumber + ":"
						+ toNumber);
				//
				String line = null;
				while ((line = in.readLine()) != null) {
					System.out.println(line);
				}
			} catch (Exception ex) {

			} finally {
				try {
					out.close();
					in.close();
					socket.close();
				} catch (IOException e) {

				}
			}
		} else {
			System.out
					.println("Use java Client <PrimaryServerIP> <PrimaryServerPort> <fromNumber> <toNumber>");
		}
	}

}

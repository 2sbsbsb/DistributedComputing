package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * PrimeNumberPrimaryService
 */
public class PrimeNumberPrimaryService extends PrimeNumberService {

	private List<Server> servers;

	public PrimeNumberPrimaryService(Socket socket, List<Server> servers) {
		super(socket);
		this.servers = servers;
	}

	/**
	 * @param fromNumber
	 * @param toNumber
	 * @return
	 */
	protected List<Integer> calculatePrimeNumbers(int fromNumber, int toNumber) {

		List<Integer> results = new ArrayList<Integer>();
		int secondaryServerSize = servers.size();
		// Added 1 because total server = secondary servers + primary servers
		int totalServers = secondaryServerSize + 1;
		int totalLoadSize = toNumber - fromNumber + 1;
		int loadSizeOnEachServer = totalLoadSize / totalServers;
		// Primary server will take the remainder burden too.
		int loadSizeOnPrimaryServer = loadSizeOnEachServer
				+ (totalLoadSize % totalServers);
		// Printing info about how many secondary servers are present
		System.out.println("Each secondary server will handle "
				+ loadSizeOnEachServer + " and primary server will handle "
				+ loadSizeOnPrimaryServer + " numbers");

		// Algorithm for dividing task
		int fNumber = fromNumber;
		List<BufferedReader> ins = new ArrayList<BufferedReader>();
		try {
			for (int i = 0; i < secondaryServerSize; i++) {
				Socket secondarySocket = new Socket(servers.get(i).getIp(),
						servers.get(i).getPort());
				PrintWriter out = new PrintWriter(
						secondarySocket.getOutputStream(), true);
				int x = fNumber;
				fNumber = fNumber - 1 + loadSizeOnEachServer;
				out.println(x + " " + fNumber);
				out.flush();
				// Keeping the reader in list because later here i will get the
				// results form all the servers
				BufferedReader in = new BufferedReader(new InputStreamReader(
						secondarySocket.getInputStream()));
				ins.add(in);
				fNumber++;
			}
			// Adding primary server results first.
			results.addAll(super.calculatePrimeNumbers(fNumber, toNumber));
			System.out.println("Primary Server input range " + fNumber + "-"
					+ toNumber);
			System.out.println("Primary Server primary numbers "
					+ Arrays.toString(results.toArray()));

			// Adding secondary server results from all the servers.
			String inputLine = null;
			for (BufferedReader in : ins) {
				while ((inputLine = in.readLine()) != null) {
					String[] input = inputLine.split(" ");
					for (String inp : input) {
						if (inp != null && !inp.equalsIgnoreCase("")) {
							results.add(Integer.parseInt(inp));
						}
					}
				}
			}
			// Sorting the results
			Collections.sort(results);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;
	}

}

package server;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

import algo.PrimeNumberCalculator;

/**
 * PrimeNumberService reads two integers from socket and uses
 * <code>PrimeNubmerService</code> and get the prime numbers between those two
 * numbers, finally writes back the results to the socket.
 */
public class PrimeNumberService extends Thread {

	private Socket socket = null;

	/**
	 * @param socket
	 */
	public PrimeNumberService(Socket socket) {
		super("PrimeNumberService");
		this.socket = socket;
	}

	/**
	 * 	
	 */
	public void run() {
		PrintWriter out = null;
		BufferedReader in = null;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			String inputLine = null;
			int fromNumber = -1;
			int toNumber = -1;
			while ((inputLine = in.readLine()) != null) {
				String[] input = inputLine.split(" ");
				if (input.length >= 2) {
					fromNumber = Integer.parseInt(input[0]);
					toNumber = Integer.parseInt(input[1]);
					if (fromNumber >= 0 && toNumber >= 0) {
						break;
					}
				}
			}
			System.out.println("The input range is " + fromNumber + "-"
					+ toNumber);
			List<Integer> primeNumbers = calculatePrimeNumbers(fromNumber,
					toNumber);
			StringBuilder sb = new StringBuilder();
			for (Integer i : primeNumbers) {
				sb.append(i);
				sb.append(" ");
			}
			out.println(sb.toString());
			out.flush();
			System.out.println("The output is " + sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.close();
			} catch (IOException e) {
				// Intentionally leaving it
			}
		}
	}

	/**
	 * @param fromNumber
	 * @param toNumber
	 * @return
	 */
	protected List<Integer> calculatePrimeNumbers(int fromNumber, int toNumber) {
		ArrayList<Integer> primeNumbers = PrimeNumberCalculator.getPrimeNumber(
				fromNumber, toNumber);
		return primeNumbers;
	}

}

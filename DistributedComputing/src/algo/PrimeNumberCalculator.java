package algo;

import java.util.ArrayList;

/**
 * Responsible for calculating prime number between any two given numbers
 */
public class PrimeNumberCalculator {

	/**
	 * @return the list of prime number given between toNumber and fromNumber
	 */
	public static ArrayList<Integer> getPrimeNumber(int fromNumber, int toNumber) {
		// assert is for testing
		assert fromNumber >= toNumber : "fromNumber " + fromNumber
				+ " should be greater than or equal toNumber " + toNumber;

		ArrayList<Integer> prime = new ArrayList<Integer>();
		for (int i = fromNumber; i <= toNumber; i++) {
			if (isPrime(i)) {
				prime.add(i);
			}
		}
		return prime;
	}

	/**
	 * Verify if the given number is prime or not - This is the easiest and
	 * safest way to calculate isPrime - This is not necessarily efficient
	 * performance wise
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(int n) {
		if (n <= 1) {
			return false;
		}
		if (n == 2) {
			return true;
		}
		for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
			if (n % i == 0) {
				return false;
			}
		}
		return true;
	}

}

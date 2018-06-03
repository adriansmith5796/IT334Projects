/*
 * Cynthia Pham
 * Adrian Smith
 * 1/17/18
 * IT 333
 * Test class
 */

package tests;

import java.util.Iterator;

import datastructures.PrimeCalculator;

public class Test {

	public static void main(String[] args) {
		
		
		PrimeCalculator calc = new PrimeCalculator(30);
		
		System.out.println("Testing isPrime(10): " + calc.isPrime(10));
		System.out.println("Testing isPrime(19): " + calc.isPrime(19));
		System.out.println("Testing iterator with bounds = 30: ");
		Iterator<Integer> it = calc.iterator();
		while(it.hasNext()){
			int next = it.next();
			System.out.println(next + " is Prime! ");
		}

	}

}

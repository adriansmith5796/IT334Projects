/*
 * Cynthia Pham
 * Adrian Smith
 * 1/17/18
 * IT 333
 * PrimeCalculator class
 */

package datastructures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeCalculator implements Iterable<Integer> {
	
	boolean[] primeOrNot;
	
	public PrimeCalculator(int bound) {
		if(bound <= 0) {
			throw new IllegalArgumentException("Bound cannot be negative!");
		}
		
		primeOrNot = new boolean[bound + 1];
		
		for(int i = 0; i < primeOrNot.length; i++){
			if(isPrime(i)){
				primeOrNot[i] = true;
			}else{
				primeOrNot[i] = false;
			}
		}
		
	}
	
	public boolean isPrime(int number) {
		
		if(number <= 1 ) {
			 return false;
		}
		
		for(int i = 2; i < number; i++){
			if(number % i == 0){
				return false;
			}
		}
		return true;
	}

	@Override
	public Iterator<Integer> iterator() {
		return new PrimeIterator();
	}
	
	private class PrimeIterator implements Iterator<Integer>{

		private int currentIndex;
		private int numPrime;
		
		public PrimeIterator(){
			currentIndex = 0;
			for(int i = 0; i < primeOrNot.length; i++){
				if(primeOrNot[i]){
					numPrime = i;
				}
			}
		}
		
		@Override
		public boolean hasNext() {
			
			if (currentIndex < numPrime) {
				return true;
			}
			
			return false;
		}

		@Override
		public Integer next() {
			Integer nextPrime;
			
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			
			for (int i = currentIndex; i <= numPrime; i++) {
				if (primeOrNot[currentIndex] == true) {
					nextPrime = (Integer) currentIndex++;
					return nextPrime;
				} else {
					currentIndex++;
				}
			}
			
			return null;
			
		}
		
	}

}

package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import datastructures.PrimeCalculator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PrimeCalculatorTests
{
    private PrimeCalculator calculator;

    @Before
    public void setup()
    {
        calculator = new PrimeCalculator(50);
    }

    @Test
    public void testIsPrime()
    {
        //negative numbers should be false
        Assert.assertFalse("Negative numbers are not prime", calculator.isPrime(-1));
        Assert.assertFalse("Negative numbers are not prime", calculator.isPrime(-10));

        //0-2 should be false
        Assert.assertFalse("0 is not prime", calculator.isPrime(0));
        Assert.assertFalse("1 is not prime, by definition", calculator.isPrime(1));

        //test a few of the first calculator
        int[] primes = {2, 3, 5, 7};

        for (int i = 0; i < primes.length; i++)
        {
            Assert.assertTrue(primes[i] + " should be marked as prime", calculator.isPrime(primes[i]));
        }
    }

    @Test
    public void testIteratorInLoop()
    {
        int[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

        //loop using the iterator and retrieve each value
        int count = 0;
        for (int prime : calculator)
        {
            Assert.assertEquals("value retrieved by iterator is incorrect", primes[count++], prime);
        }
    }

    @Test
    public void testIteratorInteractions()
    {
        //try to create an iterator with only a single element
        calculator = new PrimeCalculator(1);

        Iterator<Integer> primes = calculator.iterator();
        Assert.assertFalse("Iterator should return false with only a single value and a bound of one",
                           primes.hasNext());

        //try to create an iterator with the first prime number
        calculator = new PrimeCalculator(2);
        primes = calculator.iterator();
        int firstPrime = primes.next();

        Assert.assertEquals("", 2, firstPrime);
        Assert.assertFalse("Iterator should not have another value after the first prime and bound of one",
                primes.hasNext());

        //verify that a NoSuchElementException is thrown when you reach the last element in the iterator
        try
        {
            //retrieve another prime without checking hasNext() (there are none)
            int secondPrime = primes.next();
            Assert.fail("The next() method does not throw a NoSuchElementException when there are no elements left");
        }
        catch (NoSuchElementException ex)
        {
            //do nothing...
        }
    }

    @Test
    public void testBounds()
    {
        //try to enter an incorrect bounds
        try
        {
            calculator = new PrimeCalculator(0);
            Assert.fail("PrimeCalculator class should throw an IllegalArgumentException when " +
                    "given a non-positive bounds.");
        }
        catch (IllegalArgumentException ex)
        {
            //do nothing...
        }
    }
}

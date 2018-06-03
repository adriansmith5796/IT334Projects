package tests;

import org.junit.Test;
import structures.FourWayHeap;

import java.util.Random;

import static org.junit.Assert.*;

public class FourWayHeapTest {
    FourWayHeap<Integer> heap;

    @org.junit.Before
    public void setUp() throws Exception {
        heap = new FourWayHeap<>();
        assertTrue("Size is not 0 when initialized as empty.",
                heap.size() == 0);
    }

    @org.junit.After
    public void tearDown() throws Exception {
        heap = null;
    }



    @Test
    public void testAddEmptyHeapGoodVal(){
        heap.add(9);
        assertTrue("Heap should have one element.", heap.size() == 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddEmptyHeapBadVal(){
        heap.add(null);
        assertTrue("Heap should have no elements.", heap.size() == 0);
    }

    @Test
    public void testAddMultipleElemsGoodVals(){
        int[] check = new int[10];
        for(int i = 0; i < 10; i++){
            heap.add(i);
            check[i] = i;
        }
        assertTrue("Heap and array should be equal.", heap.size() == check.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMultiVarVals(){
        Random random = new Random();
        int iterations = 50;

        for(int i = 0; i < iterations; i++){
            heap.add(i < iterations / 2 ? i : null);
        }
        assertTrue("Heap should have " + iterations / 2 + " elements, it has " + heap.size(), heap.size() == iterations / 2);
    }

    @Test
    public void testEmptyHeapIsEmpty() {
        assertTrue("Expected: Empty heap isEmpty() == true, Got Empty heap isEmpty() == false", heap.isEmpty());
    }

    @Test
    public void testOneElemHeapIsEmptyFalse() {
        heap.add(5);
        assertFalse("Expected: Empty heap isEmpty() == false, Got Empty heap isEmpty() == true", heap.isEmpty());
    }

    @Test
    public void testRemoveMinEmptyHeap() {
        assertTrue("RemoveMin should return null on empty heap.", heap.removeMin() == null);
    }

    @Test
    public void testRemoveMinNonEmptyHeap() {
        for(int i = 0; i < 5; i++){
            heap.add(i);
        }

        for(int i = 0; i < 5; i++){
            int remove = heap.removeMin();
            assertTrue("RemoveMin should return " + i + ", returned " + remove + " .", remove == i);
        }

        assertTrue("RemoveMin should be null on empty tree.", heap.removeMin() == null);

    }

    @Test
    public void size() {
        for(int i = 0; i < 10; i++){
            heap.add(i);
        }

        assertTrue("size should be 10", heap.size() == 10);
    }

    @Test
    public void testIsEmpty() {
        assertTrue("Heap should be empty.", heap.isEmpty());
        heap.add(5);
        assertFalse("Heap should not be empty.", heap.isEmpty());
    }

    @Test
    public void clear() {
        heap.add(5);
        heap.clear();
        assertTrue("Heap should be empty.", heap.isEmpty());
    }

    @Test
    public void contains() {
    }
}
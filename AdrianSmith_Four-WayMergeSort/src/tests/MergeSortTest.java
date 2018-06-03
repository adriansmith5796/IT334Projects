package tests;

import org.junit.*;
import sort.MergeSort;

import java.util.Random;

public class MergeSortTest {

    /****************  SIZE IS LESS THAN 4  ****************/

    @Test
    public void sizeEqualsThreeArraySorted() {
        Comparable[] array = new Comparable[] {1, 2, 3};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void sizeEqualsThreeArrayNotSorted() {
        Comparable[] array = new Comparable[] {3, 2, 0};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void sizeEqualsTwoArraySorted() {
        Comparable[] array = new Comparable[] {1, 2};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void sizeEqualsTwoArrayNotSorted() {
        Comparable[] array = new Comparable[] {2, 1};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void sizeEqualsOne() {
        Comparable[] array = new Comparable[] {1};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void sizeEqualsZero() {
        Comparable[] array = new Comparable[] {};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    /*************** END SIZE TESTS *********************/

    @Test
    public void allValuesAreEqual() {
        Comparable[] array = new Comparable[] {7, 7, 7, 7, 7, 7, 7, 7, 7, 7};
        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void reverseSortedArray() {
        Comparable[] array = new Comparable[10];
        int num = 10;

        for (int i = 0; i < 10; i++)
            array[i] = num--;

        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void testArraySize100() {
        Comparable[] array = new Comparable[100];
        Random random = new Random();

        for(int i = 0; i < 100; i++){
            array[i] = random.nextInt(1000);
        }

        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void testArraySize1000() {
        Comparable[] array = new Comparable[1000];
        Random random = new Random();

        for(int i = 0; i < 1000; i++){
            array[i] = random.nextInt(1000);
        }

        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void testArraySize10000() {
        Comparable[] array = new Comparable[10000];
        Random random = new Random();

        for(int i = 0; i < 10000; i++){
            array[i] = random.nextInt(1000);
        }

        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void testArraySize100000() {
        Comparable[] array = new Comparable[100000];
        Random random = new Random();

        for(int i = 0; i < 100000; i++){
            array[i] = random.nextInt(1000);
        }

        MergeSort.mergeSort(array);
        Assert.assertTrue(isSorted(array));
    }

    @Test
    public void testSimpleSort() {
        Comparable[] actual = new Comparable[10];
        Comparable[] expected = new Comparable[10];
        int num = 10;

        for (int i = 0; i < 10; i++) {
            actual[i] = num--;
            expected[i] = i + 1;
        }

        MergeSort.simpleSort(actual, 0, actual.length - 1);
        Assert.assertArrayEquals(expected, actual);
    }

    private boolean isSorted(Comparable[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i].compareTo(array[i + 1]) > 0) {
                return false;
            }
        }


        return true;
    }
}
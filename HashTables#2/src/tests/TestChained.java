package tests;

import interfaces.ICollection;
import interfaces.ISet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.ChainedMap;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class TestChained {

    private ChainedMap<String, Integer> chainedMap;

    private class KeyValue {
        String key;
        Integer value;

        KeyValue(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    /* Test Fixture */
    @Before
    public void setup(){
        chainedMap = new ChainedMap<>();
    }

    /* Method tests */
    @Test
    public void addAndGetTest(){
        chainedMap.add("Algorithms", 334);
        int result = chainedMap.get("Algorithms");
        Assert.assertEquals("add(): value does not match key",334, result);
    }

    @Test
    public void addUpdateTest(){
        chainedMap.add("Algorithms", 333);
        chainedMap.add("Algorithms", 334);
        int result = chainedMap.get("Algorithms");
        Assert.assertEquals("add() with existing key did not update the value",334, result);
    }

    @Test
    public void addSizeTest(){
        chainedMap.add("DataStructures", 333);
        chainedMap.add("Algorithms", 334);
        chainedMap.add("SystemsProgramming", 301);
        int result = chainedMap.size();
        Assert.assertEquals("multi-element add() did not maintain size correctly",3, result);
    }

    @Test
    public void getTestNull(){
        Integer result = chainedMap.get("Algorithms");
        Assert.assertNull("get() on empty table returned non-null value", result);
    }

    @Test
    public void containsTestTrue(){
        chainedMap.add("Algorithms", 334);
        boolean result = chainedMap.contains("Algorithms");
        Assert.assertTrue("contains() did not find key that was added", result);
    }

    @Test
    public void containsTestFalse(){
        boolean result = chainedMap.contains("Algorithms");
        Assert.assertFalse("contains() on empty table should not return true", result);
    }


    @Test
    public void removeFoundTestTrue() {
        chainedMap.add("Algorithms", 334);
        boolean result = chainedMap.remove("Algorithms");
        Assert.assertTrue("remove() should return true after removing found key", result);
    }

    @Test
    public void removeNotFoundTestFalse() {
        boolean result = chainedMap.remove("Algorithms");
        Assert.assertFalse("remove() should return false if key not found", result);
    }


    @Test
    public void removeFromOneElemFoundTest(){
        chainedMap.add("Algorithms", 334);
        Assert.assertTrue(chainedMap.remove("Algorithms"));

        boolean result = chainedMap.contains("Algorithms");
        Assert.assertFalse("remove() did not remove existing key in one-element table", result);
    }

    @Test
    public void removeFoundFromMultiElemTest(){
        for (int i = 0; i < 20; i++) {
            chainedMap.add("Janet" + i, i);
        }
        chainedMap.remove("Janet5");

        boolean result = chainedMap.contains("Janet5");
        Assert.assertFalse("remove() did not remove existing key in multi-element table", result);
    }

    @Test
    public void removeNotFoundFromMultiElemTest(){
        for (int i = 0; i < 20; i++) {
            chainedMap.add("Janet" + i, i);
        }
        boolean removed = chainedMap.remove("Janet");
        Assert.assertFalse("remove() indicates removal of non-existant key in multi-element table", removed);
    }

    @Test
    public void sizeEmptyTableTest() {
        Assert.assertEquals("size() of empty table is incorrect", 0, chainedMap.size());
    }

    @Test
    public void sizeOneElemTableTest() {
        chainedMap.add("Red", 255);
        Assert.assertEquals("size() of one-element table is incorrect", 1, chainedMap.size());
    }

    @Test
    public void sizeMultiElemTableTest() {
        chainedMap.add("Red", 255);
        chainedMap.add("Green", 255);
        chainedMap.add("Blue", 255);
        chainedMap.add("Alpha", 255);
        Assert.assertEquals("size() of multi-element table is incorrect", 4, chainedMap.size());
    }

    @Test
    public void sizeAfterRemoveMultiElemTableTest() {
        chainedMap.add("Red", 255);
        chainedMap.add("Green", 255);
        chainedMap.add("Blue", 255);
        chainedMap.add("Alpha", 255);
        chainedMap.remove("Blue");
        Assert.assertEquals("size() of multi-element table after remove is incorrect", 3, chainedMap.size());
    }

    @Test
    public void sizeAfterRemoveMultiElemTableTest2() {
        chainedMap.add("Red", 255);
        chainedMap.add("Green", 255);
        chainedMap.add("Blue", 255);
        chainedMap.add("Alpha", 255);
        chainedMap.remove("Yellow");
        Assert.assertEquals("size() of multi-element table after no-op remove is incorrect", 4, chainedMap.size());
    }

    @Test
    public void testIsEmptyTrue(){
        Assert.assertTrue("isEmpty() on empty table did not return true", chainedMap.isEmpty());
    }

    @Test
    public void testIsEmptyFalse(){
        chainedMap.add("Janet", 21);
        Assert.assertFalse("isEmpty() on non-empty table did not return false", chainedMap.isEmpty());
    }

    @Test
    public void clearNonEmptyTableTest(){
        chainedMap.add("Red", 255);
        chainedMap.add("Green", 255);
        chainedMap.add("Blue", 255);
        chainedMap.add("Alpha", 255);
        chainedMap.clear();
        Assert.assertTrue(chainedMap.isEmpty());
    }

    @Test
    public void clearEmptyTableTest(){
        chainedMap.clear();
        Assert.assertTrue(chainedMap.isEmpty());
    }

    @Test
    public void testEmptyTableIterator() {
        String result = "";
        Iterator<Integer> iterator = chainedMap.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Iterator returned wrong result", "", result);
    }

    @Test
    public void testIterateAll() {

        // load data for iterator
        Integer[] elems = new Integer[20];
        for (int i = 0; i < 20; i++) {
            elems[i] = i;
            chainedMap.add("Janet" + i, i);
        }
        // second array to track returned values
        Integer[] foundInts = new Integer[elems.length];

        // iterate and store returned object references
        int i = 0;
        Iterator<Integer> iterator = chainedMap.iterator();
        while (iterator.hasNext()) {
            foundInts[i++] = iterator.next();
        }

        // compare start and end arrays after sorting both
        Arrays.sort(foundInts);
        Arrays.sort(elems);
        assertArrayEquals("Values from iterator don't match starting values", elems, foundInts);
    }

    @Test(expected=ConcurrentModificationException.class)
    public void testModWhileIterating() {
        for (int i = 0; i < 20; i++) {
            chainedMap.add("Janet" + i, i);
        }

        // load data for iterator
        Iterator<Integer> iterator = chainedMap.iterator();
        while (iterator.hasNext()) {
            chainedMap.add("Kobe", 6);
            iterator.next();
            fail("Iterator didn't throw exception");
        }
    }

    @Test
    public void testEmptyTableKeyset() {
        String result = "";
        ISet<String> keys = chainedMap.keyset();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Keyset on empty table returned wrong result", "", result);
    }

    @Test
    public void testOneElementTableKeyset() {
        chainedMap.add("Janet", 21);
        String result = "";
        ISet<String> keys = chainedMap.keyset();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Keyset on one-element table returned wrong result", "Janet", result);
    }

    @Test
    public void testMultiElementTableKeyset() {
        chainedMap.add("Janet", 1);
        chainedMap.add("Ian", 2);
        chainedMap.add("Gabriella", 3);
        chainedMap.add("Jordan", 4);
        chainedMap.add("Alex", 5);
        String[] names = {"Alex", "Gabriella", "Ian", "Janet", "Jordan"};
        String result = "";
        ISet<String> keys = chainedMap.keyset();
        Iterator<String> iterator = keys.iterator();
        while (iterator.hasNext()) {
            result +=  iterator.next() + " ";
        }
        result.substring(0,result.length()-1);
        String[] array = result.split(" ");
        Arrays.sort(array);

        assertEquals("Keyset on multi-element table returned wrong result", names, array);
    }

    @Test
    public void testEmptyTableValues() {
        String result = "";
        ICollection<Integer> values = chainedMap.values();
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Values on empty table returned wrong result", "", result);
    }

    @Test
    public void testOneElementTableValues() {
        chainedMap.add("Janet", 1);

        String result = "";
        ICollection<Integer> values = chainedMap.values();
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Values on empty table returned wrong result", "1", result);
    }

    @Test
    public void testMultiElementTableValues() {
        chainedMap.add("Janet", 1);
        chainedMap.add("Ian", 2);
        chainedMap.add("Gabriella", 3);
        chainedMap.add("Jordan", 4);
        chainedMap.add("Alex", 5);

        String result = "";
        ICollection<Integer> values = chainedMap.values();
        Iterator<Integer> iterator = values.iterator();
        while (iterator.hasNext()) {
            result += iterator.next();
        }
        assertEquals("Values on empty table returned wrong result", "12345", result);
    }

}
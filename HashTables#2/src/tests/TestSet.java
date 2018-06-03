package tests;

import interfaces.ISet;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import structures.Set;

import java.util.Arrays;

public class TestSet {

    private Set<Integer> set1;
    private Set<Integer> set2;

    /**
     * Setup.
     */
    @Before
    public void setup(){
        set1 = new Set<>();
        set2 = new Set<>();
    }

    private int[] toArray(ISet<Integer> set) {
        int[] array = new int[set.size()];
        int idx = 0;
        for (int i : set) {
            array[idx++] = i;
        }
        return array;
    }

    @Test
    public void unionNonEmptyTest(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        ISet<Integer> result = set1.union(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("union() of two non-overlapping sets produced incorrect results", new int[]{1,2,3,4,5,6}, actual);
    }

    @Test
    public void unionNonEmptyOverlapTest(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(4);
        set2.add(3);
        ISet<Integer> result = set1.union(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("union() of two overlapping sets produced incorrect results", new int[]{1,2,3,4}, actual);
    }

    @Test
    public void unionNonEmptyEmptyTest(){
        set1.add(1);
        set1.add(2);
        ISet<Integer> result = set1.union(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("union() of non-empty and empty set produced incorrect results", new int[]{1,2}, actual);
    }

    @Test
    public void unionEmptyNonEmptyTest(){
        set2.add(3);
        set2.add(4);
        ISet<Integer> result = set1.union(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("union() of empty and non-empty set produced incorrect results", new int[]{3,4}, actual);
    }

    @Test
    public void unionEmptyTest(){
        ISet<Integer> result = set1.union(set2);
        assertTrue("union() of two empty sets produced incorrect results", result.isEmptySet());
    }

    @Test
    public void intersectsNoOverlapTest(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        ISet<Integer> result = set1.intersects(set2);
        assertTrue("intersects() of two non-overlapping sets produced incorrect results", result.isEmptySet());
    }

    @Test
    public void intersectsOverlapTest() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(4);
        set2.add(3);
        ISet<Integer> result = set1.intersects(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("intersects() of two overlapping sets produced incorrect results", new int[]{2,3}, actual);
    }

    @Test
    public void intersectsNonEmptyEmptyTest(){
        set1.add(1);
        set1.add(2);
        ISet<Integer> result = set1.intersects(set2);
        assertTrue("intersects() of non-empty and empty set produced incorrect results", result.isEmptySet());
    }

    @Test
    public void intersectsEmptyNonEmptyTest(){
        set2.add(3);
        set2.add(4);
        ISet<Integer> result = set1.intersects(set2);
        assertTrue("intersects() of empty and non-empty set produced incorrect results", result.isEmptySet());
    }

    @Test
    public void intersectsEmptyTest(){
        ISet<Integer> result = set1.intersects(set2);
        assertTrue("intersects() of two empty sets produced incorrect results", result.isEmptySet());
    }

    @Test
    public void differenceNoOverlapTest(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        ISet<Integer> result = set1.difference(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("difference() of two non-overlapping sets produced incorrect results", new int[]{1,2,3}, actual);
    }

    @Test
    public void differenceOverlapTest() {
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(2);
        set2.add(4);
        set2.add(3);
        ISet<Integer> result = set1.difference(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("difference() of two overlapping sets produced incorrect results", new int[]{1}, actual);
    }

    @Test
    public void differenceNonEmptyEmptyTest(){
        set1.add(1);
        set1.add(2);
        ISet<Integer> result = set1.difference(set2);
        int[] actual = toArray(result);
        Arrays.sort(actual);
        assertArrayEquals("difference() of non-empty and empty set produced incorrect results", new int[]{1,2}, actual);
    }

    @Test
    public void differenceEmptyNonEmptyTest(){
        set2.add(3);
        set2.add(4);
        ISet<Integer> result = set1.difference(set2);
        assertTrue("difference() of empty and non-empty set produced incorrect results", result.isEmptySet());
    }

    @Test
    public void differenceEmptyTest(){
        ISet<Integer> result = set1.difference(set2);
        assertTrue("difference() of two empty sets produced incorrect results", result.isEmptySet());
    }

    @Test
    public void isSubsetEqualSubsetTestTrue(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(1);
        set2.add(2);
        set2.add(3);
        assertTrue("isSubset() with two equivalent sets returned false", set1.isSubset(set2));
    }

    @Test
    public void isSubsetProperSubsetTestTrue(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(1);
        set2.add(2);
        assertTrue("isSubset() with proper subset returned false", set1.isSubset(set2));
    }

    @Test
    public void isSubsetEmptySetTestTrue(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertTrue("isSubset() with empty set did not not return true", set1.isSubset(set2));
    }

    @Test
    public void isSubsetTestFalse(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        assertFalse("isSubset() with disjoint sets did not return false", set1.isSubset(set2));
    }

    @Test
    public void isSubsetOverlapTestFalse(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(3);
        set2.add(6);
        assertFalse("isSubset() with partially overlapping set did not return false", set1.isSubset(set2));
    }

    @Test
    public void isSubsetEmptySetTestFalse(){
        set2.add(4);
        set2.add(5);
        set2.add(6);
        assertFalse("isSubset() called on empty set with non-empty set should return false", set1.isSubset(set2));
    }

    @Test
    public void isSubsetBothEmptyTestTrue(){
        assertTrue("isSubset() with two empty sets should return true", set1.isSubset(set2));
    }

    @Test
    public void isDisjointTestTrue(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(5);
        set2.add(6);
        assertTrue("isDisjoint() with disjoint sets should return true", set1.isDisjoint(set2));
    }

    @Test
    public void isDisjointEmptyNonEmptyTest(){
        set2.add(4);
        set2.add(2);
        set2.add(6);
        assertTrue("isDisjoint() with empty and non-empty sets should return true", set1.isDisjoint(set2));
    }

    @Test
    public void isDisjointNonEmptyEmptyTest(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        assertTrue("isDisjoint() with non-empty and empty sets should return true", set1.isDisjoint(set2));
    }

    @Test
    public void isDisjointTestFalse(){
        set1.add(1);
        set1.add(2);
        set1.add(3);
        set2.add(4);
        set2.add(2);
        set2.add(6);
        assertFalse("isDisjoint() with partially overlapping sets should return false", set1.isDisjoint(set2));
    }

    @Test
    public void isDisjointEmptyEmptyTestTrue(){
        assertTrue("isDisjoint() did not return true on two empty sets", set1.isDisjoint(set2));
    }

    @Test
    public void testIsEmptySetTrue(){
        assertTrue("isEmptySet() return false on empty set", set1.isEmptySet());
    }

    @Test
    public void testIsEmptySetFalse(){
        set1.add(1);
        assertFalse("isEmptySet() returned true on non-empty set", set1.isEmptySet());
    }

}
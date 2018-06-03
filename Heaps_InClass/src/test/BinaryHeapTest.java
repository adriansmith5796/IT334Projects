package test;

import static org.junit.Assert.*;
import org.junit.*;
import heaps.*;

import java.util.Arrays;

public class BinaryHeapTest {

    BinaryHeap<Task> heap = new BinaryHeap<>();
    // mock data
    final Task[] tasks = {
            new Task(Priority.HIGH, 1, "Finish homework"),
            new Task(Priority.MEDIUM, 2, "Go to gym"),
            new Task(Priority.LOW, 1, "check Facebook"),
            new Task(Priority.MEDIUM, 1, "get groceries"),
            new Task(Priority.MEDIUM, 3, "go to dinner"),
            new Task(Priority.LOW, 2, "play football"),
            new Task(Priority.HIGH, 1, "pay bills"),
            new Task(Priority.MEDIUM, 3, "Clean house"),
            new Task(Priority.HIGH, 2, "feed cats")
    };

    @Before
    public void setUp() throws Exception {
        heap = new BinaryHeap<>(); // construct empty queue
    }

    @Test
    public void testEmptyHeapIsEmpty() {
        assertTrue("Expected: Empty heap isEmpty() == true, Got Empty heap isEmpty() == false", heap.isEmpty());
    }

    @Test
    public void testOneElemHeapIsEmptyFalse() {
        heap.insert(tasks[0]);
        assertFalse("Expected: Empty heap isEmpty() == false, Got Empty heap isEmpty() == true", heap.isEmpty());
    }

    @Test
    public void testEmptyHeapInsertGoodTask1() {
        heap.insert(tasks[0]);
        assertEquals("Task was not inserted.", 1, heap.size());
    }

    @Test
    public void testEmptyHeapInsertGoodTask2() {
        heap.insert(tasks[0]);
        assertSame("Task was not inserted.", tasks[0], heap.findMin());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testEmptyHeapInsertBadTask(){
        heap.insert(null);
    }

    @Test
    public void testContainsTrue() {
        heap.insert(tasks[0]);
        assertTrue("Task was not inserted.", heap.contains(tasks[0]));
    }

    @Test
    public void testContainsFalse() {
        assertFalse("Task was inserted.", heap.contains(tasks[0]));
    }

    @Test
    public void testEmptyHeapDeleteMin() {
        assertNull("Deletemin returned not null value.", heap.deleteMin());
    }

    @Test
    public void testEmptyHeapFindMin() {
        assertNull("Findmin returned not null value.", heap.findMin());
    }

    @Test
    public void testEmptyHeapIterator() {
        for(Task task : heap){
            fail("iterator should not find value in empty heap");
        }
    }

    @Test
    public void testMultiElemHeapIterator() {
        Task[] initTasks = new Task[tasks.length];

        for(int i = 0; i < tasks.length; i++){
            initTasks[i] = tasks[i];
            heap.insert(initTasks[i]);
        }

        Task[] foundTasks = new Task[initTasks.length];

        int index = 0;

        for(Task task : heap){
            foundTasks[index++] = task;
        }

        Arrays.sort(initTasks);
        Arrays.sort(foundTasks);
        assertArrayEquals("Values from iterator don't match starting values.", initTasks, foundTasks);
    }

    @Test
    public void size() {

    }



    @Test
    public void clear() {
    }

    @Test
    public void contains() {
    }



    @After
    public void tearDown() throws Exception {

    }
}
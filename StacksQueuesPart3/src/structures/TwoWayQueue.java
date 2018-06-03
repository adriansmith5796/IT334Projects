/*
 * Adrian Smith
 * IT 333
 * Stacks & Queues Part 2
 * Two Way Queue
 * This class implements a custom LinkedList Node class
 * to create a two way queue data structure. 
 */

package structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.EmptyQueueException;

/**
 * A generic two way queue data structure. It allows for enqueueing or 
 * dequeueing at the front or end of the queue.   
 * @author adriansmith
 * @version 1.0
 * @param <T> the type being put in the queue
 */
public class TwoWayQueue<T> implements ITwoWayQueue<T> {

    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;
    
    /**
     * This constructor creates the data structure and links head and tail nodes
     * appropriately, while instantiating size to 0.
     */
    public TwoWayQueue(){
	head = new ListNode<T>();
	tail = new ListNode<T>();
	
	head.next = tail;
	tail.prev = head;
	
	size = 0;
    }
    
    /**
     * Removes and returns the first element in the queue.
     *
     * @throws exceptions.EmptyQueueException if the queue
     * is empty and dequeueFirst() is called
     * @return the first element
     */
    public T dequeueFirst() {
	if(isEmpty()) {
	    throw new EmptyQueueException();
	}
	
	T dequeue = head.next.data;
	head.next = head.next.next;
	head.next.prev = head;
	size--;
	return dequeue;
    }

    /**
     * Removes and returns the last element in the queue.
     *
     * @throws exceptions.EmptyQueueException if the queue
     * is empty and dequeueLast() is called
     * @return the last element
     */
    public T dequeueLast() {
	if(isEmpty()) {
	    throw new EmptyQueueException();
	}
	
	T dequeue = tail.prev.data;
	tail.prev = tail.prev.prev;
	tail.prev.next = tail;
	size--;
	return dequeue;
    }

    /**
     * Removes and returns all elements in the queue. The first
     * element in the queue should be located at the last index
     * of the resulting list (index size() - 1) and the last
     * element in the queue at index zero.
     *
     * @return a list of all elements in the queue
     */
    public List<T> dequeueAll() {
	if(isEmpty()) {
	    throw new EmptyQueueException();
	}
	
	List<T> queue = new LinkedList<T>();
	ListNode<T> current = tail.prev;
	
	while(current != head) {
	    queue.add(current.data);
	    current = current.prev;
	}

	clear();
	return queue;
    }

    /**
     * Adds a new element to the front of the queue. The
     * queue should continually resize to make room for new
     * elements.
     *
     * @param element the new element
     */
    public void enqueueFirst(T element) {
	ListNode<T> newNode = new ListNode<T>(element);

	newNode.next = head.next;
	newNode.prev = head;
	newNode.next.prev = newNode;
	head.next = newNode;
	size++;
    }

    /**
     * Adds a new element to the end of the queue. The
     * queue should continually resize to make room for new
     * elements.
     *
     * @param element the new element
     */
    public void enqueueLast(T element) {
	ListNode<T> newNode = new ListNode<T>(element);
	
	newNode.prev = tail.prev;
	newNode.next = tail;
	newNode.prev.next = newNode;
	tail.prev = newNode;
	size++;
    }

    /**
     * Adds a group of elements to the front of the queue.
     *
     * @param elements an array of elements
     */
    public void enqueueAllFirst(T[] elements) {
	for(int i = 0; i < elements.length; i++) {
	    enqueueFirst(elements[i]);
	}
    }

    /**
     * Adds a group of elements to the end of the queue.
     *
     * @param elements an array of elements
     */
    public void enqueueAllLast(T[] elements) {
	for(int i = 0; i < elements.length; i++) {
	    enqueueLast(elements[i]);
	}
    }

    /**
     * Returns the number of elements in the queue.
     *
     * @return the number of elements
     */
    public int size() {
	return size;
    }

    /**
     * Reports whether the queue is empty or not.
     *
     * @return true if no elements are in the queue,
     * otherwise returns false
     */
    public boolean isEmpty() {
	return size == 0;
    }

    /**
     * Removes all elements from the queue by only connecting
     * the head and tail node together, thus severing the rest of
     * the nodes from the queue.
     */
    public void clear() {
	head.next = tail;
	tail.prev = head;
	size = 0;
    }

    /**
     * Returns an iterator over the elements of the
     * queue. It should not be possible to use the
     * iterator while making any changes to the stack
     * itself.
     *
     * Elements should return in FIFO order (i.e. The
     * first element added should be the first returned
     * by the iterator. The last element added should
     * be the last returned by the iterator.)
     *
     * @return an object using the Iterator<T> interface
     */
    public Iterator<T> iterator() {
	return new TwoWayIterator();
    }
    
    /**
     * This class is a custom linked list node that is used in the Stack
     * class to replicate a Stack data structure. It holds a field of the 
     * Data Type <E> and keeps track of which node is next in the list/Stack. 
     * @author adriansmith
     *
     * @param <T1> The data type being managed in the structure.
     */
    private class ListNode<T1> {
	    public T1 data;
	    public ListNode<T1> next;
	    public ListNode<T1> prev;
	    
	    /**
	     * No-argument constructor that initializes the node with null values.
	     */
	    public ListNode() {
		this(null, null, null);
	    }
	    
	    /**
	     * One-argument constructor that initializes the data with the value
	     * passed in but the next with a null value.
	     * @param data
	     */
	    public ListNode(T1 data) {
		this(data, null, null);
	    }   
	    
	    public ListNode(T1 data, ListNode<T1> prev, ListNode<T1> next) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	    }
    }
    
    /**
     * This custom class is an iterator for the TwoWayQueue Data Structure.
     * @author adriansmith
     * @version 1.0
     */
    private class TwoWayIterator implements Iterator<T>{
	private ListNode<T> current;
	private int startingSize;
	
	/**
	 * Constructs the iterator by setting the starting point to the first item 
	 * enqueued and setting the startingSize to ensure there's no 
	 * concurrent modification happening.
	 */
	public TwoWayIterator() {
	    current = tail;
	    startingSize = size();
	}
	
	/**
	 * Returns true if the iteration has more elements.
	 * (In other words, returns true if next would return an element rather than throwing an exception.)
	 * 
	 * @throws ConcurrentModificationException if queue is modified while benig iterated over
	 * @return true if there are more elements to be iterated over
	 */
	public boolean hasNext() {
	    if(startingSize != size) {
		throw new ConcurrentModificationException();
	    }
	    
	    return current.prev != head;
	}

	/**
	 * Returns the next element in the iteration.
	 * 
	 * @throws ConcurrentModificationException if queue is modified while benig iterated over
	 * @return the next element in the iteration
	 */
	public T next() {
	    if(startingSize != size) {
		throw new ConcurrentModificationException();
	    }
	    
	    T data = current.prev.data;
	    current = current.prev;
	    return data;
	}
	
    }
}

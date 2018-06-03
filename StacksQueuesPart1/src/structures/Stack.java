package structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import exceptions.StackUnderflowException;

/**
 * This custom Stack class implements a custom class, ListNode, 
 * that helps manage the data being held and which object 
 * should be popped next. The end of the list is always the most recent
 * object pushed on and should always be the first object popped off.
 * @author adriansmith
 * @version 1.0
 * @param <T> the data type being managed by this class
 */
public class Stack<T> implements IStack<T> {
    private ListNode<T> head;
    private int size;
    
    /**
     * This is a constructor for the data structure and 
     * instantiates the starting node with null values
     * and sets size to zero.
     */
    public Stack() {
	head = new ListNode<T>();
	size = 0;
    }
    
    @Override
    public void push(T element) {
	ListNode<T> last = traverse();
	ListNode<T> newNode = new ListNode<T>(element);
	last.next = newNode;
	size++;
    }

    @Override
    public void pushAll(T[] elements) {
	ListNode<T> last = traverse();
	
	for(int i = 0; i < elements.length; i++) {
	    ListNode<T> newNode = new ListNode<T>(elements[i]);
	    last.next = newNode;
	    last = last.next;
	    size++;
	}
    }

    @Override
    /**
     * @throws StackUnderflowException if trying to pop off an element when the Stack has no elements
     */
    public T pop(){
	if(head.next == null) {
	    throw new StackUnderflowException();
	}
	
	ListNode<T> last = traverse();
	ListNode<T> current = head;
	while(current.next != last) {
	    current = current.next;
	}
	current.next = null;
	size--;
	return last.data;
    }

    @Override
    /**
     * @throws StackUnderflowException if trying to pop off an element when the Stack has no elements
     */
    public List<T> popAll() {
	if(head.next == null) {
	    throw new StackUnderflowException();
	}
	
	List<T> list = new LinkedList<T>();
	ListNode<T> current = head.next;
	
	while(current != null) {
	    list.add(current.data);
	    current = current.next;
	    size--;
	}
	
	// Severs the connection between head node and rest of stack
	head.next = null;
	
	return list;
    }

    @Override
    public int size() {
	return size;
    }

    @Override
    public boolean isEmpty() {
	return head.next == null;
    }

    @Override
    public Iterator<T> iterator() {
	return new StackIterator(head, size);
    }

    @Override
    public void clear() {
	size = 0;
	head.next = null;
    }
    
    /**
     * This method traverses the custom linked list nodes until it gets to the end.
     * @return The last node in the list
     */
    private ListNode<T> traverse(){
	ListNode<T> current = head;
	while(current.next != null) {
	    current = current.next;
	}
	
	return current;
    }

    /**
     * This class is a custom linked list node that is used in the Stack
     * class to replicate a Stack data structure. It holds a field of the 
     * Data Type <E> and keeps track of which node is next in the list/Stack. 
     * @author adriansmith
     *
     * @param <E> The data type being managed in the structure.
     */
    private class ListNode<E> {
	    public E data;
	    public ListNode<E> next;
	    
	    /**
	     * No-argument constructor that initializes the node with null values.
	     */
	    public ListNode() {
		this(null);
	    }
	    
	    /**
	     * One-argument constructor that initializes the data with the value
	     * passed in but the next with a null value.
	     * @param data
	     */
	    public ListNode(E data) {
		this.data = data;
		this.next = null;
	    }   
    }
    
    /**
     * This iterator goes through the stack structure.
     * @author adriansmith
     * @throws ConcurrentModificationException if stack is modified while 
     * being iterated through
     */
    private class StackIterator implements Iterator<T>{

	private ListNode<T> current;
	private double startingSize;
	
	/**
	 * Returns an iterator object for the Stack data structure.
	 * @param start The head of the list/stack being iterated through
	 * @param size The size of the stack before any iteration has occured to ensure
	 * that it's not being modified while being iterated through.
	 */
	public StackIterator(ListNode<T> start, double size){
	    this.startingSize = size;
	    this.current = start;
	}
	
	@Override
	/**
	 * @throws StackUnderflowException if trying to pop off an element when the Stack has no elements
	 */
	public boolean hasNext() {
	    if(startingSize != size)
		throw new ConcurrentModificationException();
	    return current.next != null;
	}

	@Override
	/**
        	 * @throws StackUnderflowException if trying to pop off an element when the Stack has no elements
        	 */
	public T next() {
	    if(startingSize != size)
		throw new ConcurrentModificationException();
	    T data = current.next.data;
	    if(current != null && current.next != null)
		current = current.next;
	    return data;
	}
	
    }

    
}

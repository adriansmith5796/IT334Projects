package queue;

import java.util.NoSuchElementException;

public class CircularQueue {

    public static void main(String[] args) {
	CircularQueue queue = new CircularQueue();
	
	queue.enqueue(5);
	queue.enqueue(6);
	queue.enqueue(7);
	queue.enqueue(8);
	System.out.println(queue.toString());
	
	queue.dequeue();
	queue.dequeue();
	System.out.println(queue.toString());
	
	
	queue.dequeue();
	queue.dequeue();
	
	System.out.println(queue.toString());
    }
    
    private int front;
    private int back;
    private int[] queue;
    private int size;
    
    public CircularQueue() {
	queue = new int[10];
	front = 0;
	back = 0;
	size = 0;
    }
    
    public void enqueue(int elem) {
	if(back == front && !isEmpty()) {
	    return;
	}
	
	queue[back] = elem;
	size++;
	back = (++back) % queue.length;
    }
    
    public int dequeue() {
	if(isEmpty()) {
	    throw new NoSuchElementException();
	}
	size--;
	return queue[front++];
    }
    
    public boolean isEmpty() {
	return size == 0;
    }
    
    public String toString() {
	String rep = "Circular Queue of size: " + size + " ---- ";

	for(int i = 0; i < size; i++) {
	    rep += queue[front + i] + ", ";
	}
	
	return rep;
    }

}

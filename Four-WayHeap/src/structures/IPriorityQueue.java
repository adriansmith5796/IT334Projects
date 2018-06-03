package structures;

import java.util.Iterator;

public interface IPriorityQueue<T extends Comparable<T>> {

    // ADT Methods
    public void add(T element);
    public T removeMin();
    public T peek();
    // decrease(T element, int amount);
    // increase(T element, int amount);
    // remove(T element);

    // Other Standard Methods
    public int size();
    public boolean isEmpty();
    public void clear();
    public boolean contains(T element);
}

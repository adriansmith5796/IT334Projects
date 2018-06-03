package structures;

import java.util.Iterator;
import java.util.List;

/**
 * This interface provides an interface for a Stack
 * class.
 *
 * DO NOT ALTER THIS FILE!
 *
 * @author Josh Archer
 * @version 1.0
 */
public interface IStack<T> extends Iterable<T>
{
    /**
     * Adds a new element to the top of the stack.
     * The stack should grow to accommodate any number
     * of elements.
     *
     * @param element the new elements
     */
    public void push(T element);

    /**
     * Adds several elements to the top of the stack.
     * Elements are added starting with lower indices.
     * (i.e. the first element added should be index
     * zero and the last element added at index
     * length - 1). The stack should grow to accommodate
     * any number of elements.
     *
     * @param elements an array of elements
     */
    public void pushAll(T[] elements);

    /**
     * Removes and returns the top element of the stack.
     *
     * @throws exceptions.StackUnderflowException thrown when the
     * stack is empty and pop() is called
     * @return the top element of the stack
     */
    public T pop();

    /**
     * Removes and returns all elements of the stack as
     * a List<T> object. The elements should be returned
     * in the same order as they are located in the stack
     * itself (i.e. the element at index zero should be
     * the bottom of the stack and the element at index
     * size() - 1 should be the top of the stack)
     *
     * @throws exceptions.StackUnderflowException thrown when the
     * stack is empty and popAll() is called
     * @return a list of stack elements
     */
    public List<T> popAll();

    /**
     * Returns the number of elements in the stack.
     *
     * @return the number of elements
     */
    public int size();

    /**
     * Reports if the stack is empty.
     *
     * @return true if the stack has no elements, otherwise
     * false
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in the stack.
     * It should not be possible to use the iterator while
     * making any changes to the stack itself.
     * @return an object using the Iterator<T> interface
     */
    public Iterator<T> iterator();

    /**
     * Removes all elements from the stack.
     */
    public void clear();
}

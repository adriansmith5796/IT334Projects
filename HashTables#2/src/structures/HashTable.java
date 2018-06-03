/*
 * Adrian Smith
 * 3/12/16
 * IT 333
 * Hash Tables #1
 *
 * This homework demonstrates how a Hash Table is implemented using chaining.
 */
package structures;

import interfaces.ICollection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

@SuppressWarnings("unchecked") // Only Node objects are placed in buckets so unchecked casting is irrelevant
/**
 * This generic class tracks elements based on their hashcode. Values are passed
 * into the Node constructor which determines the hashcode, and therefore the
 * key indicating which index of buckets the Node will be stored in. Collisions
 * are managed using Seperate Chaining so each bucket is essentially a Linked 
 * list. 
 *
 * @author adriansmith
 *
 * @param <T> Data Type being stored
 */
public class HashTable<T> implements ICollection<T> {
    /********* FIELDS *********/
    private Object[] buckets; // Array of Nodes
    private int size; // Number of elements in structure

    /**
     * Constructs the table with 31 buckets that are Linked Lists with the key being
     * the index that the buckets is.
     */
    public HashTable() {
        this.buckets = new Object[31];
        this.size = 0;
    }

    @Override
    /**
     * Adds an element to the collection. No specific ordering is required.
     *
     * @param element
     *            the new element to put in the collection
     */
    public void add(T element) {
        Node newNode = new Node(element);
        Node current = (Node) buckets[newNode.key];
        Node prev = (Node) buckets[newNode.key];

        if (current == null) {
            buckets[newNode.key] = newNode;
            this.size++;
            return;
        }

        while (current != null) {
            prev = current;
            current = current.next;
        }

        prev.next = newNode;
        this.size++;
    }

    @Override
    /**
     * Finds and removes an element from the collection.
     *
     * @throws NoSuchElementException
     *             thrown when the element is not found in the collection
     * @param element
     *            the element to remove
     */
    public void remove(T element) {
        Node remove = new Node(element);
        Node current = (Node) buckets[remove.key];

        if (current != null && current.equals(remove)) {
            buckets[remove.key] = current.next;
            this.size--;
            return;
        }

        Node prev = (Node) buckets[remove.key];

        while (current != null) {
            if (remove.equals(current)) {
                prev.next = current.next;
                this.size--;
                return;
            }

            prev = current;
            current = current.next;
        }

        throw new NoSuchElementException();
    }

    @Override
    /**
     * Reports whether the collection contains an element.
     *
     * @param element
     *            the element to search for.
     * @return true if the element is found, otherwise false
     */
    public boolean contains(T element) {
        Node node = new Node(element);
        Node current = (Node) buckets[node.key];

        if (node.equals(current))
            return true;

        while (current != null) {
            if (node.equals(current)) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    /**
     * Returns the number of elements in the collection.
     *
     * @return the number of elements
     */
    public int size() {
        return this.size;
    }

    @Override
    /**
     * Reports whether the collection is empty or not.
     *
     * @return true if the collection is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    /**
     * Removes all elements from the collection.
     */
    public void clear() {
        this.buckets = new Object[13];
        this.size = 0;
    }

    @Override
    /**
     * Returns an element in the collection that matches the input parameter
     * according the equals() method of the parameter.
     *
     * @param element
     *            an element to search for
     * @return a matching element
     */
    public T get(T element) {
        Node node = new Node(element);
        Node current = (Node) buckets[node.key];

        if (node.equals(current))
            return node.value;

        while (current != null) {
            if (node.equals(current)) {
                return node.value;
            }

            current = current.next;
        }

        return null;
    }

    @Override
    /**
     * Returns an iterator over the collection.
     *
     * @return an object using the Iterator<T> interface
     */
    public Iterator<T> iterator() {
        return new HashIterator();
    }

    /**
     * Returns the String representation of the Hash Table.
     *
     * @return String representing the table
     */
    public String toString() {
        String value = "";
        int index = 0;
        Node current;

        for (int i = 0; i < buckets.length; i++) {
            value += i + ": ";
            if (buckets[i] != null) {
                current = (Node) buckets[i];
                while (current != null) {
                    value += current.value + " --> ";
                    current = current.next;
                    if (current == null) {
                        value = value.substring(0, value.length() - 5);
                    }
                }
            }
            value += "\n";
        }

        return value;
    }

    /**
     * This class implements the Iterator interface to iterate over the Hash Table.
     *
     * @author adriansmith
     */
    private class HashIterator implements Iterator<T> {
        // FIELDS //
        int initSize;
        int index;
        Node current;

        /**
         * Constructs the iterator at the beginning of the list and stores the initial
         * size so that it can throw a ConcurrentModificationException if any removals
         * or additions occer.
         */
        public HashIterator() {
            this.initSize = size;
            this.index = 0;
            current = (Node) buckets[index];
            while (current == null && index < buckets.length - 1 ) {
                current = (Node) buckets[++index];
            }
        }

        @Override
        /**
         * Checks to see if the iterator has reached the end of the structure.
         *
         * @return true if there is another element
         */
        public boolean hasNext() {
            if (initSize != size)
                throw new ConcurrentModificationException();

            if (current != null) {
                return true;
            }

            return false;
        }

        @Override
        /**
         * Returns the next value in the structure.
         *
         * @return the next value
         */
        public T next() {
            if (initSize != size)
                throw new ConcurrentModificationException();

            T value = current.value;

            if ((current = current.next) != null) {
                return value;
            } else {
                while (current == null && index != (buckets.length - 1)) {
                    current = (Node) buckets[++index];
                }
            }

            return value;
        }

    }

    /**
     * Custom Node class that stores it's value as a generic, a link to the next
     * node, and a key that also represents which index it's in.
     *
     * @author adriansmith
     */
    public class Node {
        int key;
        T value;
        Node next;

        /**
         * Constructs an empty node.
         */
        public Node() {
            this(null);
        }

        /**
         * Constructs a node with the parameter that's passed in.
         *
         * @param data The value being stored
         */
        public Node(T data) {
            this.value = data;
            this.key = this.hashCode();
            this.next = null;
        }

        @Override
        /**
         * Hashing method for the value stored in the Node object.
         *
         * @return the hashcode, or index that the Node will be stored in
         */
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return (result & 0x7fffffff) % buckets.length;
        }

        @Override
        /**
         * Checks to see if two Nodes are equal to each other.
         *
         * @return boolean, true if the Nodes being compared are equal.
         */
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Node other = (Node) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (value == null) {
                if (other.value != null)
                    return false;
            } else if (!value.equals(other.value))
                return false;
            return true;
        }

        /**
         * Gets the type that's holding the Node object.
         *
         * @return HashTable for equals method
         */
        private HashTable<T> getOuterType() {
            return HashTable.this;
        }
    }
}

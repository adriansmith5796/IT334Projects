package structures;

import interfaces.*;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class ChainedMap<K, V> implements Iterable<V>{
    /********* FIELDS *********/
    private Object[] buckets; // Array of Nodes
    private int size; // Number of elements in structure

    /**
     * Constructs the table with 31 buckets that are Linked Lists with the key being
     * the index that the buckets is.
     */
    public ChainedMap() {
        this.buckets = new Object[31];
        this.size = 0;
    }

    /**
     * Adds an element to the collection. No specific ordering is required.
     *
     * @param key the new element to put in the collection
     */
    public void add(K key, V value) {
        ChainedMap.Node newNode = new ChainedMap.Node(key, value);
        ChainedMap.Node current = (ChainedMap.Node) buckets[newNode.hash];
        ChainedMap.Node prev = (ChainedMap.Node) buckets[newNode.hash];

        if (current == null) {
            buckets[newNode.hash] = newNode;
            this.size++;
            return;
        }

        while (current != null) {
            if(current.key == key){
                current.value = value;
                return;
            }

            prev = current;
            current = current.next;
        }

        prev.next = newNode;
        this.size++;
    }

    /**
     * Finds and removes an element from the collection.
     *
     * @param key the key
     */
    public boolean remove(K key) {
        int hash = getHashCode(key);
        ChainedMap.Node current = (ChainedMap.Node) buckets[hash];
        ChainedMap.Node prev = null;

        while (current != null) {
            if (key == current.key) {
                if(prev == null)
                    buckets[hash] = null;
                else
                    prev.next = current.next;

                this.size--;
                return true;
            }

            prev = current;
            current = current.next;
        }

        return false;
    }

    /**
     * Reports whether the collection contains an element.
     *
     * @param key the key to search for.
     * @return true if the element is found, otherwise false
     */
    public boolean contains(K key) {
        if(this.isEmpty()) return false;

        int hash = getHashCode(key);
        ChainedMap.Node current = (ChainedMap.Node) buckets[hash];

        if (key == current.key)
            return true;

        while ((current = current.next) != null) {
            if (key == current.key)
                return true;
        }

        return false;
    }

    /**
     * Returns the number of elements in the collection.
     *
     * @return the number of elements
     */
    public int size() {
        return this.size;
    }

    /**
     * Reports whether the collection is empty or not.
     *
     * @return true if the collection is empty, otherwise false
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all elements from the collection.
     */
    public void clear() {
        this.buckets = new Object[13];
        this.size = 0;
    }

    /**
     * Returns an element in the collection that matches the input parameter
     * according the equals() method of the parameter.
     *
     * @param key an element to search for
     * @return a matching element
     */
    public V get(K key) {
        if(this.isEmpty()) return null;
        int hashcode = getHashCode(key);
        ChainedMap.Node current = (ChainedMap.Node) buckets[hashcode];

        if (key == current.key)
            return (V) current.value;

        while (current != null) {
            if (key == current.key) {
                return (V) current.value;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Returns an iterator over the collection.
     *
     * @return an object using the Iterator<T> interface
     */
    public Iterator<V> iterator() {
        return new ChainedMap.HashIterator();
    }

    /**
     * Returns a set of all keys in the map.
     * @return ISet object that has all of the keys being used in the map.
     */
    public ISet<K> keyset() {
        Set<K> keyset = new Set<>();
        ChainedMap.Node current;

        // Goes thru all nodes and adds their keys to the set.
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                current = (ChainedMap.Node) buckets[i];
                while (current != null) {
                    keyset.add((K) current.key);
                    current = current.next;
                }
            }
        }

        return keyset;
    }

    /**
     * Gets all values in the map and returns as an ICollection object.
     * @return All of the values in the map.
     */
    public ICollection<V> values() {
        HashTable<V> values = new HashTable<>();
        ChainedMap.Node current;

        // Goes thru all nodes and adds their keys to the set.
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                current = (ChainedMap.Node) buckets[i];
                while (current != null) {
                    values.add((V) current.value);
                    current = current.next;
                }
            }
        }

        return values;
    }

    /**
     * Returns the String representation of the Hash Table.
     *
     * @return String representing the table
     */
    public String toString() {
        String value = "";
        ChainedMap.Node current;

        for (int i = 0; i < buckets.length; i++) {
            value += i + ": ";
            if (buckets[i] != null) {
                current = (ChainedMap.Node) buckets[i];
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
     * Hashing method for the value stored in the Node object.
     *
     * @return the hashcode, or index that the Node will be stored in
     */
    private int getHashCode(K key) {
        final int prime = 31;
        int result = 1;
        result = prime * result + this.hashCode();
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return (result & 0x7fffffff) % buckets.length;
    }

    /**
     * This class implements the Iterator interface to iterate over the Hash Table.
     *
     * @author adriansmith
     */
    private class HashIterator implements Iterator<V> {
        // FIELDS //
        int initSize;
        int index;
        ChainedMap.Node current;

        /**
         * Constructs the iterator at the beginning of the list and stores the initial
         * size so that it can throw a ConcurrentModificationException if any removals
         * or additions occer.
         */
        public HashIterator() {
            this.initSize = size;
            this.index = 0;
            current = (ChainedMap.Node) buckets[index];
            while (current == null && index < buckets.length - 1) {
                current = (ChainedMap.Node) buckets[++index];
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
        public V next() {
            if (initSize != size)
                throw new ConcurrentModificationException();

            V value = (V) current.value;

            if ((current = current.next) != null) {
                return value;
            } else {
                while (current == null && index != (buckets.length - 1)) {
                    current = (ChainedMap.Node) buckets[++index];
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
        K key;
        V value;
        int hash;
        ChainedMap.Node next;

        /**
         * Constructs an empty node.
         */
        public Node() {
            this(null, null);
        }

        /**
         * Constructs a node with the parameters that are passed in and determines the index it will be hashed to.
         *
         * @param key   The key of the value
         * @param value The value associated with the key
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = this.hashCode();
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
            result = prime * result + ((key == null) ? 0 : key.hashCode());
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
            ChainedMap.Node other = (ChainedMap.Node) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (key == null) {
                if (other.key != null)
                    return false;
            } else if (!key.equals(other.key))
                return false;
            return true;
        }

        /**
         * Gets the type that's holding the Node object.
         *
         * @return HashTable for equals method
         */
        private ChainedMap<K, V> getOuterType() {
            return ChainedMap.this;
        }
    }
}

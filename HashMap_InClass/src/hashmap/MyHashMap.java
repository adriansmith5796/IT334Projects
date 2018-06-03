package hashmap;

/**
 * HashTable that uses linear probing for collision resolution
 * @param <K> key  (for example, flight numbers)
 * @param <V> value  (for example, flight data)
 */
public class MyHashMap<K, V> {

    // constants for managing the table
    private static final double LOAD_FACTOR = 0.7;
    private static final int INITIAL_TABLE_SIZE = 13;
    private static final double RESIZE_FACTOR = 2;

    private KVPair[] table;

    private int size; // number of actual active elements in table
    private int tombstones; // number of spaces that are active or inactive

    // constructor
    public MyHashMap() {
        this(INITIAL_TABLE_SIZE);
    }

    public MyHashMap(int tableSize) {
        table = new KVPair[tableSize];
    }

    // get size of table
    public int size() {
        return size;
    }

    // is table empty?
    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        size = 0;  // reset the active elements tracker
        tombstones = 0;  // reset the all elements tracker
        table = new KVPair[INITIAL_TABLE_SIZE];
    }

    // overload for users who want to specify a starting table size
    public void clear(int tableSize) {
        size = 0;  // reset the active elements tracker
        tombstones = 0;  // reset the all elements tracker
        table = new KVPair[tableSize];
    }


    // add using linear probing
    public void add(K key, V value) {
        // check that key is not null
        if(key == null) throw new IllegalArgumentException("Key cannot be null!");

        // check load factor to see if table needs to be resized
        if (tableSizeExceedsLF()) {
            resize();
        }

        int index = getHashIndex(key);

        /********** start: this is for testing purposes ******************/
        if (table[index] == null) {
            System.out.println("Found a space!");
        } else {
            System.out.println("Collision!");
        }
        /*********** end: for testing purposes ****************************/

        // sequentially probe table while slots contain active KVPairs
        // At each KVPair, check if it matches key -- if so, update and remove ASAP
        // TODO: change && -- need condition to short circuit if table[index] is null
        while (table[index] != null && table[index].active) {
            if(table[index] .key.equals(key)){
                table[index].value = value;
                table[index].active = true;
                return;
            }

            index = ++index % table.length;
        }

        // current key is not active, create new KVPair
        if(table[index] != null) tombstones--;
        table[index] = new KVPair(key, value);
        size++;
    }

    // Finds the table index based on the key
    private int getHashIndex(K key){
        int code = key.hashCode();
        return code % table.length;
    }

    /**
     * Finds the value for given key. If key doesn't exist, return null.
     * @param key
     * @return value if key exists, null if not
     */
    public V find(K key) {
        int index = getHashIndex(key);

        while (table[index] != null) {

            // is this index where the key resides?
            if (table[index].active && table[index].key.equals(key)) {
                return (V) table[index].value;
            }

            index = ++index % table.length ;  // otherwise, look at next slot
        }

        return null;
    }

    // for adds
    public boolean tableSizeExceedsLF() {
        return (double) size / table.length >= LOAD_FACTOR;
    }

    // resize the table
    private void resize() {
        //TODO
    }

    /**
     * remove value associated with a key from the table
     * Uses lazy deletion -- marks KVPair as inactive
     * Will be cleaned up on table resize
     */
    public void delete(K key){
        if(key == null) throw new IllegalArgumentException("Key cannot be null!");

        int index = getHashIndex(key);

        // sequentially probe table again while slots contain active KVPairs
        // At each KVPair, check if matches key -- if so, update it to inactive and return
        while(table[index] != null ){
            if(table[index].key.equals(key)){
                table[index].active = false;
                tombstones++;
                return;
            }
            index = ++index % table.length;
        }
    }

    /***** INNER CLASSES ************/

    // define inner class that contains both key K and value V
    private class KVPair<K, V> {

        private K key;
        private V value;

        // track whether this object is active (i.e., deleted or not)
        private boolean active;

        public KVPair(K key, V value) {
            this.key = key;
            this.value = value;
            this.active = true;
        }

        public String toString() {
            return value.toString();
        }
    }

}
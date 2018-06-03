/*
 * Adrian Smith
 * IT 334 Algorithms
 * This class is an implementation of the Set ADT.
 */
package structures;

import interfaces.ISet;

/**
 * This class uses the HashTable class as the underlying data
 * structure and provides methods that are a part of the Set ADT.
 *
 * @param <T> The data type of the objects being stored
 */
public class Set<T> extends HashTable<T> implements ISet<T> {

    /**
     * Constructs the class using it's parent's constructor.
     */
    public Set() {
        super();
    }

    @Override
    public ISet<T> union(ISet<T> other) {
        Set<T> newSet = new Set<>();

        for (T item : this) {
            newSet.add(item);
        }

        for (T item : other) {
            if(!newSet.contains(item))
                newSet.add(item);
        }

        return newSet;
    }

    @Override
    public ISet<T> intersects(ISet<T> other) {
        Set<T> newSet = new Set<>();

        for (T item : this)
            if (other.contains(item))
                newSet.add(item);

        return newSet;
    }

    @Override
    public ISet<T> difference(ISet<T> other) {
        Set<T> newSet = new Set<>();

        for (T item : this)
            if (!other.contains(item))
                newSet.add(item);

        return newSet;
    }

    @Override
    public boolean isSubset(ISet<T> other) {
        for (T item : other)
            if (!this.contains(item))
                return false;

        return true;
    }

    @Override
    public boolean isDisjoint(ISet<T> other) {

        for (T item : this) {
            if (other.contains(item))
                return false;
        }

        return true;
    }

    @Override
    public boolean isEmptySet() {
        return this.isEmpty();
    }
}

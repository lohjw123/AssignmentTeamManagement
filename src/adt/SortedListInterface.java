package adt;

import java.util.Iterator;

/**
 *
 * @author Loh JW
 */
public interface SortedListInterface<T extends Comparable<T>> {

    Iterator<T> getIterator();

    public boolean add(T newEntry);

    public boolean remove(T anEntry);

    public T get(T anEntry);

    public boolean contains(T anEntry);

    public void clear();

    public int getNumberOfEntries();

    public boolean isEmpty();

    public T replace(T anEntry, T anotherEntry);
}

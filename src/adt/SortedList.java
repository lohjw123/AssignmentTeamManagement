package adt;

import java.io.Serializable;
import java.util.Iterator;

/**
 *
 * @author Loh JW (Reference : Practical Chapter 7)
 */
public class SortedList<T extends Comparable<T>> implements SortedListInterface<T>, Serializable {

    private Node firstNode;
    private int numberOfEntries;

    @Override
    public boolean add(T newEntry) {
        if (newEntry != null && !contains(newEntry)) {
            Node newNode = new Node(newEntry);
            Node nodeBefore = null;
            Node currentNode = firstNode;
            while (currentNode != null && newEntry.compareTo(currentNode.data) > 0) {
                nodeBefore = currentNode;
                currentNode = currentNode.next;
            }
            if (isEmpty() || (nodeBefore == null)) {
                newNode.next = firstNode;
                firstNode = newNode;
            } else {
                newNode.next = currentNode;
                nodeBefore.next = newNode;
            }
            numberOfEntries++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(T anEntry) {
        boolean stop = false;
        Node previousNode = null;
        Node currentNode = firstNode;
        if (anEntry != null) {
            while (currentNode != null && !stop) {
                if (currentNode.data.compareTo(anEntry) >= 0) {
                    stop = true;
                } else {
                    previousNode = currentNode;
                    currentNode = currentNode.next;
                }
            }
            if (currentNode != null && currentNode.data.compareTo(anEntry) == 0) {
                if (currentNode == firstNode) {
                    firstNode = currentNode.next;
                } else {
                    previousNode.next = currentNode.next;
                }
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T get(T anEntry) {
        T currentElement = null;
        boolean stop = false;
        Node previousNode = null;
        Node currentNode = firstNode;
        while (currentNode != null && !stop) {
            if (currentNode.data.compareTo(anEntry) >= 0) {
                stop = true;
            } else {
                previousNode = currentNode;
                currentNode = currentNode.next;
            }
        }
        if (currentNode != null && currentNode.data.compareTo(anEntry) == 0) {
            currentElement = currentNode.data;
            return currentElement;
        }
        return currentElement;
    }

    @Override
    public boolean contains(T anEntry) {
        boolean found = false;
        Node tempNode = firstNode;

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
            }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getNumberOfEntries() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return (numberOfEntries == 0);
    }

    @Override
    public T replace(T anEntry, T anotherEntry) {
        boolean found = false;
        Node tempNode = firstNode;
        Node newNode = new Node(anotherEntry);

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
            }
        }

        while (!found && (tempNode != null)) {
            if (anEntry.compareTo(tempNode.data) <= 0) {
                found = true;
            } else {
                tempNode = tempNode.next;
            }
        }
        if (tempNode != null && tempNode.data.equals(anEntry)) {
            remove(tempNode.data);
            add(anotherEntry);
        } else {
            return anotherEntry;
        }
        return anEntry;
    }

    @Override
    public Iterator<T> getIterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T>, Serializable {

        private Node currentNode = firstNode;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public T next() {
            T currentElement = null;

            if (hasNext()) {
                currentElement = currentNode.data;
                currentNode = currentNode.next;
            }
            return currentElement;
        }
    }

    private class Node implements Serializable {

        private T data;
        private Node next;

        private Node(T data) {
            this.data = data;
            next = null;
        }

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}

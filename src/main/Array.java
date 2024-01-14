package main;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Array<T> implements Iterable<T> {
    private Object[] items;
    private int size;
    private static final int INITIAL_SIZE = 10;

    public Array() {
        items = new Object[INITIAL_SIZE];
        size = 0;
    }

    public void add(T item) {
        if (size == items.length) {
            resize();
        }
        items[size] = item;
        size++;
    }

    private void resize() {
        Object[] newItems = new Object[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }

    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    public int getSize() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        return (T) items[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) items[currentIndex++];
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }

        sb.append("]");
        return sb.toString();
    }
}

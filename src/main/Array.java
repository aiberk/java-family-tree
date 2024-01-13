package main;

public class Array {
    private int[] items;
    private int size;
    private static final int INITIAL_SIZE = 10;

    public Array() {
        items = new int[INITIAL_SIZE];
        size = 0;
    }

    public void add(int item) {
        if (size == items.length) {
            resize();
        }
        items[size] = item;
        size++;
    }

    private void resize() {
        int[] newItems = new int[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        items = newItems;
    }

    public int indexOf(int item) {
        for (int i = 0; i < size; i++) {
            if (items[i] == item) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public int getSize() {
        return size;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Invalid index");
        }
        return items[index];
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

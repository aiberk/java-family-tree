package main;

public class HashMapNode<K, V> {
    private K key;
    private V value;
    private boolean isActive;
    private HashMapNode<K, V> next;

    public HashMapNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.isActive = true;
        this.next = null;
    }

    // Getter and setter methods

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public HashMapNode<K, V> getNext() {
        return next;
    }

    public void setNext(HashMapNode<K, V> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HashMapNode{" +
                "key=" + key +
                ", value=" + value +
                ", isActive=" + isActive +
                '}';
    }
}

package main;

// TODO: Implement custom hash function
public class HashMap {
    private HashMapNode<String, Object>[] buckets;
    private int capacity;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @SuppressWarnings("unchecked")
    public HashMap() {
        capacity = INITIAL_CAPACITY;
        buckets = (HashMapNode<String, Object>[]) new HashMapNode[capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getBucketIndex(String key, int probeCount) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode + probeCount) % capacity;
    }

    public void put(String key, Object value) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null && !buckets[bucketIndex].getKey().equals(key)) {
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }

        if (buckets[bucketIndex] != null && buckets[bucketIndex].getKey().equals(key)) {
            buckets[bucketIndex].setValue(value); // Update existing
        } else {
            buckets[bucketIndex] = new HashMapNode<>(key, value); // Insert new
            size++;
        }
    }

    public Object get(String key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey().equals(key)) {
                return buckets[bucketIndex].getValue();
            }
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }
        return null;
    }

    public boolean containsKey(String key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey().equals(key)) {
                return true;
            }
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }
        return false;
    }

    /**
     * Resizes the hash table when the load factor threshold is exceeded.
     * This method doubles the capacity of the hash table.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        HashMapNode<String, Object>[] oldBuckets = buckets;
        buckets = (HashMapNode<String, Object>[]) new HashMapNode[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (HashMapNode<String, Object> node : oldBuckets) {
            if (node != null) {
                put(node.getKey(), node.getValue());
            }
        }
    }

    public void print() {
        for (HashMapNode<String, Object> node : buckets) {
            if (node != null) {
                System.out.println("Key: " + node.getKey() + ", Value: " + node.getValue());
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{");
        boolean isFirst = true;
        for (HashMapNode<String, Object> node : buckets) {
            if (node != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    builder.append(", ");
                }
                builder.append(node.getKey()).append(": ").append(node.getValue());
            }
        }
        builder.append("}");
        return builder.toString();
    }

}

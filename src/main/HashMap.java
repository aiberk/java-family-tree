package main;

public class HashMap {
    private HashMapNode[] buckets;
    private static final int INITIAL_CAPACITY = 16;
    private int size;

    public HashMap() {
        buckets = new HashMapNode[INITIAL_CAPACITY];
        size = 0;
    }

    private int getBucketIndex(String key, int probeCount) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode + probeCount) % buckets.length;
    }

    public void put(String key, Person value) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null && buckets[bucketIndex].getKey() != key.hashCode()) {
            bucketIndex = getBucketIndex(key, probeCount);
            probeCount++;
        }

        if (buckets[bucketIndex] != null && buckets[bucketIndex].getKey() == key.hashCode()) {
            buckets[bucketIndex].value = value; // Update existing
        } else {
            buckets[bucketIndex] = new HashMapNode(key.hashCode(), value); // Insert new
            size++;
        }
    }

    public Person get(String key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey() == key.hashCode()) {
                return buckets[bucketIndex].getValue();
            }
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
        }
        return null; // Not found
    }

    // Additional methods like remove, size, etc.
}

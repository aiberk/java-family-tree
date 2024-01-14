package main;

// TODO: Implement custom hash function
public class HashMap {
    private HashMapNode[] buckets;
    private int capacity;
    private int size;
    private static final int INITIAL_CAPACITY = 16;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    public static void main(String[] args) {
        HashMap nameMap = new HashMap(); // Initialize the HashMap
        Person person = new Person("John", "unknown", "unknown");
        Person person2 = new Person("Mary", "unknown", "John");
        Person person3 = new Person("John I", "Mary", "John");
        Person person4 = new Person("John II", "Mary", "John");
        nameMap.put(person.getName(), person);
        nameMap.put(person2.getName(), person2);
        nameMap.put(person3.getName(), person3);
        nameMap.put(person4.getName(), person4);
        nameMap.print();
    }

    public HashMap() {
        capacity = INITIAL_CAPACITY;
        buckets = new HashMapNode[capacity];
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

    public void put(String key, Person value) {
        if ((double) size / capacity >= LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null && buckets[bucketIndex].getKey() != key.hashCode()) {
            probeCount++;
            bucketIndex = getBucketIndex(key, probeCount);
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
        return null;
    }

    public boolean containsKey(String key) {
        int probeCount = 0;
        int bucketIndex = getBucketIndex(key, probeCount);
        while (buckets[bucketIndex] != null) {
            if (buckets[bucketIndex].getKey() == key.hashCode()) {
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
    private void resize() {
        int newCapacity = capacity * 2;
        HashMapNode[] oldBuckets = buckets;
        buckets = new HashMapNode[newCapacity];
        capacity = newCapacity;
        size = 0;
        for (HashMapNode node : oldBuckets) {
            if (node != null) {
                put(node.value.getName(), node.value);
            }
        }
    }

    public void print() {
        for (HashMapNode node : buckets) {
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
        for (HashMapNode node : buckets) {
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

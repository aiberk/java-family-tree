package main;

/**
 * Represents an entry in a hash map, holding a key-value pair.
 */
public class HashMapNode {
    /**
     * The key of the entry, represented by an int.
     * This is expected to be the hash value of the Person object.
     */
    public int key;

    /**
     * The value associated with the key in the hash map.
     * In this case, the value is a Person object.
     */
    public Person value;

    /**
     * Flag to check if the entry is active (not deleted).
     */
    public boolean isActive;

    /**
     * Reference to the next node in the chain. This is used in case of collisions.
     */
    public HashMapNode next;

    /**
     * Constructs an Entry with a specified key and value.
     * 
     * @param key   The int key of the entry, typically a hash of the Person.
     * @param value The Person to be associated with the key.
     */
    public HashMapNode(int key, Person value) {
        this.key = key;
        this.value = value;
        this.isActive = true;
        this.next = null;
    }

    // Getter for key
    public int getKey() {
        return key;
    }

    // Getter for value
    public Person getValue() {
        return value;
    }

    // Getter for isActive
    public boolean isActive() {
        return isActive;
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

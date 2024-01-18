package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Person;
import main.HashMapNode;

public class HashMapNodeTest {

    @SuppressWarnings("rawtypes")
    private HashMapNode node;
    private Person person;
    private int hashKey;

    @Before
    public void setUp() {
        person = new Person("Alice", "MotherName", "FatherName");
        hashKey = person.getName().hashCode();
        node = new HashMapNode<Integer, Person>(hashKey, person);
    }

    @Test
    public void testConstructor() {
        assertEquals(hashKey, node.getKey());
        assertSame(person, node.getValue());
        assertTrue(node.isActive());
    }

    @Test
    public void testToString() {
        String expectedString = "HashMapNode{" +
                "key=" + hashKey +
                ", value=" + person +
                ", isActive=" + true +
                '}';
        assertEquals(expectedString, node.toString());
    }

    @Test
    public void testGetKey() {
        assertEquals(hashKey, node.getKey());
    }

    @Test
    public void testGetValue() {
        assertSame(person, node.getValue());
    }

    @Test
    public void testIsActive() {
        assertTrue(node.isActive());
    }
}

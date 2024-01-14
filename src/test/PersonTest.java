package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Person;
import main.Array;

public class PersonTest {

    private Person person;
    @SuppressWarnings("unused")
    private Person mother;
    @SuppressWarnings("unused")
    private Person father;
    private Person child;

    @Before
    public void setUp() throws Exception {
        mother = new Person("Jane", null, null);
        father = new Person("John", null, null);
        person = new Person("Alice", "Jane", "John");
        child = new Person("Bob", "Alice", "Unknown");
    }

    @Test
    public void testGetName() {
        assertEquals("Alice", person.getName());
    }

    @Test
    public void testGetMother() {
        assertEquals("Jane", person.getMother());
    }

    @Test
    public void testGetFather() {
        assertEquals("John", person.getFather());
    }

    @Test
    public void testAddChildAndGetChildren() {
        person.addChild(child);
        Array<Person> children = person.getChildren();
        assertNotNull(children);
        assertEquals(1, children.getSize());
        assertSame(child, children.get(0));
    }

    @Test
    public void testToString() {
        assertEquals("Alice", person.toString());
    }
}

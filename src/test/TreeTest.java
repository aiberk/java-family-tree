package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Tree;
import main.Array;
import main.Person;
import main.HashMap; // Import your custom HashMap class

public class TreeTest {

    private Tree familyTree;
    private HashMap nameMap; // Declare a HashMap instance

    @Before
    public void setUp() {
        nameMap = new HashMap(); // Initialize the HashMap
        familyTree = new Tree(nameMap); // Pass the HashMap to Tree
        Person person = new Person("John", "unknown", "unknown");
        Person person2 = new Person("Mary", "unknown", "John");
        Person person3 = new Person("John I", "Mary", "John");
        Person person4 = new Person("John II", "Mary", "John");
        nameMap.put(person.getName(), person);
        nameMap.put(person2.getName(), person2);
        nameMap.put(person3.getName(), person3);
        nameMap.put(person4.getName(), person4);

        // Roots, children, grandchildren
        familyTree.addPerson("Mary", "Antonella", "John");
        familyTree.addPerson("Alice", "Mary", "Joe");
        familyTree.addPerson("Bob", "Alice", "Unknown");
    }

    @Test
    public void testAddingPersons() {
        fail("Implement this test");
    }

    @Test
    public void testGetDescendants() {
        fail("Implement this test");

    }

    @Test
    public void testGetAncestors() {
        fail("Implement this test");

    }
}

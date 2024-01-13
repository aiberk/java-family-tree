package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Tree;
import main.Array;
import main.Person;

public class TreeTest {

    private Tree familyTree;

    @Before
    public void setUp() {
        familyTree = new Tree();
        // Roots, children, grandchildren

        familyTree.addPerson("Mary", "Anotnella", "John");
        familyTree.addPerson("Alice", "Mary", "Joe");
        familyTree.addPerson("Bob", "Alice", "Unknown");
    }

    @Test
    public void testAddingPersons() {
        assertNotNull("John should be in the tree", familyTree.getPerson("John"));
        assertNotNull("Alice should be in the tree", familyTree.getPerson("Alice"));
    }

    @Test
    public void testGetDescendants() {
        Array<Person> descendantsOfJohn = familyTree.getDescendants("John");
        assertEquals("John should have 2 descendants", 2, descendantsOfJohn.getSize());
    }

    @Test
    public void testGetAncestors() {
        Array<Person> ancestorsOfAlice = familyTree.getAncestors("Alice");
        assertEquals("Alice should have 2 ancestors", 2, ancestorsOfAlice.getSize());
    }
}

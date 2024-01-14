package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Tree;
import main.TreeNode;
import main.Person;

public class TreeTest {
    private Tree<Person> tree;

    @Before
    public void setUp() {
        tree = new Tree<>();
    }

    @Test
    public void testAddPersonAndFindPersonNode() {
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, "John");
        tree.addPerson("Alice", "Mary", "John");

        // Check if persons are correctly added
        assertNotNull("John should be in the tree", tree.findPersonNode("John"));
        assertNotNull("Mary should be in the tree", tree.findPersonNode("Mary"));
        assertNotNull("Alice should be in the tree", tree.findPersonNode("Alice"));

        // Check parent-child relationships
        TreeNode<Person> johnNode = tree.findPersonNode("John");
        TreeNode<Person> maryNode = tree.findPersonNode("Mary");
        TreeNode<Person> aliceNode = tree.findPersonNode("Alice");

        assertTrue("John should be the father of Mary", maryNode.getFather() == johnNode);
        assertTrue("Mary should be the mother of Alice", aliceNode.getMother() == maryNode);
        assertTrue("John should be the father of Alice", aliceNode.getFather() == johnNode);
    }

    @Test
    public void testRootNode() {
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, "John");
        tree.addPerson("Alice", "Mary", "John");
        TreeNode<Person> rootNode = tree.getRoot();
        assertEquals("Root of the tree should be John", "John", rootNode.getData().getName());
    }

    // Additional tests can be added as needed
}

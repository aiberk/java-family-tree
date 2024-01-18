package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.Tree;
import main.TreeNode;
import main.Person;
import main.HashMap;

public class TreeTest {
    private Tree<Person> tree;
    private HashMap<String, TreeNode<Person>> familyMap;

    @Before
    public void setUp() {
        familyMap = new HashMap<>();
        tree = new Tree<>(familyMap);
    }

    @Test
    public void testAddPersonAndFindPersonNode() {
        tree.addPerson("John", null, null);
        tree.addPerson("Mary", null, "John");
        tree.addPerson("Alice", "Mary", "John");

        assertNotNull("John should be in the tree", tree.findPersonNode("John"));
        assertNotNull("Mary should be in the tree", tree.findPersonNode("Mary"));
        assertNotNull("Alice should be in the tree", tree.findPersonNode("Alice"));

        TreeNode<Person> johnNode = tree.findPersonNode("John");
        TreeNode<Person> maryNode = tree.findPersonNode("Mary");
        TreeNode<Person> aliceNode = tree.findPersonNode("Alice");

        assertEquals("Mary should be the mother of Alice", maryNode, aliceNode.getMother());
        assertEquals("John should be the father of Alice", johnNode, aliceNode.getFather());
    }

}

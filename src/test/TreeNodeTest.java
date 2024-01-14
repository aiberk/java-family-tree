package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import main.TreeNode;
import main.Person;

public class TreeNodeTest {
    private TreeNode<Person> rootNode;
    private Person rootPerson;
    private Person childPerson;

    @Before
    public void setUp() {
        // Setup for each test
        rootPerson = new Person("RootPerson", null, null);
        childPerson = new Person("ChildPerson", null, null);
        rootNode = new TreeNode<>(rootPerson);
    }

    @Test
    public void testAddChild() {
        TreeNode<Person> childNode = new TreeNode<>(childPerson);
        rootNode.addChild(childNode);

        // Assert that the child has been added
        assertEquals("Child count should be 1", 1, rootNode.getChildren().getSize());
        assertEquals("Child should be ChildPerson", "ChildPerson", rootNode.getChildren().get(0).getData().getName());
    }

    @Test
    public void testGetMotherAndFather() {
        TreeNode<Person> motherNode = new TreeNode<>(new Person("Mother", null, null));
        TreeNode<Person> fatherNode = new TreeNode<>(new Person("Father", null, null));

        rootNode.setMother(motherNode);
        rootNode.setFather(fatherNode);

        // Assert that mother and father are set correctly
        assertEquals("Mother should be set correctly", "Mother", rootNode.getMother().getData().getName());
        assertEquals("Father should be set correctly", "Father", rootNode.getFather().getData().getName());
    }

    @Test
    public void testGetData() {
        // Assert that root node data is correct
        assertEquals("Data should be RootPerson", "RootPerson", rootNode.getData().getName());
    }
}

package test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import main.Array;

public class ArrayTest {

    private Array array;

    @Before
    public void setUp() {
        array = new Array();
    }

    @Test
    public void testAddAndGetSize() {
        assertEquals(0, array.getSize());
        array.add(10);
        assertEquals(1, array.getSize());
        array.add(20);
        assertEquals(2, array.getSize());
    }

    @Test
    public void testGet() {
        array.add(30);
        array.add(40);
        assertEquals(30, array.get(0));
        assertEquals(40, array.get(1));
    }

    @Test
    public void testIndexOf() {
        array.add(50);
        array.add(60);
        assertEquals(0, array.indexOf(50));
        assertEquals(1, array.indexOf(60));
        assertEquals(-1, array.indexOf(70));
    }

    @Test
    public void testContains() {
        assertFalse(array.contains(100));
        array.add(100);
        assertTrue(array.contains(100));
        assertFalse(array.contains(200));
    }

    @Test
    public void testResize() {
        for (int i = 0; i < 20; i++) {
            array.add(i);
        }
        assertEquals(20, array.getSize());
        for (int i = 0; i < 20; i++) {
            assertEquals(i, array.get(i));
        }
    }

    @Test
    public void testToString() {
        assertEquals("[]", array.toString());
        array.add(1);
        assertEquals("[1]", array.toString());
        array.add(2);
        array.add(3);
        assertEquals("[1, 2, 3]", array.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGet() {
        array.get(0);
    }
}

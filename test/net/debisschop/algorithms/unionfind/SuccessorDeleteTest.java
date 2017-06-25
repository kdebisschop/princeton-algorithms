package net.debisschop.algorithms.unionfind;

import org.junit.Test;

import static org.junit.Assert.*;

public class SuccessorDeleteTest {
    @Test
    public void delete() {
        int size = 10;
        SuccessorDelete set = new SuccessorDelete(size);
        for (int i = 0; i <= size; i++) {
            assertTrue(set.isElement(i));
            assertEquals(i, set.successor(i));
        }
        for (int i = 0; i <= size; i++) set.delete(i);
        assertTrue(set.isElement(0));
        for (int i = 1; i <= size; i++) {
            assertFalse(set.isElement(i));
            assertEquals(0, set.successor(i));
        }
    }

    @Test
    public void successor1() {
        int size = 10;

        SuccessorDelete set;

        set = new SuccessorDelete(10);
        set.delete(0);
        assertEquals(0, set.successor(0));

    }

    @Test
    public void successor2() {
        int size = 10;

        SuccessorDelete set;
        set = new SuccessorDelete(10);
        set.delete(10);
        assertEquals(0, set.successor(10));

    }

    @Test
    public void successor3() {
        int size = 10;

        SuccessorDelete set;
        set = new SuccessorDelete(10);
        set.delete(5);
        assertEquals(6, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(10, set.successor(10));

        set.delete(6);
        assertEquals(7, set.successor(5));
        assertEquals(7, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(10, set.successor(10));

        set.delete(3);
        assertEquals(4, set.successor(3));
        assertEquals(7, set.successor(5));
        assertEquals(7, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(10, set.successor(10));

        set.delete(4);
        assertEquals(7, set.successor(3));
        assertEquals(7, set.successor(5));
        assertEquals(7, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(10, set.successor(10));

        set.delete(9);
        assertEquals(10, set.successor(9));
        assertEquals(7, set.successor(3));
        assertEquals(7, set.successor(5));
        assertEquals(7, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(10, set.successor(10));

        set.delete(10);
        assertEquals(0, set.successor(9));
        assertEquals(0, set.successor(10));
        assertEquals(7, set.successor(3));
        assertEquals(7, set.successor(5));
        assertEquals(7, set.successor(5));
        assertEquals(0, set.successor(0));
        assertEquals(0, set.successor(10));
    }
}

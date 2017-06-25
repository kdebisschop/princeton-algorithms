package net.debisschop.algorithms.unionfind;

import org.junit.Test;

import static org.junit.Assert.*;

public class WeightedQuickUnionTest {
    @Test
    public void connected() throws Exception {
        WeightedQuickUnion net;

        net = new WeightedQuickUnion(2);
    	assertFalse(net.connected(0, 1));
    	net.union(0, 1);
        assertTrue(net.connected(0, 1));

        int size = 10;
        net = new WeightedQuickUnion(size);
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (i == j) assertTrue(net.connected(i, j));
                else assertFalse(net.connected(i, j));
        net.union(4, 3);
        net.union(3, 8);
        net.union(6, 5);
        net.union(9, 4);
        net.union(2, 1);
        net.union(8, 9);
        net.union(5, 0);
        net.union(7, 2);
        net.union(6, 1);
        net.union(1, 0);
        net.union(6, 7);
        assertTrue(net.connected(4, 3));
        assertTrue(net.connected(8, 9));
        assertFalse(net.connected(7, 8));
    }

    @Test
    public void find() throws Exception {
        WeightedQuickUnion net;
        int size = 10;

        net = new WeightedQuickUnion(size);
        for (int i = 0; i < size; i++) assertEquals(i, net.find(i));
        net.union(4, 3);
        net.union(3, 8);
        net.union(6, 5);
        net.union(9, 4);
        net.union(2, 1);
        net.union(8, 9);
        net.union(5, 0);
        net.union(7, 2);
        net.union(6, 1);
        net.union(1, 0);
        net.union(6, 7);
        assertEquals(9, net.find(9));
        assertEquals(7, net.find(0));
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (net.connected(i, j)) assertEquals(net.find(i), net.find(j));
                else assertNotEquals(net.find(i), net.find(j));
    }

}

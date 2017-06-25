package net.debisschop.algorithms.percolation;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class PercolationTest {

  @Test
  public void construct() {
    try {
      new Percolation(0);
      fail("Should not be able to create 0x0 lattice");
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage(), is("Size of grid must be larger than 0"));
    }
    try {
      new Percolation(1);
    } catch (Exception e) {
      fail("Could not create 1x1 lattice");
    }
  }

  @Test
  public void isFull() {
    Percolation lattice;
    for (int size = 1; size <= 5; size++) {
      lattice = new Percolation(size);
      for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
          assertFalse(lattice.isFull(i, j));
          assertFalse(lattice.isOpen(i, j));
        }
      }
    }

    lattice = new Percolation(3);
    lattice.open(1, 1);
    lattice.open(2, 1);
    lattice.open(3, 1);
    lattice.open(2, 3);
    lattice.open(3, 3);
    assertFalse(lattice.isFull(2, 3));

    lattice = new Percolation(3);
    lattice.open(1, 3);
    lattice.open(2, 3);
    lattice.open(3, 3);
    lattice.open(2, 1);
    lattice.open(3, 1);
    assertTrue(lattice.isFull(2, 3));
  }

  @Test
  public void open() {
    Percolation lattice;
    for (int size = 1; size <= 5; size++) {
      lattice = new Percolation(size);
      for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
          assertFalse(lattice.isFull(i, j));
          assertFalse(lattice.isOpen(i, j));
          lattice.open(i, j);
          assertTrue(lattice.isOpen(i, j));
          assertTrue(lattice.isFull(i, j));
        }
      }
    }
  }

  @Test
  public void validateIndexes() {
    Percolation lattice;

    for (int size = 1; size < 6; size++) {
      lattice = new Percolation(size);
      try {
        lattice.open(0, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row must be greater than 0"));
      }
      try {
        lattice.open(1, 0);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column must be greater than 0"));
      }
      try {
        lattice.open(size + 1, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row is outside lattice"));
      }
      try {
        lattice.open(1, size + 1);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column is outside lattice"));
      }

      try {
        lattice.isOpen(0, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row must be greater than 0"));
      }
      try {
        lattice.isOpen(1, 0);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column must be greater than 0"));
      }
      try {
        lattice.isOpen(size + 1, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row is outside lattice"));
      }
      try {
        lattice.isOpen(1, size + 1);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column is outside lattice"));
      }

      try {
        lattice.isFull(0, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row must be greater than 0"));
      }
      try {
        lattice.isFull(1, 0);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column must be greater than 0"));
      }
      try {
        lattice.isFull(size + 1, 1);
        fail("Should not be able to query row 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Row is outside lattice"));
      }
      try {
        lattice.isFull(1, size + 1);
        fail("Should not be able to query column 0");
      } catch (IndexOutOfBoundsException e) {
        assertThat(e.getMessage(), is("Column is outside lattice"));
      }
    }
  }

  @Test
  public void numberOfOpenSites() {
    Percolation lattice;
    for (int size = 1; size <= 5; size++) {
      int openSites = 0;
      lattice = new Percolation(size);
      assertEquals(openSites, lattice.numberOfOpenSites());
      for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
          lattice.open(i, j);
          assertEquals(++openSites, lattice.numberOfOpenSites());
        }
      }
      for (int i = 1; i <= size; i++) {
        for (int j = 1; j <= size; j++) {
          lattice.open(i, j);
          assertEquals(openSites, lattice.numberOfOpenSites());
        }
      }
    }
  }

  @Test
  public void percolates() {
    Percolation lattice;

    lattice = new Percolation(1);
    assertFalse(lattice.percolates());
    assertEquals(0, lattice.numberOfOpenSites());
    lattice.open(1, 1);
    assertTrue(lattice.percolates());
    assertEquals(1, lattice.numberOfOpenSites());
    lattice.open(1, 1);
    assertTrue(lattice.percolates());
    assertEquals(1, lattice.numberOfOpenSites());

    lattice = new Percolation(3);
    lattice.open(1, 1);
    assertFalse(lattice.percolates());
    lattice.open(2, 1);
    assertFalse(lattice.percolates());
    lattice.open(3, 1);
    assertTrue(lattice.percolates());
    lattice.open(2, 3);
    assertTrue(lattice.percolates());
    lattice.open(3, 3);
    assertTrue(lattice.percolates());

    lattice = new Percolation(3);
    lattice.open(1, 3);
    assertFalse(lattice.percolates());
    lattice.open(2, 3);
    assertFalse(lattice.percolates());
    lattice.open(3, 3);
    lattice.open(3, 3);
    assertTrue(lattice.percolates());
    lattice.open(2, 1);
    assertTrue(lattice.percolates());
    lattice.open(3, 1);
    assertTrue(lattice.percolates());

    lattice = new Percolation(2);
    assertFalse(lattice.percolates());
    lattice.open(1, 1);
    assertEquals(1, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());
    lattice.open(2, 1);
    assertEquals(2, lattice.numberOfOpenSites());
    assertTrue(lattice.percolates());

    lattice = new Percolation(2);
    assertFalse(lattice.percolates());
    lattice.open(1, 1);
    assertEquals(1, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());
    lattice.open(1, 2);
    assertEquals(2, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice = new Percolation(2);
    assertFalse(lattice.percolates());
    lattice.open(1, 1);
    assertEquals(1, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());
    lattice.open(2, 2);
    assertEquals(2, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice = new Percolation(3);
    assertFalse(lattice.percolates());

    lattice.open(1, 1);
    assertEquals(1, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(1, 2);
    assertEquals(2, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(1, 3);
    assertEquals(3, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(3, 1);
    assertEquals(4, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(3, 2);
    assertEquals(5, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(3, 3);
    assertEquals(6, lattice.numberOfOpenSites());
    assertFalse(lattice.percolates());

    lattice.open(2, 2);
    assertEquals(7, lattice.numberOfOpenSites());
    assertTrue(lattice.percolates());

    lattice.open(2, 1);
    assertEquals(8, lattice.numberOfOpenSites());
    assertTrue(lattice.percolates());

    lattice.open(2, 3);
    assertEquals(9, lattice.numberOfOpenSites());
    assertTrue(lattice.percolates());
  }
}

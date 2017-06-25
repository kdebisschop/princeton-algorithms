package net.debisschop.algorithms.queues;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.Test;

public class DequeTest {

  @Test
  public void isEmpty() {
    Deque<Integer> deque = new Deque<>();
    assertTrue(deque.isEmpty());
    deque.addFirst(1);
    assertFalse(deque.isEmpty());
    deque.addFirst(1);
    assertFalse(deque.isEmpty());
    deque.addLast(1);
    assertFalse(deque.isEmpty());
    deque.addLast(2);
    assertFalse(deque.isEmpty());
  }

  @Test
  public void size() throws Exception {
    Deque<Integer> deque = new Deque<>();
    assertEquals(0, deque.size());

    for (int i = 1; i < 5; i++) {
      deque.addFirst(i);
      assertEquals(i, deque.size());
    }
    for (int i = 3; i >= 0; i--) {
      deque.removeLast();
      assertEquals(i, deque.size());
    }

    for (int i = 1; i < 5; i++) {
      deque.addFirst(i);
      assertEquals(i, deque.size());
    }
    for (int i = 3; i >= 0; i--) {
      deque.removeFirst();
      assertEquals(i, deque.size());
    }

    for (int i = 1; i < 5; i++) {
      deque.addLast(i);
      assertEquals(i, deque.size());
    }
    for (int i = 3; i >= 0; i--) {
      deque.removeLast();
      assertEquals(i, deque.size());
    }

    for (int i = 1; i < 5; i++) {
      deque.addLast(i);
      assertEquals(i, deque.size());
    }
    for (int i = 3; i >= 0; i--) {
      deque.removeFirst();
      assertEquals(i, deque.size());
    }

    deque.addFirst(1);
    assertEquals(1, deque.size());
    deque.addFirst(2);
    assertEquals(2, deque.size());
    deque.addLast(3);
    assertEquals(3, deque.size());
    deque.addLast(4);
    assertEquals(4, deque.size());

    deque.removeFirst();
    assertEquals(3, deque.size());
    deque.removeFirst();
    assertEquals(2, deque.size());
    deque.removeFirst();
    assertEquals(1, deque.size());
    deque.removeFirst();
    assertEquals(0, deque.size());
  }

  @Test
  public void addFirst() throws Exception {
    Deque<String> deque = new Deque<>();
    assertTrue(deque.isEmpty());

    try {
      deque.addFirst(null);
      fail("Cannot add null");
    } catch (IllegalArgumentException e) {
      assertTrue(deque.isEmpty());
    }

    deque.addFirst("Foo");
    assertFalse(deque.isEmpty());
  }

  @Test
  public void addLast() throws Exception {
    Deque<String> deque = new Deque<>();
    assertTrue(deque.isEmpty());

    try {
      deque.addLast(null);
      fail("Cannot add null");
    } catch (IllegalArgumentException e) {
      assertTrue(deque.isEmpty());
    }

    deque.addLast("Foo");
    assertFalse(deque.isEmpty());
  }

  @Test
  public void removeFirst() throws Exception {
    Deque<Integer> deque = new Deque<>();
    try {
      deque.removeFirst();
      fail("Cannot removeFirst() on empty collection");
    } catch (NoSuchElementException e) {
      assertTrue(deque.isEmpty());
    }
    assertEquals(0, deque.size());
    deque.addFirst(1);
    deque.addFirst(2);
    deque.addFirst(3);
    deque.addFirst(4);
    deque.addFirst(5);
    assertEquals((Integer)5, deque.removeFirst());
    assertEquals((Integer)1, deque.removeLast());
  }

  @Test
  public void removeLast() throws Exception {
    Deque<Integer> deque = new Deque<>();
    try {
      deque.removeLast();
      fail("Cannot removeLast() on empty collection");
    } catch (NoSuchElementException e) {
      assertTrue(deque.isEmpty());
    }
    assertEquals(0, deque.size());
    deque.addLast(1);
    deque.addLast(2);
    deque.addLast(3);
    deque.addLast(4);
    deque.addLast(5);
    assertEquals((Integer)1, deque.removeFirst());
    assertEquals((Integer)5, deque.removeLast());
  }

  @Test
  public void iterator() throws Exception {
    Deque<Integer> deque = new Deque<>();
    Iterator<Integer> iterator = deque.iterator();
    assertFalse(iterator.hasNext());
    try {
      iterator.next();
      fail("Cannot iterate empty collection");
    } catch (NoSuchElementException e) {
      assertFalse(iterator.hasNext());
    }

    deque.addFirst(1);
    iterator = deque.iterator();
    assertTrue(iterator.hasNext());
    assertEquals(1, (int)iterator.next());
    assertFalse(iterator.hasNext());

    deque.addFirst(2);
    deque.addFirst(1);
    iterator = deque.iterator();
    assertTrue(iterator.hasNext());
    assertEquals(1, (int)iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(2, (int)iterator.next());
    assertTrue(iterator.hasNext());
    assertEquals(1, (int)iterator.next());
    assertFalse(iterator.hasNext());
  }

  public void sample() {
    File file;
    Scanner scanner;
    ClassLoader classLoader = this.getClass().getClassLoader();
    try {
      file = new File(classLoader.getResource("tinyTale.txt").getFile());
      assertTrue(file.exists());
      scanner = new Scanner(file);
    } catch (NullPointerException e) {
      fail("Could not open file " + e.getMessage());
    } catch (FileNotFoundException e) {
      fail("File not found " + e.getMessage());
    }
  }

  @Test
  public void Test10() {
    int counter = 0;
    int iCounter;
    Iterator iterator;
    Deque<Integer> deque = new Deque<>();
    for (int i = 0; i < 200; i++) {
      int op = StdRandom.uniform(0, 4);
      switch (op) {
        case 0:
          deque.addFirst(i);
          counter++;
          break;
        case 1:
          deque.addLast(i);
          counter++;
          break;
        case 2:
          try {
            deque.removeFirst();
            counter--;
          } catch (Exception e) {
          }
          break;
        case 3:
          try {
            deque.removeLast();
            counter--;
          } catch (Exception e) {
          }
          break;
      }
      iterator = deque.iterator();
      iCounter = 0;
      while (iterator.hasNext()) {
        iterator.next();
        iCounter++;
      }
      assertEquals(String.format("%d %d %d%n", i, counter, iCounter), counter, iCounter);
    }
  }
}

package net.debisschop.algorithms.queues;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;

/**
 * Created by Karl DeBisschop on 2017-06-21.
 */
public class RandomizedQueueTest {

  @Test
  public void isEmpty() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    assertTrue(queue.isEmpty());
    queue.enqueue(1);
    assertFalse(queue.isEmpty());
    queue.dequeue();
    assertTrue(queue.isEmpty());
  }

  @Test
  public void size() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    int count = 0;
    for (int i = 0; i < 200; i++) {
      for (int j = i; j < i + 100; j++) {
        assertEquals(count, queue.size());
        queue.enqueue((Integer) j);
        count++;
      }
      for (int j = i + 100; j > i; j--) {
        assertEquals(count, queue.size());
        queue.dequeue();
        count--;
      }
    }
  }

  @Test
  public void enqueue() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    for (int i = 0; i < 200; i++) {
      queue.enqueue(i);
      assertEquals(i, (int)queue.dequeue());
    }
    for (int i = 0; i < 200; i++) {
      queue.enqueue(1);
    }
    for (int i = 0; i < 200; i++) {
      assertEquals(1, (int)queue.dequeue());
    }
  }

  @Test
  public void dequeue() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    try {
      queue.dequeue();
      fail("Cannot dequeue empty queue");
    } catch (NoSuchElementException e) {
      assertTrue(queue.isEmpty());
    }
  }

  @Test
  public void sample() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    try {
      queue.dequeue();
      fail("Cannot dequeue empty queue");
    } catch (NoSuchElementException e) {
      assertTrue(queue.isEmpty());
    }
    queue.enqueue(1);
    assertEquals((Integer)1, queue.sample());
    for (int h = 2; h < 5; h++) {
      queue.enqueue(h);
      for (int i = 1; i <= h; i++) {
        for (int j = 0; j < 500; j++) {
          if (queue.sample() == i) {
            break;
          }
          if (j == 499) {
            fail("sample() did not get " + i);
          }
        }
      }
    }
  }

  @Test
  public void iterator() throws Exception {
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    for (int i = 0; i < 100; i++) {
      queue.enqueue(i);
    }
    Iterator<Integer> iterator1 = queue.iterator();
    Iterator<Integer> iterator2 = queue.iterator();
    int index = 0;
    while (iterator1.hasNext()) {
      int i = iterator1.next();
      int j = iterator2.next();
      assertFalse(i == j && i == index);
      index++;
    }
    assertEquals(100, index);
    try {
      iterator1.next();
      fail("Cannot call next()");
    } catch (NoSuchElementException e) {
      assertFalse(iterator1.hasNext());
    }
  }

  @Test
  public void main() throws Exception {
  }


  @Test
  public void enqueueTiming() {
    long startTime;
    long endTime;
    long loops;
    RandomizedQueue<Integer> queue = new RandomizedQueue<>();
    for (int i = 10; i < 24; i++) {
      loops = (long)Math.pow(2, i);
      startTime = System.currentTimeMillis();
      for (int j = 0; j < loops; j++) {
        int op = StdRandom.uniform(0, 2);
        switch (op) {
          case 0:
            queue.enqueue(i);
            break;
          case 1:
            try {
              queue.dequeue();
            } catch (Exception e) {
            }
            break;
        }
        queue.enqueue(i);
        queue.sample();
        queue.size();
      }
      endTime = System.currentTimeMillis();
      System.out.printf("Test %10d execution time %10d ms%n", loops, endTime - startTime);
    }
  }
}

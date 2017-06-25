package net.debisschop.algorithms.queues;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private int count = 0;
  private int head = 0;
  private int tail = 0;
  private Item[] queue;

  /**
   * Construct an empty randomized queue.
   */
  public RandomizedQueue() {
    queue = (Item[]) new Object[1];
  }

  /**
   * @return True if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * @return the number of items on the queue
   */
  public int size() {
    return count;
  }

  /**
   * Adds an item to the queue.
   * @param item the item to add
   */
  public void enqueue(Item item) {
    validate(item);
    if (queue.length == tail) {
      resize(2 * tail);
    }
    int index = StdRandom.uniform(head, tail + 1);
    queue[tail] = queue[index];
    queue[index] = item;
    tail++;
    count++;
  }

  /**
   * Remove and return a random item.
   * @return the item that was removed
   */
  public Item dequeue() {
    if (size() == 0) throw new NoSuchElementException();
    Item item = queue[head];
    queue[head] = null;
    head++;
    count--;
    if (tail > head && head * 4 > queue.length) resize((tail - head) * 2);
    return item;
  }

  /**
   * Gets a random item from the queue without removing it.
   * @return a random item
   */
  public Item sample() {
    if (size() == 0) throw new NoSuchElementException();
    return queue[StdRandom.uniform(head, tail)];
  }

  /**
   * @return An independent iterator over items in random order
   */
  public Iterator<Item> iterator() {
    Item[] copy = (Item[]) new Object[size()];
    System.arraycopy(queue, head, copy, 0, tail - head);
    StdRandom.shuffle(copy);
    return new Iterator<Item>() {
      private int current = 0;

      @Override
      public boolean hasNext() { return current < size(); }

      @Override
      public Item next() {
        if (!this.hasNext()) throw new NoSuchElementException();
        current++;
        return copy[current - 1];
      }

      @Override
      public void remove() { throw new UnsupportedOperationException(); }
    };
  }

  /**
   * Resize the storage array.
   * @param size desired storage size
   */
  private void resize(int size) {
    Item[] copy = (Item[]) new Object[size];
    int length = tail - head;
    System.arraycopy(queue, head, copy, 0, length);
    queue = copy;
    tail = length;
    head = 0;
  }

  private void validate(Item item) {
    if (item == null) throw new IllegalArgumentException();
  }
}

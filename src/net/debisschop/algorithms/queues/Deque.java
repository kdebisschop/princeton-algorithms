package net.debisschop.algorithms.queues;

import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
  private int count;
  private Node first;
  private Node last;

  /**
   * Constructs an empty deque.
   */
  public Deque() {
    count = 0;
    first = null;
    last = null;
  }

  /**
   * @return true if empty, false otherwise
   */
  public boolean isEmpty() {
    return size() == 0;
  }

  /**
   * @return number of items on the deque
   */
  public int size() {
    return count;
  }

  /**
   * Adds an item to the front of the deque.
   *
   * @param item an Object to add to the deque
   */
  public void addFirst(Item item) {
    validateItem(item);
    Node node = new Node();
    node.item = item;
    node.prev = null;
    if (isEmpty()) {
      node.next = null;
      first = node;
      last = node;
    } else {
      node.next = first;
      first.prev = node;
      first = node;
    }
    count++;
  }

  /**
   * Adds an item to the end of the deque.
   *
   * @param item an Object to add to the deque
   */
  public void addLast(Item item) {
    validateItem(item);
    Node node = new Node();
    node.item = item;
    if (isEmpty()) {
      first = node;
      last = node;
    } else {
      node.prev = last;
      last.next = node;
      last = node;
    }
    count++;
  }

  /**
   * Removes an item from the front of the deque.
   *
   * @return the item that was previously at the front of the deque
   */
  public Item removeFirst() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = first.item;
    count--;
    if (isEmpty()) {
      reset();
    } else {
      first = first.next;
      first.prev = null;
    }
    return item;
  }

  /**
   * Removes an item from the end of the deque.
   *
   * @return the item that was previously at the end of the deque
   */
  public Item removeLast() {
    if (isEmpty()) throw new NoSuchElementException();
    Item item = last.item;
    count--;
    if (isEmpty()) {
      reset();
    } else {
      last = last.prev;
      last.next = null;
    }
    return item;
  }

  private void reset() {
    first = null;
    last = null;
  }

  public Iterator<Item> iterator() {
    return new Iterator<Item>() {
      private Node pointer = first;

      @Override
      public boolean hasNext() {
        return this.pointer != null;
      }

      @Override
      public Item next() {
        if (!this.hasNext()) throw new NoSuchElementException();
        Item item = this.pointer.item;
        this.pointer = this.pointer.next;
        return item;
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException();
      }
    };
  }        // return an iterator over items in order from front to end

  // public static void main(String[] args) {}  // unit testing (optional)

  private void validateItem(Item item) {
    if (item == null) throw new IllegalArgumentException();
  }

  private class Node {
    private Item item;
    private Node prev = null;
    private Node next = null;
  }
}

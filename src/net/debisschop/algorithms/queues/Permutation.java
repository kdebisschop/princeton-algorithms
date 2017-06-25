package net.debisschop.algorithms.queues;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
  public static void main(String[] args)
  {
    String token;
    RandomizedQueue<String> queue = new RandomizedQueue<>();
    while (!StdIn.isEmpty()) {
      token = StdIn.readString();
      queue.enqueue(token);
    }
    int limit = Integer.parseInt(args[0]);
    int count = 0;
    for (String string : queue) {
      count++;
      if (count > limit) break;
      StdOut.println(string);
    }
  }
}

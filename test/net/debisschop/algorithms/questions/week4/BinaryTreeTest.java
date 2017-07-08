package net.debisschop.algorithms.questions.week4;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

/**
 * Created by Karl DeBisschop on 2017-07-08.
 */
class BinaryTreeTest {

  @Test
  void put() {
    BinaryTree<Integer, Integer> tree = new BinaryTree<>();
    tree.put(1, 1);
  }

  @Test
  void get() {
    BinaryTree<Integer, Integer> tree = new BinaryTree<>();
    tree.put(5, 5);
    tree.put(9, 9);
    tree.put(1, 1);
    tree.put(6, 6);
    tree.put(3, 3);
    assertThat(tree.get(1), is(1));
    assertThat(tree.get(5), is(5));
    assertThat(tree.get(9), is(9));
    assertThat(tree.get(6), is(6));
    assertThat(tree.get(3), is(3));
    assertNull(tree.get(111));
  }

  @Test
  void isSearchTree() {
    BinaryTree<Integer, Integer> tree = new BinaryTree<>();
    tree.put(5, 5);
    tree.put(9, 9);
    tree.put(1, 1);
    tree.put(6, 6);
    tree.put(3, 3);
    assertThat(tree.isSearchTree(), is(true));
    tree.exch();
    assertThat(tree.isSearchTree(), is(false));
  }

}

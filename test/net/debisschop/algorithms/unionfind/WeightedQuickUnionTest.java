package net.debisschop.algorithms.unionfind;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WeightedQuickUnionTest {

  @Test
  public void connected() throws Exception {
    WeightedQuickUnion net;

    net = new WeightedQuickUnion(2);
    assertThat(net.connected(0, 1), is(false));
    net.union(0, 1);
    assertThat(net.connected(0, 1), is(true));

    int size = 10;
    net = new WeightedQuickUnion(size);
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (i == j) {
          assertThat(net.connected(i, j), is(true));
        } else {
          assertThat(net.connected(i, j), is(false));
        }
      }
    }
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
    assertThat(net.connected(4, 3), is(true));
    assertThat(net.connected(8, 9), is(true));
    assertThat(net.connected(7, 8), is(false));
  }

  @Test
  public void find() throws Exception {
    WeightedQuickUnion net;
    int size = 10;

    net = new WeightedQuickUnion(size);
    for (int i = 0; i < size; i++) {
      assertThat(net.find(i), is(i));
    }
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
    assertThat(net.find(9), is(9));
    assertThat(net.find(0), is(7));
    for (int i = 0; i < size; i++)
      for (int j = 0; j < size; j++)
        if (net.connected(i, j)) {
          assertThat(net.find(j), is(net.find(i)));
        } else
          assertThat(net.find(j), not(is(net.find(i))));
  }

}

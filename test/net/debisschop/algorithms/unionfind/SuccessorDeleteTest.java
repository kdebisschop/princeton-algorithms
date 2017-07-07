package net.debisschop.algorithms.unionfind;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SuccessorDeleteTest {

  @Test
  public void find() throws Exception {
    int size = 10;
    SuccessorDelete set = new SuccessorDelete(size);
    for (int i = 0; i < size; i++) {
      assertThat("find() fails on new set", set.find(i), is(i));
    }
  }

  @Test
  public void delete() {
    int size = 10;
    SuccessorDelete set = new SuccessorDelete(size);
    for (int i = 0; i <= size; i++) {
      assertThat(set.isElement(i), is(true));
      assertThat(set.successor(i), is(i));
    }
    for (int i = 0; i <= size; i++) {
      set.delete(i);
    }
    assertThat(set.isElement(0), is(true));
    for (int i = 1; i <= size; i++) {
      assertThat(set.isElement(i), is(false));
      assertThat(set.successor(i), is(0));
    }
  }

  @Test
  public void successor1() {
    int size = 10;

    SuccessorDelete set;

    set = new SuccessorDelete(10);
    set.delete(0);
    assertThat(set.successor(0), is(0));

  }

  @Test
  public void successor2() {
    int size = 10;

    SuccessorDelete set;
    set = new SuccessorDelete(10);
    set.delete(10);
    assertThat(set.successor(10), is(0));

  }

  @Test
  public void successor3() {
    int size = 10;

    SuccessorDelete set;
    set = new SuccessorDelete(10);
    set.delete(5);
    assertThat(set.successor(5), is(6));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(10));

    set.delete(6);
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(10));

    set.delete(3);
    assertThat(set.successor(3), is(4));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(10));

    set.delete(4);
    assertThat(set.successor(3), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(10));

    set.delete(9);
    assertThat(set.successor(9), is(10));
    assertThat(set.successor(3), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(10));

    set.delete(10);
    assertThat(set.successor(9), is(0));
    assertThat(set.successor(10), is(0));
    assertThat(set.successor(3), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(5), is(7));
    assertThat(set.successor(0), is(0));
    assertThat(set.successor(10), is(0));
  }
}

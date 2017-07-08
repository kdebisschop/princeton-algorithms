package net.debisschop.algorithms.questions.week4;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;


public class AutoboxingTest {
  @Test
  void negativeZero() {
    Double x = 0.0;
    Double y = -0.0;
    double a = x;
    double b = y;
    assertThat(a == b, is(true));
    assertThat(x.equals(y), is(false));
  }

  @Test
  void notANumber() {
    Double x = Double.NaN;
    Double y = x;
    double a = x;
    double b = x;
    assertThat(a == b, is(false));
    assertThat(x.equals(y), is(true));
  }
}

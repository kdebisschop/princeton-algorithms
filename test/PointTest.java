import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

import java.util.Comparator;
import org.junit.Test;

public class PointTest {

  @Test
  public void slopeTo() {
    Point p1, p2;
    p1 = new Point(0, 0);
    assertThat("Degenerate point should return NEGATIVE_INFINITY", p1.slopeTo(p1), is(Double.NEGATIVE_INFINITY));

    p2 = new Point(0, 0);
    assertThat("Degenerate point should return NEGATIVE_INFINITY", p1.slopeTo(p2), is(Double.NEGATIVE_INFINITY));

    p2 = new Point(1, 1);
    assertThat("Slope should be 1.0", p1.slopeTo(p2), is(1.0));

    p2 = new Point(-1, 1);
    assertThat("Slope should be -1.0", p1.slopeTo(p2), is(-1.0));

    p2 = new Point(0, 1);
    assertThat("Same x coordinate should return POSITIVE_INFINITY", p1.slopeTo(p2), is(Double.POSITIVE_INFINITY));

    p2 = new Point(32767, 32767);
    assertThat("Slope should be 1.0", p1.slopeTo(p2), is(1.0));

    p2 = new Point(32767, 0);
    assertThat("Slope should be 0.0", p1.slopeTo(p2), is(0.0));

    p2 = new Point(0, 32767);
    assertThat("Same x coordinate should return POSITIVE_INFINITY", p1.slopeTo(p2), is(Double.POSITIVE_INFINITY));

    assertThat("Check floating point", p1.slopeTo(new Point(2, 1)), is(0.5));
    assertThat("Check floating point", p1.slopeTo(new Point(4, 1)), is(0.25));
    assertThat("Check floating point", p1.slopeTo(new Point(8, 1)), is(0.125));
    assertThat("Check floating point", p1.slopeTo(new Point(8, 1)), is(0.125));
    assertThat("Check floating point", p1.slopeTo(new Point(10, 1)), is(0.1));
    assertThat("Check floating point", p1.slopeTo(new Point(100, 1)), is(0.01));
    assertThat("Check floating point", p1.slopeTo(new Point(1000, 1)), is(0.001));
    assertThat("Check floating point", p1.slopeTo(new Point(10000, 1)), is(0.0001));
  }

  @Test
  public void compareTo() {
    Point p1, p2;
    p1 = new Point(0, 0);
    assertThat("Degenerate point should return 0", p1.compareTo(p1), is(0));

    p2 = new Point(0, 0);
    assertThat("Degenerate point should return 0", p1.compareTo(p2), is(0));

    p2 = new Point(0, 1);
    assertThat("New point y is larger", p1.compareTo(p2), lessThan(0));

    p2 = new Point(-1, 1);
    assertThat("New point y is larger", p1.compareTo(p2), lessThan(0));

    p2 = new Point(1, 0);
    assertThat("New point x is larger", p1.compareTo(p2), lessThan(0));

    p2 = new Point(1, 1);
    assertThat("New point y is larger", p1.compareTo(p2), lessThan(0));

    p2 = new Point(0, -1);
    assertThat("New point y is smaller", p1.compareTo(p2), greaterThan(0));

    p2 = new Point(1, -1);
    assertThat("New point y is smaller", p1.compareTo(p2), greaterThan(0));

    p2 = new Point(-1, 0);
    assertThat("New point x is smaller", p1.compareTo(p2), greaterThan(0));

    p2 = new Point(-1, -1);
    assertThat("New point y is smaller", p1.compareTo(p2), greaterThan(0));

    p2 = new Point(32767, 32767);
    assertThat("New point x is larger", p1.compareTo(p2), lessThan(0));
    p2 = new Point(0, 32767);
    assertThat("New point x is larger", p1.compareTo(p2), lessThan(0));
    p2 = new Point(32767, 0);
    assertThat("New point x is larger", p1.compareTo(p2), lessThan(0));
  }

  @Test
  public void slopeOrder() {
    Point p0, p1, p2;
    p0 = new Point(0, 0);
    Comparator<Point> comparator;
    comparator = p0.slopeOrder();

    p1 = new Point(1, 1);
    p2 = new Point(2, 2);
    assertThat("slopes are equal", comparator.compare(p1, p2), is(0));

    p1 = new Point(1, 1);
    p2 = new Point(2, 1);
    assertThat("p1 has a greater slope", comparator.compare(p1, p2), greaterThan(0));
    assertThat("p1 has a greater slope", comparator.compare(p2, p1), lessThan(0));
  }
}

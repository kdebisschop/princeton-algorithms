import static edu.princeton.cs.algs4.StdOut.println;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * Created by Karl DeBisschop on 2017-06-30.
 */
public class BruteCollinearPointsTest {

  @Test
  public void numberOfSegments() throws Exception {
    Point[] points = new Point[4];
    points[0] = new Point(0, 0);
    points[1] = new Point(1, 1);
    points[2] = new Point(2, 2);
    points[3] = new Point(3, 3);
    BruteCollinearPoints segments = new BruteCollinearPoints(points);
    segments.segments();
    assertThat(segments.numberOfSegments(), is(1));
  }

  @Test
  public void segments() throws Exception {
    BruteCollinearPoints collinearPoints;

    Point[] points = new Point[4];
    points[0] = new Point(1, 0);
    points[1] = new Point(2, 1);
    points[3] = new Point(3, 2);
    points[2] = new Point(4, 3);
    BruteCollinearPoints brute = new BruteCollinearPoints(points);
    LineSegment[] segments = brute.segments();
    assertThat(segments[0].toString(), is((new LineSegment(new Point(1, 0), new Point(4, 3))).toString()));

    points[2] = new Point(3, 2);
    try {
      new BruteCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points[2] = null;
    try {
      new BruteCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = null;
    try {
      new BruteCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = new Point[8];
    points[0] = new Point(10000, 0);
    points[1] = new Point(0, 10000);
    points[2] = new Point(3000, 7000);
    points[3] = new Point(7000, 3000);
    points[4] = new Point(20000, 21000);
    points[5] = new Point(3000, 4000);
    points[6] = new Point(14000, 15000);
    points[7] = new Point(6000, 7000);
    collinearPoints = new BruteCollinearPoints(points);
    assertThat("Check count", collinearPoints.numberOfSegments(), is(2));
    //for (LineSegment seg : collinearPoints.segments()) println(seg);

    In in = new In("test/net/debisschop/algorithms/collinear/equidistant.txt");
    int n = in.readInt();
    points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    collinearPoints = new BruteCollinearPoints(points);
    //for (LineSegment segment : collinearPoints.segments()) println(segment.toString());
    assertThat("Check count", collinearPoints.numberOfSegments(), is(4));
  }

}

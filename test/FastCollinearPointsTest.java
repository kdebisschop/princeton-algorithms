import static edu.princeton.cs.algs4.StdOut.print;
import static edu.princeton.cs.algs4.StdOut.println;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

public class FastCollinearPointsTest {

  @Test
  public void numberOfSegments() throws Exception {
    Point[] points;
    FastCollinearPoints segments;

    points = new Point[4];
    points[0] = new Point(1, 1);
    points[1] = new Point(2, 2);
    points[2] = new Point(3, 3);
    points[3] = new Point(4, 4);
    segments = new FastCollinearPoints(points);
    segments.segments();
    assertThat(segments.numberOfSegments(), is(1));

    points = new Point[4];
    points[0] = new Point(0, 0);
    points[1] = new Point(1, 1);
    points[2] = new Point(2, 2);
    points[3] = new Point(3, 3);
    segments = new FastCollinearPoints(points);
    segments.segments();
    assertThat(segments.numberOfSegments(), is(1));

    points = new Point[5];
    points[0] = new Point(0, 0);
    points[1] = new Point(1, 1);
    points[2] = new Point(2, 2);
    points[3] = new Point(3, 3);
    points[4] = new Point(4, 4);
    segments = new FastCollinearPoints(points);
    //for (LineSegment seg : segments.segments()) println(seg);
    assertThat(segments.numberOfSegments(), is(1));
  }

  @Test
  public void segments() throws Exception {
    Point[] points;
    FastCollinearPoints collinearPoints;

    points = new Point[4];
    points[0] = new Point(1, 0);
    points[1] = new Point(2, 1);
    points[3] = new Point(3, 2);
    points[2] = new Point(4, 3);
    collinearPoints = new FastCollinearPoints(points);
    LineSegment[] segments = collinearPoints.segments();
    assertThat(segments[0].toString(), is((new LineSegment(new Point(1, 0), new Point(4, 3))).toString()));

    points[2] = new Point(3, 2);
    try {
      new FastCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points[2] = null;
    try {
      new FastCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = null;
    try {
      new FastCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = new Point[3];
    points[0] = new Point(23264, 4126);
    points[1] = new Point(23264, 4126);
    points[2] = new Point(19505, 10636);
    try {
      new FastCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = new Point[2];
    points[0] = new Point(23264, 4126);
    points[1] = new Point(23264, 4126);
    try {
      new FastCollinearPoints(points);
      fail("Should have thrown exception");
    } catch (IllegalArgumentException e) {
      assertThat("Expected exception was encountered", true);
    }

    points = new Point[6];
    points[0] = new Point(10000, 19000);
    points[1] = new Point(10000, 18000);
    points[2] = new Point(10000, 32000);
    points[3] = new Point(10000, 21000);
    points[4] = new Point( 1234, 5678);
    points[5] = new Point(10000, 14000);
    collinearPoints = new FastCollinearPoints(points);
//    for (LineSegment segment : collinearPoints.segments()) println(segment);
    assertThat("Check count", collinearPoints.numberOfSegments(), is(1));

    collinearPoints = new FastCollinearPoints(readPoints("equidistant.txt"));
//    for (LineSegment segment : collinearPoints.segments()) println(segment);
    assertThat("Check count", collinearPoints.numberOfSegments(), is(4));

    collinearPoints = new FastCollinearPoints(readPoints("input6.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(1));

    collinearPoints = new FastCollinearPoints(readPoints("input8.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(2));

    collinearPoints = new FastCollinearPoints(readPoints("input20.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(5));

    collinearPoints = new FastCollinearPoints(readPoints("input48.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(6));

    collinearPoints = new FastCollinearPoints(readPoints("input50.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(7));

    collinearPoints = new FastCollinearPoints(readPoints("input80.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(31));

    collinearPoints = new FastCollinearPoints(readPoints("kw1260.txt"));
    assertThat("Check count", collinearPoints.numberOfSegments(), is(288));

    collinearPoints = new FastCollinearPoints(readPoints("rs1423.txt"));
//    for (LineSegment segment : collinearPoints.segments()) println(segment);
    assertThat("Check count", collinearPoints.numberOfSegments(), is(443));
  }

  private Point[] readPoints(String file) {
    In in = new In("test/net/debisschop/algorithms/collinear/" + file);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    return points;
  }
}

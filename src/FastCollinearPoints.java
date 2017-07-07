import static edu.princeton.cs.algs4.StdOut.printf;

import edu.princeton.cs.algs4.Stack;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

  private Stack<Point[]> segments = new Stack<>();

  // finds all line segments containing 4 or more points
  public FastCollinearPoints(Point[] points) {
    if (points == null) throw new IllegalArgumentException();
    Point[] copy = points.clone();
    for (Point point : copy) {
      if (point == null) {
        throw new IllegalArgumentException();
      }
    }

    int numberOfPoints = copy.length;

    Arrays.sort(copy, new Comparator<Point>() {
      @Override
      public int compare(Point o1, Point o2) {
        return o1.compareTo(o2);
      }
    });
    for (int i = 1; i < numberOfPoints; i++) {
      if (copy[i].compareTo(copy[i-1]) == 0) throw new IllegalArgumentException();
    }

    if (numberOfPoints < 4) return;

    Point temp;
    for (int i = 0; i < copy.length - 3; i++) {
      Point origin = copy[i];
      numberOfPoints--;
      Point[] working = new Point[numberOfPoints];
      System.arraycopy(copy, i + 1, working, 0, numberOfPoints);
      Arrays.sort(working, origin.slopeOrder());
      double slope = origin.slopeTo(working[0]);
      Point min = origin;
      Point max = origin;
      int counter = 1;
      for (int j = 0; j < numberOfPoints; j++) {
        temp = working[j];
        double newSlope = origin.slopeTo(temp);
        if (slope != newSlope) {
          tryToAdd(counter, min, max, slope);
          slope = newSlope;
          min = origin;
          max = origin;
          counter = 1;
        }
        counter++;
        if (min.compareTo(temp) > 0) min = temp;
        else if (max.compareTo(temp) < 0) max = temp;
      }
      tryToAdd(counter, min, max, slope);
    }
  }

  private boolean isCounted(Point max, double slope) {
    for (Point[] seg : segments) {
      if (seg[1].compareTo(max) == 0 && max.slopeTo(seg[0]) == slope) return true;
    }
    return false;
  }

  private void tryToAdd(int counter, Point min, Point max, double slope) {
    if (counter >= 4 && !isCounted(max, slope)) addSegment(min, max);
  }

  private void addSegment(Point min, Point max) {
    Point[] p = new Point[2];
    p[0] = min;
    p[1] = max;
    this.segments.push(p);
  }

  // the number of line segments
  public int numberOfSegments() {
    return this.segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    int size = this.segments.size();
    LineSegment[] segmentArray = new LineSegment[size];
    int i = 0;
    for (Point[] seg : segments) {
      segmentArray[i] = new LineSegment(seg[0], seg[1]);
      i++;
    }
    return segmentArray;
  }
}

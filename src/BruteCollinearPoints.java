import edu.princeton.cs.algs4.Stack;

public class BruteCollinearPoints {

  private Stack<LineSegment> segments = new Stack<>();
  private Point[] pts;

  // finds all line segments containing 4 pts
  public BruteCollinearPoints(Point[] points) {
    if (points == null) {
      throw new IllegalArgumentException();
    }
    pts = points.clone();
    for (Point point : pts) {
      if (point == null) {
        throw new IllegalArgumentException();
      }
    }

    Point[] indexes = new Point[4];
    for (int h = 0; h < pts.length; h++) {
      indexes[0] = pts[h];
      for (int i = h + 1; i < pts.length; i++) {
        testEquality(h, i);
        indexes[1] = pts[i];
        for (int j = i + 1; j < pts.length; j++) {
          testEquality(h, j);
          testEquality(i, j);
          indexes[2] = pts[j];
          for (int k = j + 1; k < pts.length; k++) {
            testEquality(h, k);
            testEquality(i, k);
            testEquality(j, k);
            indexes[3] = pts[k];
            if (isCollinear(indexes)) segments.push(orderedSegment(indexes));
          }
        }
      }
    }
  }

  // the number of line segments
  public int numberOfSegments() {
    return segments.size();
  }

  // the line segments
  public LineSegment[] segments() {
    LineSegment[] segmentArray = new LineSegment[segments.size()];
    int i = 0;
    for (LineSegment seg : segments) {
      segmentArray[i] = seg;
      i++;
    }
    return segmentArray;
  }

  private LineSegment orderedSegment(Point[] indexes) {
    Point min = indexes[0];
    Point max = indexes[0];
    for (int i = 1; i < indexes.length; i++) {
      Point point = indexes[i];
      if (min.compareTo(point) > 0) {
        min = point;
      } else if (max.compareTo(point) < 0) {
        max = point;
      }
    }
    return new LineSegment(min, max);
  }

  private Point minimum(Point[] indexes) {
    Point min = indexes[0];
    for (int i = 1; i < indexes.length; i++)
      if (min.compareTo(indexes[i]) > 0) min = indexes[i];
    return min;
  }

  private boolean isCollinear(Point[] indexes) {
    Point min = minimum(indexes);
    double temp;
    double slope = Double.NEGATIVE_INFINITY;
    for (Point point : indexes) {
      temp = min.slopeTo(point);
      if (temp == Double.NEGATIVE_INFINITY) continue;
      if (slope == Double.NEGATIVE_INFINITY) slope = temp;
      else if (slope != temp) return false;
    }
    return true;
  }

  private void testEquality(int i, int j) {
    if (pts[j].compareTo(pts[i]) == 0) throw new IllegalArgumentException();
  }

}

package questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalListIntersections {
    private class Point {
        int point;
        boolean isStart;

        Point(int point, boolean isStart) {
            this.point = point;
            this.isStart = isStart;
        }
    }

    Comparator<Point> pointComparator = (p1, p2) -> {
        if(p1.point < p2.point)
            return -1;
        else if(p1.point > p2.point)
            return 1;
        else {
            if(p1.isStart)
                return -1;
            else if(p2.isStart)
                return 1;
            else
                return 0;
        }
    };

    public int[][] intervalIntersection(int[][] A, int[][] B) {
        return _findIntersections(A, B);
    }

    private int[][] _findIntersections(int[][] A, int[][] B) {
        Point[] points = new Point[A.length*2 + B.length*2];

        int pIdx = 0;
        for(int[] aItem : A) {
            Point start = new Point(aItem[0], true);
            Point end = new Point(aItem[1], false);
            points[pIdx++] = start;
            points[pIdx++] = end;
        }

        for(int[] bItem : B) {
            Point start = new Point(bItem[0], true);
            Point end = new Point(bItem[1], false);
            points[pIdx++] = start;
            points[pIdx++] = end;
        }

        Arrays.sort(points, pointComparator);
        List<int[]> intersections = new ArrayList<>();
        int count = 0;
        int[] intersection = new int[2];

        for(Point p : points) {
            if(p.isStart) {
                count++;
                if(count == 2) {
                    intersection[0] = p.point;
                }
            }
            else {
                count--;
                if(count == 1) {
                    intersection[1] = p.point;
                    intersections.add(Arrays.copyOf(intersection, intersection.length));
                }
            }
        }

        int[][] results = new int[intersections.size()][2];
        int idx = 0;
        for(int[] intersects : intersections) {
            results[idx][0] = intersects[0];
            results[idx][1] = intersects[1];
            idx++;
        }

        return results;
    }
}

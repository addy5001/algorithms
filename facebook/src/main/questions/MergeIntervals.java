package questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {

    private class Point {
        int time;
        boolean start;

        Point(int time, boolean start) {
            this.time = time;
            this.start = start;
        }

        int getTime() {
            return time;
        }

        boolean isStart() {
            return start;
        }
    }

    private Comparator<Point> timeComparator = Comparator.comparing(Point::getTime);

    public int[][] merge(int[][] intervals) {
        Point[] points = _createPoints(intervals);
        Arrays.sort(points, timeComparator);
        List<int[]> mergedList = new ArrayList<>();
        int count = 0;
        int start = -1;

        for(int i=0; i<points.length; i++) {
            if(points[i].isStart()) {
                count++;
                if(count == 1 && (i == 0
                        || (points[i-1].getTime() != points[i].getTime()))) {
                    start = points[i].getTime();
                }
            }
            else {
                count--;
                if(count == 0 && (i == points.length-1
                        || (i < points.length-1 && points[i].getTime() != points[i+1].getTime()))) {
                    mergedList.add(new int[]{start, points[i].getTime()});
                }
            }
        }

        int[][] results = new int[mergedList.size()][2];
        int inc = 0;
        for(int[] item : mergedList) {
            results[inc][0] = item[0];
            results[inc][1] = item[1];
            inc++;
        }

        return results;
    }

    private Point[] _createPoints(int[][] intervals) {
        Point[] points = new Point[intervals.length * intervals[0].length];
        int pointIdx = 0;
        for(int i=0; i<intervals.length; i++) {
            Point startPoint = new Point(intervals[i][0], true);
            Point endPoint = new Point(intervals[i][1], false);

            points[pointIdx++] = startPoint;
            points[pointIdx++] = endPoint;
        }

        return points;
    }
}

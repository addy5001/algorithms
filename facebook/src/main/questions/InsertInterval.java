package questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class InsertInterval {
    class Point implements Comparable<Point> {
        int point;
        boolean isStart;

        Point(int point, boolean isStart) {
            this.point = point;
            this.isStart = isStart;
        }

        @Override
        public int compareTo(Point other) {
            return Integer.compare(this.point, other.point);
        }
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<Point> points = new ArrayList<>();

        for(int i=0; i<intervals.length; i++) {
            points.add(new Point(intervals[i][0], true));
            points.add(new Point(intervals[i][1], false));
        }

        points.add(new Point(newInterval[0], true));
        points.add(new Point(newInterval[1], false));

        Collections.sort(points);
        int count = 0;
        int[] overlap = new int[2];
        List<int[]> results = new ArrayList<>();

        for(int i=0; i<points.size(); i++) {
            if(points.get(i).isStart) {
                count++;
                if(i>0 && points.get(i).point == points.get(i-1).point)
                    continue;

                if(count == 1) {
                    overlap[0] = points.get(i).point;
                }
            }
            else {
                count--;
                if(i<points.size()-1 && points.get(i).point == points.get(i+1).point)
                    continue;

                if(count == 0) {
                    overlap[1] = points.get(i).point;
                    results.add(Arrays.copyOf(overlap, 2));
                }
            }
        }

        int[][] resultArray = new int[results.size()][2];
        for(int i=0; i<results.size(); i++) {
            resultArray[i][0] = results.get(i)[0];
            resultArray[i][1] = results.get(i)[1];
        }

        return resultArray;
    }
}

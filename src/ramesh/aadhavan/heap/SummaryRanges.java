package ramesh.aadhavan.heap;

import java.util.Comparator;
import java.util.TreeSet;

public class SummaryRanges {

    class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int[] toArray() {
            return new int[] {start, end};
        }
    }

    TreeSet<Interval> treeSet;

    public SummaryRanges() {
        this.treeSet = new TreeSet<>(Comparator.comparingInt(interval -> interval.start));
    }

    public void addNum(int val) {
        Interval interval = new Interval(val, val);

        Interval floor = treeSet.floor(interval);
        if(floor != null) {
            if(val <= floor.end)
                return;
            else {
                if(val == floor.end+1) {
                    interval.start = floor.start;
                    treeSet.remove(floor);
                }
            }
        }

        Interval higher = treeSet.higher(interval);
        if(higher != null) {
            if(higher.start == val+1) {
                interval.end = higher.end;
                treeSet.remove(higher);
            }
        }

        treeSet.add(interval);
    }

    public int[][] getIntervals() {
        return treeSet.stream().map(Interval::toArray).toArray(int[][]::new);
    }
}

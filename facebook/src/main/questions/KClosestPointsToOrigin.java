package questions;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;

public class KClosestPointsToOrigin {

    class Point implements Comparable<Point> {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public int compareTo(Point o) {
            int distanceToOrigin = (x*x) + (y*y);
            int otherPointDistanceToOrigin = (o.x*o.x) + (o.y*o.y);
            return Integer.compare(distanceToOrigin, otherPointDistanceToOrigin);
        }
    }

    public int[][] kClosest(int[][] points, int K) {
        Queue<Point> pQueue = new PriorityQueue<>();

        //Arrays.stream(points).forEach(point -> pQueue.add(new Point(point[0], point[1])));

        for(int[] point : points) {
            pQueue.add(new Point(point[0], point[1]));
        }

        int[][] result = new int[K][2];
        int idx = 0;
        int toPoll = K;

        while(!pQueue.isEmpty() && toPoll > 0) {
            Point polledPoint = pQueue.poll();
            result[idx][0] = polledPoint.x;
            result[idx][1] = polledPoint.y;

            idx++;
            toPoll--;
        }

        return result;
    }

    /**
     * Divide and Conquer approach
     * QuickSelect method
     */
    int[][] points;

    public int[][] kClosestQuickSelect(int[][] points, int K) {
        this.points = points;
        sort(0, points.length - 1, K);
        return Arrays.copyOfRange(points, 0, K);
    }

    public void sort(int i, int j, int K) {
        if (i >= j) return;
        int k = ThreadLocalRandom.current().nextInt(i, j);
        swap(i, k);

        int mid = partition(i, j);
        int leftLength = mid - i + 1;
        if (K < leftLength)
            sort(i, mid - 1, K);
        else if (K > leftLength)
            sort(mid + 1, j, K - leftLength);
    }

    public int partition(int i, int j) {
        int oi = i;
        int pivot = dist(i);
        i++;

        while (true) {
            while (i < j && dist(i) < pivot)
                i++;
            while (i <= j && dist(j) > pivot)
                j--;
            if (i >= j) break;
            swap(i, j);
        }
        swap(oi, j);
        return j;
    }

    public int dist(int i) {
        return points[i][0] * points[i][0] + points[i][1] * points[i][1];
    }

    public void swap(int i, int j) {
        int t0 = points[i][0], t1 = points[i][1];
        points[i][0] = points[j][0];
        points[i][1] = points[j][1];
        points[j][0] = t0;
        points[j][1] = t1;
    }
}

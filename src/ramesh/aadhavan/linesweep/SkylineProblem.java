package ramesh.aadhavan.linesweep;

import java.util.*;

public class SkylineProblem {

    class SkylineEntry {
        int id;
        int point;
        int height;
        boolean isStart;

        public SkylineEntry(int id, int point, int height, boolean isStart) {
            this.id = id;
            this.point = point;
            this.height = height;
            this.isStart = isStart;
        }

        public int getId() {
            return id;
        }

        public int getPoint() {
            return point;
        }

        public int getHeight() {
            return height;
        }

        public boolean isStart() {
            return isStart;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof SkylineEntry) && this.id == ((SkylineEntry) obj).id;
        }
    }

    Comparator<SkylineEntry> skylineEntryComparator = (a, b) -> {
        if(a.point == b.point) {
            if(a.height == b.height) {
                if(a.isStart)
                    return -1;
                else if(b.isStart)
                    return 1;
                else
                    return 0;
            }
            else {
                return Integer.compare(b.height, a.height);
            }
        }
        else {
            return Integer.compare(a.point, b.point);
        }
    };

    public List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings == null || buildings.length == 0)
            return Collections.emptyList();

        List<SkylineEntry> skylineEntries = new ArrayList<>();

        int idIdx = 0;
        for(int[] building : buildings) {
            int start = building[0];
            int end = building[1];
            int height = building[2];

            skylineEntries.add(new SkylineEntry(idIdx, start, height, true));
            skylineEntries.add(new SkylineEntry(idIdx, end, height, false));

            idIdx++;
        }

        Collections.sort(skylineEntries, skylineEntryComparator);
        PriorityQueue<SkylineEntry> maxQueue = new PriorityQueue<>(Comparator.comparingInt(SkylineEntry::getHeight).reversed());
        int count = 0;

        List<List<Integer>> skyline = new ArrayList<>();
        for(int i=0; i<skylineEntries.size(); i++) {
            if(skylineEntries.get(i).isStart) {
                count++;
                maxQueue.offer(skylineEntries.get(i));

                if(count == 1 || (i == 0 || skyline.get(skyline.size() - 1).get(1) != maxQueue.peek().height)) {
                    skyline.add(List.of(skylineEntries.get(i).point, maxQueue.peek().height));
                }
            }
            else {
                count--;
                maxQueue.remove(skylineEntries.get(i));

                if(count == 0) {
                    skyline.add(List.of(skylineEntries.get(i).point, 0));
                }
                else {
                    if(i != skylineEntries.size()-1 && skylineEntries.get(i+1).point == skylineEntries.get(i).point)
                        continue;

                    if(maxQueue.peek().height < skylineEntries.get(i).height) {
                        skyline.add(List.of(skylineEntries.get(i).point, maxQueue.peek().height));
                    }
                }
            }
        }

        return skyline;
    }
}

package questions;

import java.util.*;

public class HitCounter implements IHitCounter {

    private class TimedVal {
        int timestamp;
        int count;

        public TimedVal(int timestamp) {
            this.timestamp = timestamp;
            this.count = 1;
        }

        public TimedVal(int timestamp, int count) {
            this.timestamp = timestamp;
            this.count = count;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public int getCount() {
            return count;
        }

        public void increment() {
            this.count++;
        }
    }

    private final List<TimedVal> _backingList;

    /** Initialize your data structure here. */
    public HitCounter() {
        _backingList = new ArrayList<>();
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    @Override
    public void hit(int timestamp) {
        int idx = Collections.binarySearch(_backingList, new TimedVal(timestamp),
                Comparator.comparing(TimedVal::getTimestamp));

        if(idx < 0)
            _backingList.add(new TimedVal(timestamp));
        else
            _backingList.get(idx).increment();
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    @Override
    public int getHits(int timestamp) {
        int startRange = timestamp < 300 ? 0 : timestamp - 300 + 1;
        int idx = _binarySearchStartRange(startRange);
        if(idx == -1)
            return 0;

        int totalHits = 0;
        while(idx < _backingList.size() && _backingList.get(idx).timestamp <= timestamp) {
            totalHits += _backingList.get(idx).count;
            idx++;
        }

        return totalHits;
    }

    private int _binarySearchStartRange(int startRange) {
        int start = 0;
        int end = _backingList.size()-1;

        while(start <= end) {
            int mid = start + (end - start)/2;

            if(_backingList.get(mid).timestamp == startRange) {
                return mid;
            }
            else if((mid == 0 || _backingList.get(mid-1).timestamp < startRange)
                    && _backingList.get(mid).timestamp > startRange) {
                return mid;
            }
            else if(startRange < _backingList.get(mid).timestamp) {
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }

        return -1;
    }
}

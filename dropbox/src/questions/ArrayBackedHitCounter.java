package questions;

public class ArrayBackedHitCounter implements IHitCounter {

    private final int[] timestamps;
    private final int[] vals;

    public ArrayBackedHitCounter() {
        this.timestamps = new int[300];
        this.vals = new int[300];
    }

    @Override
    public int getHits(int timestamp) {
        int totalHits = 0;

        for(int i=0; i<300; i++) {
            if(timestamps[i] > timestamp - 300)
                totalHits += vals[i];
        }

        return totalHits;
    }

    @Override
    public void hit(int timestamp) {
        int modTimestamp = timestamp % 300;

        if(timestamps[modTimestamp] != timestamp) {
            timestamps[modTimestamp] = timestamp;
            vals[modTimestamp] = 1;
        }
        else {
            vals[modTimestamp]++;
        }
    }
}

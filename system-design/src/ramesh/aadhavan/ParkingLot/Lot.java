package ramesh.aadhavan.ParkingLot;

import scala.Tuple2;

import java.util.*;
import java.util.stream.IntStream;

public class Lot {

    public static final int DEFAULT_SPOTS = 100;

    private Boolean[] small;
    private Boolean[] compact;
    private Boolean[] large;

    private Deque<Integer> freeSpotsSmall = new ArrayDeque<>();
    private Deque<Integer> freeSpotsCompact = new ArrayDeque<>();
    private Deque<Integer> freeSpotsLarge = new ArrayDeque<>();

    private final Map<SpotSize, Tuple2<Boolean[], Deque<Integer>>> spotMap = new HashMap<>();

    public Lot() {
        this(new Boolean[DEFAULT_SPOTS], new Boolean[DEFAULT_SPOTS], new Boolean[DEFAULT_SPOTS]);
    }

    public Lot(Boolean[] small, Boolean[] compact, Boolean[] large) {
        this.small = small;
        this.compact = compact;
        this.large = large;

        for(int i=0; i<small.length; i++)
            small[i] = false;

        for(int i=0; i<compact.length; i++)
            compact[i] = false;

        for(int i=0; i<large.length; i++)
            large[i] = false;

        spotMap.put(SpotSize.SMALL, new Tuple2<>(small, freeSpotsSmall));
        spotMap.put(SpotSize.COMPACT, new Tuple2<>(compact, freeSpotsCompact));
        spotMap.put(SpotSize.LARGE, new Tuple2<>(large, freeSpotsLarge));
        spotMap.put(SpotSize.BUS, new Tuple2<>(large, freeSpotsLarge));
    }

    public boolean isSpotAvailable(SpotSize size) {
        boolean available = false;
        switch (size) {
            case SMALL: {
                available = _isSpotAvailable(small, freeSpotsSmall);
                break;
            }
            case COMPACT: {
                available = _isSpotAvailable(compact, freeSpotsCompact);
                break;
            }
            case LARGE: {
                available = _isSpotAvailable(large, freeSpotsLarge);
                break;
            }
            case BUS: {
                int count = 0;
                for(Boolean b : large) {
                    count = b ? 0 : count+1;
                }

                available = (count == 5);
                break;
            }
            default: {
                break;
            }
        }

        return available;
    }

    private boolean _isSpotAvailable(Boolean[] spotArray, Deque<Integer> freeSpots) {
        if (!freeSpots.isEmpty())
            return true;

        return Arrays.stream(spotArray).anyMatch(Boolean::booleanValue);
    }

    public Optional<ParkedSpot.Builder> allocate(SpotSize size) {
        switch (size) {
            case SMALL: {
                return Optional.ofNullable(_allocate(SpotSize.SMALL, small, freeSpotsSmall));
            }
            case COMPACT: {
                return Optional.ofNullable(_allocate(SpotSize.COMPACT, compact, freeSpotsCompact));
            }
            case LARGE: {
                return Optional.ofNullable(_allocate(SpotSize.LARGE, large, freeSpotsLarge));
            }
            case BUS: {
                int startIdx = 0;
                int endIdx = 0;

                for(int i=0; i<large.length; i++) {
                    if(large[i]) {
                        startIdx = i;
                        endIdx = i;
                    }
                    else {
                        endIdx++;
                        if((endIdx - startIdx) == 4) {
                            IntStream.range(startIdx, endIdx+1).forEach(idx -> large[idx] = true);
                            return Optional.of(new ParkedSpot.Builder()
                                    .setSpotSize(SpotSize.BUS)
                                    .setBeginIndex(startIdx)
                                    .setEndIndex(endIdx));
                        }
                    }
                }

                return Optional.empty();
            }
            default: {
                return Optional.empty();
            }
        }
    }

    private ParkedSpot.Builder _allocate(SpotSize spotSize, Boolean[] spotArray, Deque<Integer> freeSpots) {
        while(!freeSpots.isEmpty()) {
            int idx = freeSpots.pop();
            if(!spotArray[idx]) {
                spotArray[idx] = true;
                return new ParkedSpot.Builder().setSpotSize(spotSize).setBeginIndex(idx).setEndIndex(idx);
            }
        }

        for(int i=0; i<spotArray.length; i++) {
            if(!spotArray[i]) {
                spotArray[i] = true;
                return new ParkedSpot.Builder().setSpotSize(spotSize).setBeginIndex(i).setEndIndex(i);
            }
        }

        return null;
    }

    public void deallocate(ParkedSpot parkedSpot) {
        Tuple2<Boolean[], Deque<Integer>> tuple = spotMap.get(parkedSpot.getSpotSize());
        if(tuple == null)
            return;

        Boolean[] spots = tuple._1;
        Deque<Integer> freeSpots = tuple._2;

        if(parkedSpot.getSpotSize() == SpotSize.BUS) {
            IntStream.range(parkedSpot.getBeginIndex(), parkedSpot.getEndIndex()+1)
                    .forEach(idx -> {
                        spots[idx] = false;
                        freeSpots.push(idx);
                    });
        }
        else {
            spots[parkedSpot.getBeginIndex()] = false;
            freeSpots.push(parkedSpot.getBeginIndex());
        }
    }

}

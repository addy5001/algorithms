package ramesh.aadhavan.ParkingLot;

import java.util.*;

public class ParkingLotImpl implements ParkingLot {

    private final Lot[] lots;
    private final Map<Vehicle, ParkedSpot> cache = new HashMap<>();

    public ParkingLotImpl(int levels) {
        this.lots = new Lot[levels];
        for(int i=0; i<lots.length; i++) {
            lots[i] = new Lot();
        }
    }

    @Override
    public boolean canPark(Vehicle v) {
        ParkedSpot p = cache.get(v);
        SpotSize size = _calculateSpotSize(v);
        return Objects.nonNull(p)
                && Arrays.stream(lots).anyMatch(lot -> lot.isSpotAvailable(size));
    }

    @Override
    public Optional<ParkedSpot> park(Vehicle vehicle) {
        SpotSize spotSize = _calculateSpotSize(vehicle);
        if(spotSize == SpotSize.UNSUPPORTED)
            return Optional.empty();

        for(int i=0; i<lots.length; i++) {
            Optional<ParkedSpot.Builder> builderOpt = lots[i].allocate(spotSize);
            if(builderOpt.isPresent()) {
                ParkedSpot.Builder builder = builderOpt.get();
                builder.setVehicle(vehicle).setLevel(i);
                final ParkedSpot parkedSpot = builder.build();
                cache.put(vehicle, parkedSpot);
                return Optional.of(parkedSpot);
            }
        }

        return Optional.empty();
    }

    private SpotSize _calculateSpotSize(Vehicle v) {
        return Arrays.stream(SpotSize.values())
                .filter(s -> s.getSize() == v.getSize())
                .findAny()
                .orElse(SpotSize.UNSUPPORTED);
    }

    @Override
    public int findLevel(ParkedSpot p) {
        return p.getLevel();
    }

    @Override
    public Optional<ParkedSpot> getParkedSpot(Vehicle v) {
        return Optional.ofNullable(cache.get(v));
    }

    @Override
    public void unpark(ParkedSpot p) {
        Lot parkedLot = lots[p.getLevel()];
        parkedLot.deallocate(p);
        cache.remove(p.getVehicle());
    }
}

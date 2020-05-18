package ramesh.aadhavan.ParkingLot;

import java.util.Optional;

public interface ParkingLot {

    boolean canPark(Vehicle v);

    Optional<ParkedSpot> park(Vehicle v);

    int findLevel(ParkedSpot p);

    Optional<ParkedSpot> getParkedSpot(Vehicle v);

    void unpark(ParkedSpot p);
}

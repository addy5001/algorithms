package ramesh.aadhavan.ParkingLot;

public class Bus extends Vehicle {
    public Bus(String registration, String make, String model) {
        super(registration, make, model);
    }

    @Override
    public int getSize() {
        return 20;
    }
}

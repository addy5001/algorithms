package ramesh.aadhavan.ParkingLot;

public class Motorcycle extends Vehicle {
    public Motorcycle(String registration, String make, String model) {
        super(registration, make, model);
    }

    @Override
    public int getSize() {
        return 1;
    }
}

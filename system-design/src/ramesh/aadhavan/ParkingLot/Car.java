package ramesh.aadhavan.ParkingLot;

public class Car extends Vehicle {

    public Car(String registration, String make, String model) {
        super(registration, make, model);
    }

    @Override
    public int getSize() {
        return 2;
    }
}

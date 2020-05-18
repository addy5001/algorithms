package ramesh.aadhavan.ParkingLot;

import java.util.Objects;

public abstract class Vehicle {
    private String registration;
    private String make;
    private String model;

    public Vehicle(String registration, String make, String model) {
        this.registration = registration;
        this.make = make;
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public int hashCode() {
        return Objects.hash(registration);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vehicle) {
            Vehicle other = (Vehicle) obj;
            return Objects.equals(registration, other.registration);
        }

        return false;
    }

    public abstract int getSize();
}

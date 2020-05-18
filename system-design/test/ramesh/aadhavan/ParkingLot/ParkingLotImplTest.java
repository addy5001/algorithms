package ramesh.aadhavan.ParkingLot;

import org.junit.Test;

public class ParkingLotImplTest {

    final ParkingLotImpl parkingLot = new ParkingLotImpl(5);

    @Test
    public void testPark() {
        Car car = new Car("123", "honda", "crv");
        Car car2 = new Car("1243", "honda", "crv");
        Car car3 = new Car("1235", "honda", "crv");
        Car car4 = new Car("1236", "honda", "crv");

        Motorcycle m = new Motorcycle("a", "bmw", "s");
        Motorcycle m2 = new Motorcycle("a1", "bmw", "s");
        Motorcycle m3 = new Motorcycle("a2", "bmw", "s");

        Bus bus = new Bus("45", "volvo", "rx");
        Bus bus2 = new Bus("451", "volvo", "rx");
        Bus bus3 = new Bus("452", "volvo", "rx");
        Bus bus4 = new Bus("453", "volvo", "rx");

        parkingLot.park(car);
        parkingLot.park(car2);
        parkingLot.park(bus);
        parkingLot.park(m3);
        parkingLot.park(car3);
        parkingLot.park(m);
        parkingLot.park(m2);
        parkingLot.park(bus2);
        parkingLot.park(bus3);
        parkingLot.park(car4);
        parkingLot.park(bus4);
    }
}

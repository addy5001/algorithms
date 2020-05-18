package ramesh.aadhavan.ParkingLot;

public enum SpotSize {

    SMALL(1),
    COMPACT(2),
    LARGE(4),
    BUS(20),
    UNSUPPORTED(-1);

    private int size;

    SpotSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}

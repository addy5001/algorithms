package ramesh.aadhavan.ParkingLot;

public class ParkedSpot {

    private final Vehicle vehicle;
    private final SpotSize spotSize;
    private final int level;
    private final int beginIndex;
    private final int endIndex;

    public ParkedSpot(Vehicle vehicle, SpotSize spotSize, int level, int beginIndex, int endIndex) {
        this.vehicle = vehicle;
        this.spotSize = spotSize;
        this.level = level;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public SpotSize getSpotSize() {
        return spotSize;
    }

    public int getLevel() {
        return level;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public static class Builder {

        private Vehicle vehicle;
        private SpotSize spotSize;
        private int level;
        private int beginIndex;
        private int endIndex;

        public Builder() {
        }

        public Builder setVehicle(Vehicle vehicle) {
            this.vehicle = vehicle;
            return this;
        }

        public Builder setSpotSize(SpotSize spotSize) {
            this.spotSize = spotSize;
            return this;
        }

        public Builder setLevel(int level) {
            this.level = level;
            return this;
        }

        public Builder setBeginIndex(int beginIndex) {
            this.beginIndex = beginIndex;
            return this;
        }

        public Builder setEndIndex(int endIndex) {
            this.endIndex = endIndex;
            return this;
        }

        public ParkedSpot build() {
            return new ParkedSpot(vehicle, spotSize, level, beginIndex, endIndex);
        }
    }
}

package ramesh.aadhavan.misc;

public class Item {
    private final int weight;
    private final int worth;

    public Item(int weight, int worth) {
        this.weight = weight;
        this.worth = worth;
    }

    public int getWeight() {
        return weight;
    }

    public int getWorth() {
        return worth;
    }

    @Override
    public String toString() {
        return String.format("[Weight=%d,Worth=%d]", weight, worth);
    }

}

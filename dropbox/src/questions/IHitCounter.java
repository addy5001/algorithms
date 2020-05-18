package questions;

public interface IHitCounter {
    int getHits(int timestamp);
    void hit(int timestamp);
}

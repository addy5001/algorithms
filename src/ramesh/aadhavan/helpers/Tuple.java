package ramesh.aadhavan.helpers;

public class Tuple<T, U> {
    private T _1;
    private U _2;

    public Tuple(T _1, U _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public T get_1() {
        return _1;
    }

    public U get_2() {
        return _2;
    }
}

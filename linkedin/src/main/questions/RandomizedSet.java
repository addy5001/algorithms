package questions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {

    Map<Integer, Integer> _backingMap;
    LinkedList<Integer> _randomVals;
    Random random;

    /** Initialize your data structure here. */
    public RandomizedSet() {
        _backingMap = new HashMap<>();
        _randomVals = new LinkedList<>();
        random = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(_backingMap.containsKey(val))
            return false;

        _backingMap.put(val, _randomVals.size());
        _randomVals.add(_randomVals.size());
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!_backingMap.containsKey(val))
            return false;

        // Move last element to index of current val
        int lastElement = _randomVals.get(_randomVals.size() - 1);
        int valIdx = _backingMap.get(val);
        _randomVals.set(valIdx, lastElement);
        _backingMap.put(lastElement, valIdx);

        // Remove
        _randomVals.remove(_randomVals.size() - 1);
        _backingMap.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return _randomVals.get(random.nextInt(_randomVals.size()));
    }
}

package ramesh.aadhavan.sets;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class MyHashSet {

    private int NUM_KEYS = 4999;
    private double LOAD_FACTOR = 2.5;
    private int occupancy = 0;
    private Object[] values;

    /** Initialize your data structure here. */
    public MyHashSet() {
        values = new Object[NUM_KEYS];
    }

    public void add(int key) {
        int hash = Objects.hashCode(key) % NUM_KEYS;
        if(values[hash] != null) {
            List<Integer> list = (List<Integer>) values[hash];
            for(int elem : list) {
                if(elem == key)
                    return;
            }
            list.add(key);
        }
        else {
            List<Integer> list = new LinkedList<>();
            list.add(key);
            values[hash] = list;
            occupancy++;
        }
    }

    public void remove(int key) {
        int hash = Objects.hashCode(key) % NUM_KEYS;
        if(values[hash] != null) {
            List<Integer> list = (List<Integer>) values[hash];
            boolean found = false;
            int i = 0;
            for(;i < list.size(); i++) {
                if(list.get(i) == key) {
                    found = true;
                    break;
                }
            }

            if(found) {
                list.remove(i);
                if(list.isEmpty())
                    occupancy--;
            }
        }
    }

    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int hash = Objects.hashCode(key) % NUM_KEYS;
        if(values[hash] == null) {
            return false;
        }

        List<Integer> list = (List<Integer>) values[hash];
        for(int elem : list) {
            if(elem == key)
                return true;
        }

        return false;
    }

    public int getOccupancy() {
        return occupancy;
    }
}

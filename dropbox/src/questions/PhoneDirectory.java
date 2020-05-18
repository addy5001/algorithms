package questions;

import java.util.ArrayDeque;
import java.util.Queue;

public class PhoneDirectory {

    private final int maxNumbers;
    private final boolean[] isUsed;
    private final Queue<Integer> queue;
    private int incrementer = 0;

    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    public PhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        this.isUsed = new boolean[maxNumbers];
        this.queue = new ArrayDeque<>(maxNumbers);
    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if(queue.isEmpty()) {
            if(incrementer < maxNumbers) {
                isUsed[incrementer] = true;
                return incrementer;
            }

            return -1;
        }
        else {
            int number = queue.poll();
            isUsed[number] = true;
            return number;
        }
    }

    /** Check if a number is available or not. */
    public boolean check(int number) {
        return !isUsed[number];
    }

    /** Recycle or release a number. */
    public void release(int number) {
        if(!isUsed[number])
            return;

        isUsed[number] = false;
        queue.offer(number);
    }
}

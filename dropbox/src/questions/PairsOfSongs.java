package questions;

import java.util.HashMap;
import java.util.Map;

public class PairsOfSongs {
    public int numPairsDivisibleBy60(int[] time) {
        return _hashBased(time);
    }

    private int _hashBased(int[] time) {
        Map<Integer, Integer> map = new HashMap<>();

        int total = 0;

        for(int i=0; i<time.length; i++) {
            int mod60 = (600 - time[i]) % 60;

            if(map.containsKey(mod60))
                total += map.get(mod60);

            int timeToPut = time[i] % 60;
            map.put(timeToPut, map.getOrDefault(timeToPut, 0) + 1);
        }

        return total;
    }

    /**
     * Naive Approach - Time Limit Exceeded
     * Time Complexity = O(n^2)
     * Space Complexity = O(1)
     */
    private int _naive(int[] time) {
        int total = 0;

        for(int i=0; i<time.length; i++) {
            for(int j=i+1; j<time.length; j++) {
                if((time[i] + time[j]) % 60 == 0)
                    total++;
            }
        }

        return total;
    }
}

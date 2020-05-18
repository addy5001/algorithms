package questions;

import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        return _hashMap(nums, k);
    }

    /**
     * Idea behind the approach
     * a % k = x
     * b % k = x
     * (a - b) % k = x - x = 0
     * a - b is the sum between i and j
     *
     * Why map.put(0, -1)
     * In the case nums = [1, 5] k = 6, at i=1, sum % k is 0, so we need a key '0' in the map,
     * and it must be comply with the continuous condition, i - map.get(sum) > 1, so we give an arbitrary value of -1
     * @param nums
     * @param k
     * @return
     */
    private boolean _hashMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];

            if(k != 0)
                sum = sum % k;

            if(map.containsKey(sum)) {
                if(i - map.get(sum) > 1)
                    return true;
            }
            else {
                map.put(sum, i);
            }
        }

        return false;
    }
}

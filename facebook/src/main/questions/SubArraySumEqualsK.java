package questions;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
        return _hashMap(nums, k);
    }

    private int _naive(int[] nums, int k) {
        int count = 0;

        for(int i=0; i<nums.length; i++) {
            int rollingSum = 0;

            for(int j=i; j<nums.length; j++) {
                rollingSum += nums[j];
                if(rollingSum == k) {
                    count++;
                }
            }
        }

        return count;
    }

    private int _hashMap(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        int sum = 0;

        map.put(0, 1);

        for(int i=0; i<nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k))
                count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}

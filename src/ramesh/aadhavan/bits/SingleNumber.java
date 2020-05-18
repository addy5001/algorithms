package ramesh.aadhavan.bits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumber {

    // Every other element appears twice
    public int singleNumber(int[] nums) {
        return Arrays.stream(nums).reduce(0, (acc, x) -> acc ^ x);
    }

    // Every other element appears thrice
    public int singleNumber3(int[] nums) {
        int[] bitCounts = new int[32];
        for(int num : nums) {
            String binary = Integer.toBinaryString(num);
            String binaryString = "0".repeat(32 - binary.length()).concat(binary);
            int idx = 0;

            for(char c : binaryString.toCharArray()) {
                bitCounts[idx++] += Character.getNumericValue(c);
            }
        }

        int singleNumber = 0;
        for(int i=0; i<bitCounts.length; i++) {
            bitCounts[i] = bitCounts[i] % 3;
            singleNumber = (singleNumber << 1) + bitCounts[i];
        }

        return singleNumber;
    }

    private int _singleNumberDistinct(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        Arrays.stream(nums).boxed().forEach(i -> countMap.put(i, countMap.getOrDefault(i, 0) + 1));
        return countMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey).findFirst()
                .orElse(0);
    }

    public int[] singleNumberFindTwoSingleNumbers(int[] nums) {
        // difference between two numbers (x and y) which were seen only once
        int bitmask = 0;
        for (int num : nums) bitmask ^= num;

        // rightmost 1-bit diff between x and y
        int diff = bitmask & (-bitmask);

        int x = 0;
        // bitmask which will contain only x
        for (int num : nums) if ((num & diff) != 0) x ^= num;

        return new int[]{x, bitmask^x};
    }
}

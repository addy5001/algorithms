package ramesh.aadhavan.misc;

import java.util.Arrays;
import java.util.function.Function;

public class MaximumGap {
    public int maximumGap(int[] nums) {
        return _bucketApproach(nums);
    }

    public int _sortApproach(int[] nums) {
        if(nums.length < 2)
            return 0;

        _radixSort(nums);

        int maxDiff = Integer.MIN_VALUE;
        for(int i=0; i<nums.length-1; i++) {
            int diff = nums[i+1] - nums[i];
            if(diff > maxDiff)
                maxDiff = diff;
        }

        return maxDiff;
    }

    public void _radixSort(int[] nums) {
        int max = _findMax(nums);
        int radix = 10;
        int exp = 1;
        int[] aux = new int[nums.length];

        while((max/exp) > 0) {
            int[] counts = new int[radix];

            // Counting Sort
            for(int i=0; i<nums.length; i++) {
                counts[((nums[i]/exp) % radix)]++;
            }

            // Partial Sum
            for(int i=1; i<counts.length; i++) {
                counts[i] += counts[i-1];
            }

            // Ordering in aux
            for(int i=nums.length-1; i>=0; i--) {
                aux[--counts[((nums[i]/exp) % radix)]] = nums[i];
            }

            // Copy aux to nums
            System.arraycopy(aux, 0, nums, 0, nums.length);

            exp*=radix;
        }
    }

    private int _findMax(int[] nums) {
        return Arrays.stream(nums).max().orElseThrow(() -> new IllegalArgumentException());
    }

    private int _findMin(int[] nums) {
        return Arrays.stream(nums).min().orElseThrow(() -> new IllegalArgumentException());
    }

    static class Bucket {
        boolean used;
        int max;
        int min;

        public Bucket() {
            used = false;
        }

        public Bucket(int x) {
            setVal(x);
        }

        public void setVal(int x) {
            if(!used) {
                used = true;
                min = x;
                max = x;
            }
            else {
                if(x < min)
                    min = x;

                if(x > max)
                    max = x;
            }
        }
    }

    public int _bucketApproach(int[] nums) {
        int max = _findMax(nums);
        int min = _findMin(nums);
        int n = nums.length;
        int bSize = (max - min)/(n - 1) + 1;
        int numBuckets = (max - min)/bSize;
        Bucket[] buckets = new Bucket[numBuckets];
        for(int i=0; i<numBuckets; i++)
            buckets[i] = new Bucket();

        Function<Integer, Integer> computeBucketIdx = num -> (num - min)/bSize;

        for(int num : nums) {
            int idx = computeBucketIdx.apply(num);
            buckets[idx].setVal(num);
        }

        int maxGap = Integer.MIN_VALUE;
        int lastMax = buckets[0].max;
        for(int i=1; i<numBuckets; i++) {
            if(!buckets[i].used)
                continue;

            int gap = buckets[i].min - lastMax;
            if(gap > maxGap)
                maxGap = gap;

            lastMax = buckets[i].max;
        }

        return maxGap;
    }
}

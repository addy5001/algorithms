package ramesh.aadhavan.misc;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class NextPermutation {

    private class NextGreaterPermutationIterator implements Iterator<Integer[]> {

        private Integer[] nums;

        public NextGreaterPermutationIterator(Integer[] nums) {
            this.nums = nums;
        }

        public NextGreaterPermutationIterator(Stream<Integer> integerStream) {
            nums = integerStream.toArray(Integer[]::new);
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Integer[] next() {
            int swappedIdx = _next();
            if(swappedIdx == -1)
                Arrays.sort(nums);
            else
                Arrays.sort(nums, swappedIdx+1, nums.length);

            return Arrays.copyOf(nums, nums.length);
        }

        private int _next() {
            int begin = nums.length-1;
            int j = -1;

            while(begin >= 1) {

                j = begin-1;
                while (j >= 0) {
                    if (nums[j] < nums[begin])
                        break;

                    j--;
                }

                if(j != -1)
                    break;

                begin--;
            }

            if(j == -1) {
                return j;
            }

            int minDiff = Integer.MAX_VALUE;
            int swappingIdx = -1;

            for(int k=j+1; k<nums.length; k++) {
                if(nums[k] > nums[j]) {
                    int diff = nums[k] - nums[j];
                    if(minDiff > diff) {
                        minDiff = diff;
                        swappingIdx = k;
                    }
                }
            }

            if(swappingIdx != -1) {
                int temp = nums[j];
                nums[j] = nums[swappingIdx];
                nums[swappingIdx] = temp;
                return j;
            }

            return -1;
        }
    }

    public Iterator<Integer[]> getIterator(int[] ints) {
        return new NextGreaterPermutationIterator(Arrays.stream(ints).boxed());
    }
}

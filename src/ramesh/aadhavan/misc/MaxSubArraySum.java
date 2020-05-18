package ramesh.aadhavan.misc;

public class MaxSubArraySum {

    public static int maxSubArraySumDivideAndConquer(int[] nums) {
        return _maxSubArraySumDivideAndConquer(nums, 0, nums.length-1);
    }

    private static int _maxSubArraySumDivideAndConquer(int[] nums, int begin, int end) {
        if(begin == end)
            return nums[begin];

        int mid = (begin+end)/2;

        return Math.max(
            Math.max(_maxSubArraySumDivideAndConquer(nums, begin, mid),
                    _maxSubArraySumDivideAndConquer(nums, mid+1, end)),
            _maxBoundarySum(nums, begin, mid, end)
        );
    }

    private static int _maxBoundarySum(int[] nums, int begin, int mid, int end) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for(int i=mid; i>=begin; i--) {
            sum+=nums[i];
            if(leftSum <= sum)
                leftSum = sum;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for(int i=mid+1; i<=end; i++) {
            sum+=nums[i];
            if(rightSum <= sum)
                rightSum = sum;
        }

        return leftSum+rightSum;
    }

    public static int maxSubArraySumKadaneAlgorithm(int[] nums) {
        int currSum = 0;
        int maxSum = Integer.MIN_VALUE;
        boolean positiveSeen = false;
        int maxNegative = Integer.MIN_VALUE;

        for(int num : nums) {
            if(num >= 0)
                positiveSeen = true;

            if(maxNegative < num)
                maxNegative = num;

            currSum += num;
            if(currSum < 0)
                currSum = 0;
            else if(maxSum < currSum)
                maxSum = currSum;
        }

        return positiveSeen ? maxSum : maxNegative;
    }
}

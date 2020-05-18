package ramesh.aadhavan.dynamic;

/**
 *   A bitonic sequence is defined as any sequence which satisfies the following property
 *   a < b < c < d < .. < o > .. > x > y > z
 *
 *   To compute: at item 0..< i <..n
 *              find length of increasing subsequence from (0..i)   -> f(0, i)
 *              find length of descreasing subsequence from (i..n)  -> g(i, n)
 *              length of bitonic subsequence bitonic(i) -> f(0, i) + g(i, n) - 1 [Since item i would have been computed twice]
 *              length of longest bitonic subsequence = Max(bitonic(i)) i -> 0..n
 */
public class LongestBitonicSubsequence {

    public int lengthOfBitonicSubsequence(int[] nums) {
        int[] lis = new int[nums.length];
        int[] lds = new int[nums.length];

        _computeLengthOfIncreasingSubsequence(nums, lis);
        _computeLengthOfDecreasingSubsequence(nums, lds);

        int[] bitonic = new int[nums.length];
        int max = 1;

        for(int i=0; i<bitonic.length; i++) {
            bitonic[i] = lis[i] + lds[i] - 1;
            max = max < bitonic[i] ? bitonic[i] : max;
        }

        return max;
    }

    private void _computeLengthOfIncreasingSubsequence(int[] nums, int[] lis) {

        lis[0] = 1;

        for(int i=1; i<nums.length; i++) {
            lis[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i] && lis[i] < (1 + lis[j])) {
                    lis[i] = 1 + lis[j];
                }
            }
        }
    }

    private void _computeLengthOfDecreasingSubsequence(int[] nums, int[] lds) {
        int n = nums.length;
        lds[n-1] = 1;

        for(int i=n-2; i>=0; i--) {
            lds[i] = 1;
            for(int j=n-1; j>i; j--) {
                if(nums[j] < nums[i] && lds[i] < (1 + lds[j])) {
                    lds[i] = 1 + lds[j];
                }
            }
        }
    }
}

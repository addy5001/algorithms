package ramesh.aadhavan.dynamic;

import java.util.*;
import java.util.stream.IntStream;

public class LongestIncreasingSubsequence {

    public int lengthOfLIS(int[] nums) {
        return _binarySearchApproach(nums);
    }

    private int _dynamic(int[] nums) {
        if(nums.length <= 1)
            return nums.length;

        int[] lengthLis = new int[nums.length];
        lengthLis[0] = 1;

        int maxLen = 0;
        for (int i=1; i<nums.length; i++) {
            lengthLis[i] = 1;
            for(int j=0; j<i; j++) {
                if(nums[j] < nums[i] && lengthLis[j] < (1 + lengthLis[i]))
                    lengthLis[i] = 1 + lengthLis[j];
            }

            maxLen = maxLen < lengthLis[i] ? lengthLis[i] : maxLen;
        }

        return maxLen;
    }

    private int _dynamic_lcs(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        int n = nums.length;

        int[][] grid = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                if(nums[i] == sorted[j]) {
                    grid[i][j] = (i==0 || j==0) ? 1 : grid[i-1][j-1] + 1;
                }
                else {
                    grid[i][j] = (i==0 || j==0) ? 0 : Math.max(grid[i-1][j], grid[i][j-1]);
                }
            }
        }

        return grid[n-1][n-1];
    }

    private int _patienceSort(int[] nums, List<Deque<Integer>> list) {
        IntStream.range(0, nums.length).forEach(i -> {
            list.add(i, null);
        });

        int stackSize = 0;
        for(int num : nums) {
            int i=0;
            do {
                if(list.get(i) == null) {
                    list.add(i, new ArrayDeque<>());
                    list.get(i).push(num);
                    stackSize++;
                    break;
                }
                else {
                    if(num <= list.get(i).peek()) {
                        list.get(i).push(num);
                        break;
                    }
                    else {
                        i++;
                    }
                }
            } while (i < list.size());
        }

        return stackSize;
    }

    private int _binarySearchApproach(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for (int num : nums) {
            if(list.isEmpty())
                list.add(num);
            else if(list.get(list.size()-1) < num)
                list.add(num);
            else {
                int idxToReplace = _findIdx(num, list, 0, list.size()-1);
                list.set(idxToReplace, num);
            }
        }

        return list.size();
    }

    private int _findIdx(int num, List<Integer> list, int start, int end) {
        int mid = start + (end - start)/2;

        if(list.get(mid) > num && (mid == 0 || list.get(mid-1) < num))
            return mid;
        else if(list.get(mid) < num)
            return _findIdx(num, list, mid+1, end);
        else
            return _findIdx(num, list, start, mid);
    }
}

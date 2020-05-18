package ramesh.aadhavan.misc;

public class TrappingWater {

    public int trap(int[] height) {
        return _naive(height);
    }

    private int _naive(int[] height) {
        int total = 0;

        for(int i=0; i<height.length; i++) {
            int leftMax = findLeftMax(height, i);
            int rightMax = findRightMax(height, i);

            int minHeight = Math.min(leftMax, rightMax);
            total += minHeight - height[i];
        }

        return total;
    }

    private int _dynamic(int[] height) {
        int total = 0;
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];

        leftMax[0] = height[0];
        for(int i=1; i<height.length; i++) {
            leftMax[i] = Math.max(leftMax[i-1], height[i]);
        }

        rightMax[rightMax.length-1] = height[rightMax.length-1];
        for(int i=rightMax.length-2; i>=0; i--) {
            rightMax[i] = Math.max(rightMax[i+1], height[i]);
        }

        for(int i=0; i<height.length; i++) {
            total += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return total;
    }

    private int findLeftMax(int[] height, int idx) {
        int max = 0;
        while(idx >= 0) {
            max = Math.max(max, height[idx]);
            idx--;
        }

        return max;
    }

    private int findRightMax(int[] height, int idx) {
        int max = 0;
        while(idx < height.length) {
            max = Math.max(max, height[idx]);
            idx++;
        }

        return max;
    }

    private int _twoPointerApproach(int[] height) {
        if(height == null || height.length < 2)
            return 0;

        int leftMax = height[0];
        int rightMax = height[height.length-1];

        int left = 0;
        int right = height.length-1;
        int result = 0;

        while(left < right) {
            if(height[left] < height[right]) {
                if(height[left] >= leftMax) {
                    leftMax = height[left];
                }
                else {
                    result += leftMax - height[left];
                }

                left++;
            }
            else {
                if(height[right] >= rightMax) {
                    rightMax = height[right];
                }
                else {
                    result += rightMax - height[right];
                }

                right--;
            }
        }

        return result;
    }
}

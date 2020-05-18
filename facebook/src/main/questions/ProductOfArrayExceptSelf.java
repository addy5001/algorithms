package questions;

public class ProductOfArrayExceptSelf {
    public int[] productExceptSelf(int[] nums) {
        return _productExceptSelf(nums);
    }

    private int[] _productExceptSelf(int[] nums) {
        int[] leftSumArray = new int[nums.length];
        int[] rightSumArray = new int[nums.length];

        leftSumArray[0] = 1;
        rightSumArray[nums.length-1] = 1;

        for(int i=1, j=nums.length-2; i<nums.length; i++, j--) {
            leftSumArray[i] = nums[i-1] * leftSumArray[i-1];
            rightSumArray[j] = nums[j+1] * rightSumArray[j+1];
        }

        int[] productArray = new int[nums.length];

        for(int i=0; i<nums.length; i++) {
            productArray[i] = leftSumArray[i] * rightSumArray[i];
        }

        return productArray;
    }

    public int[] productExceptSelfSpaceEfficient(int[] nums) {
        int[] result = new int[nums.length];

        result[nums.length-1]=1;
        for(int i=nums.length-2; i>=0; i--){
            result[i]=result[i+1]*nums[i+1];
        }

        int left=1;
        for(int i=0; i<nums.length; i++){
            result[i]=result[i]*left;
            left = left*nums[i];
        }

        return result;
    }
}

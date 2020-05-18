package ramesh.aadhavan.misc;

public class HouseRobber {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0)
            return 0;

        if(nums.length == 1)
            return nums[0];
        else if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        else
            return _rob(nums);
    }

    private int _rob(int[] nums) {
        int cols = nums.length;
        int[] table = new int[cols];
        table[0] = nums[0];
        table[1] = nums[1];

        for(int i=2; i<cols; i++) {
            table[i] = (i-3) < 0 ? Math.max(table[i-1], nums[i] + table[i-2]) :
                    Math.max(table[i-1], Math.max(nums[i] + table[i-2], nums[i] + table[i-3]));
        }

        return table[cols-1];
    }

    public static void main(String[] args) {
        int[] rob = {2, 4, 8, 9, 9, 3};
        System.out.println(new HouseRobber().rob(rob));
    }
}

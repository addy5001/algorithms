package ramesh.aadhavan.misc;

public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        return _maxArea(height, 0, height.length-1);
    }

    private int _maxArea(int[] height, int begin, int end) {
        if(begin >= end)
            return 0;

        int width = Math.min(height[begin], height[end]);
        int length = end - begin;
        int area = width * length;

        return Math.max(area, Math.max(_maxArea(height, begin+1, end), _maxArea(height, begin, end-1)));
    }

    private int _maxAreaOptimized(int[] height, int begin, int end) {
        if(begin >= end)
            return 0;

        int width = Math.min(height[begin], height[end]);
        int length = end - begin;
        int area = width * length;

        int recursiveArea = (height[begin] > height[end]) ?
                _maxAreaOptimized(height, begin, end-1) :
                _maxAreaOptimized(height, begin+1, end);

        return Math.max(area, recursiveArea);
    }
}

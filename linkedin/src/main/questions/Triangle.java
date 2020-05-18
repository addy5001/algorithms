package questions;

import java.util.Arrays;

public class Triangle {

    /*
    Triangle Property
    Sides: A, B, C

    A + B > C
    B + C > A
    C + A > B
     */

    public boolean isTriangle(int[] segments) {
        return _isTriangleOptimized(segments);
    }

    /**
     * Time Complexity O(n^3)
     *
     * @param segments
     * @return
     */
    private boolean _isTriangleNaive(int[] segments) {
        int n = segments.length;

        for(int i=0; i<n-2; i++) {
            int a = segments[i];
            for(int j=i+1; j<n-1; j++) {
                int b = segments[j];
                for(int k=j+1; k<n; k++) {
                    int c = segments[k];

                    if((a + b) > c && (b + c) > a && (c + a) > b)
                        return true;
                }
            }
        }

        return false;
    }

    /**
     * Time Complexity: O(nlogn)
     *
     * @param segments
     * @return
     */
    private boolean _isTriangleOptimized(int[] segments) {
        int n = segments.length;
        Arrays.sort(segments);

        for(int i=0; i<n-2; i++) {
            if(segments[i] + segments[i+1] > segments[i+2])
                return true;
        }

        return false;
    }
}

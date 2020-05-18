package questions;

import java.util.Arrays;

public class StringRotation {

    public boolean isRotation(String initial, String toCompare) {
        if(initial.length() != toCompare.length())
            return false;

        char[] toCompareArr = toCompare.toCharArray();
        char[] initialArr = initial.toCharArray();

        int i=0;
        while(i < toCompare.length()) {
            if(Arrays.equals(initialArr, toCompareArr))
                return true;

            char c = toCompareArr[toCompareArr.length - 1];
            System.arraycopy(toCompareArr, 0, toCompareArr, 1, toCompareArr.length - 1);
            toCompareArr[0] = c;

            i++;
        }

        return false;
    }

    public boolean isRotationOptimized(String initial, String toCompare) {
        String doubleInitial = initial.concat(initial);
        return doubleInitial.contains(toCompare);
    }
}

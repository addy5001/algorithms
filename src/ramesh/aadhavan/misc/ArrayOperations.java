package ramesh.aadhavan.misc;

import java.util.Arrays;

public class ArrayOperations {
    public static int count(int[] array) {
        if(array.length == 1)
            return 1;
        return 1 + count(Arrays.copyOf(array, array.length - 1));
    }

    public static int sum(int[] array) {
        if(array.length == 1)
            return array[0];
        return array[0] + sum(Arrays.copyOfRange(array, 1, array.length));
    }

    public static int binarySearch(int[] array, int element, int start, int end) {
        if(start <= end) {
            int mid = (end + start)/2;

            if(element == array[mid])
                return mid;

            else if (array[mid] > element)
                return binarySearch(array, element, start, mid - 1);

            return binarySearch(array, element, mid + 1, end);
        }

        return -1;
    }

}

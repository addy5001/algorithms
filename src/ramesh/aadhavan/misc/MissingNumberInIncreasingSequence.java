package ramesh.aadhavan.misc;

/**
 *         1 2 3 4 5 7 (missing number is 4)
 *    pos: 0 1 2 3 4 5
 *
 *    Concept: binary search correctly placed numbers
 *
 *    mid = (start + end)/2
 *    if((array[mid] != mid+1) && (mid == 0 || array[mid-1] < array[mid]-1))
 *      return mid+1;
 *    else if(array[mid] == mid+1)
 *      return binarySearch(array, mid+1, end);
 *    else
 *      return binarySearch(array, begin, mid-1);
 */
public class MissingNumberInIncreasingSequence {

    public int findMissingNumber(int[] arr) {
        Integer missing = _findMissingNumber(arr, 0, arr.length-1);
        return missing != null ? missing : arr.length+1;
    }

    private Integer _findMissingNumber(int[] arr, int low, int high) {
        if(low > high)
            return null;

        int mid = (low + high)/2;

        if(arr[mid] != (mid+1) && ((mid == 0) || arr[mid-1] == mid))
            return mid+1;
        else if(arr[mid] > (mid+1))
            return _findMissingNumber(arr, low, mid-1);
        else
            return _findMissingNumber(arr, mid+1, high);
    }
}

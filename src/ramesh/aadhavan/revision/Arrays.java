package ramesh.aadhavan.revision;

import com.google.common.base.Preconditions;

public class Arrays {
    public int removeDuplicatesInPlace(int[] arr, int numDuplicatesAllowed) {
        Preconditions.checkNotNull(arr);
        if(arr.length <= 1)
            return arr.length;

        int nonDupIdx=0, current=1;
        int dupCounter = numDuplicatesAllowed;

        while (current < arr.length) {
            if(arr[nonDupIdx] == arr[current] && dupCounter > 0) {
                dupCounter--;
                arr[++nonDupIdx] = arr[current];
                current++;
                continue;
            }

            if(arr[nonDupIdx] != arr[current]) {
                arr[++nonDupIdx] = arr[current];
                dupCounter = numDuplicatesAllowed;
            }

            current++;
        }

        return nonDupIdx+1;
    }

    /**
     * Keep two indexes, slow and forward.
     * If forward = elem, forward++
     * else A[slow] = A[forward], slow++, forward++
     * @param A
     * @param elem
     * @return
     */
    public int removeElementInPlace(int[] A, int elem) {
        Preconditions.checkNotNull(A);
        if(A.length == 0)
            return 0;

        if(A.length == 1 && A[0] == elem)
            return 0;

        int remIdx=0, j=0;
        while(j < A.length) {
            if(A[j] != elem) {
                A[remIdx] = A[j];
                remIdx++;
            }

            j++;
        }

        return remIdx;
    }

    /**
     * Very similar to {@code removeElementInPlace}:
     *  1. two indexes, slow and forward with forward checking for zeroes and non-zeroes
     *  2. keep track of number of zeroes
     *  3. after moving non-zeroes, keep adding zeroes till count = 0
     * @param nums
     */
    public void moveZeroes(int[] nums) {
        Preconditions.checkNotNull(nums);
        if(nums.length <= 1)
            return;

        int numZeroes = 0;
        int idx = 0, fwdIdx = 0;

        while(fwdIdx < nums.length) {
            if(nums[fwdIdx] != 0) {
                nums[idx] = nums[fwdIdx];
                idx++;
            }
            else {
                numZeroes++;
            }

            fwdIdx++;
        }

        while(numZeroes > 0) {
            nums[idx++] = 0;
            numZeroes--;
        }
    }
}

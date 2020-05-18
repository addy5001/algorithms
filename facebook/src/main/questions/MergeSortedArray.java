package questions;

public class MergeSortedArray {
    public int[] mergeSortedArrays(int[] A, int[] B) {
        int[] merged = new int[A.length + B.length];

        int i = 0, j=0;
        int k = 0;

        while(i < A.length && j < B.length) {
            if(A[i] < B[j]) {
                merged[k] = A[i];
                i++;
            }
            else {
                merged[k] = B[j];
                j++;
            }

            k++;
        }

        while(i < A.length) {
            merged[k] = A[i];
            i++;
            k++;
        }

        while(j < B.length) {
            merged[k] = B[j];
            j++;
            k++;
        }

        return merged;
    }

    /**
     * Assume A has enough space after its elements of length aLen.
     * @param A
     * @param B
     * @return
     */
    public int[] mergeSortedArraysInPlace(int[] A, int aLen, int[] B, int bLen) {
        System.arraycopy(A, 0, A, bLen, aLen);

        int i = bLen;
        int j = 0;
        int k = 0;

        while(i < A.length && j < bLen) {
            if(A[i] < B[j]) {
                A[k] = A[i];
                i++;
            }
            else {
                A[k] = B[j];
                j++;
            }

            k++;
        }

        while(i < aLen) {
            A[k] = A[i];
            i++;
            k++;
        }

        while(j < bLen) {
            A[k] = B[j];
            j++;
            k++;
        }

        return A;
    }
}

package ramesh.aadhavan.revision;

import org.junit.Test;

public class ArraysTest {

    final Arrays arrays = new Arrays();

    @Test
    public void testRemoveDuplicatesInPlace_same() {
        int[] arr = {1,1,1,1,1,1,1,1,1,1,1};

        int idx = arrays.removeDuplicatesInPlace(arr,3);
        for(int i=0; i<idx; i++)
            System.out.print(arr[i]+" ");
    }

    @Test
    public void testRemoveDuplicatesInPlace() {
        int[] arr = {1,1,1,1,1,1,2,2,2,2,4,4,4,4,4,6,6,6,6,6,7,8,9,10,11,11,56,78,89};

        int idx = arrays.removeDuplicatesInPlace(arr,2);
        for(int i=0; i<idx; i++)
            System.out.print(arr[i]+" ");
    }

    @Test
    public void testRemoveElementInPlace() {
        int[] arr = {1,1,1,1,1,1,2,2,2,2,4,4,4,4,4,6,6,6,6,6,7,8,9,10,11,11,56,78,89};

        int idx = arrays.removeElementInPlace(arr, 4);
        for(int i=0; i<idx; i++)
            System.out.print(arr[i]+" ");
    }

    @Test
    public void testMoveZeroes() {
        int[] arr = {0, 1, 0, 3, 12, 0, 0, 0, 0, 0, 1};
        arrays.moveZeroes(arr);
        java.util.Arrays.stream(arr).forEach(System.out::println);
    }
}

package ramesh.aadhavan.sort;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomizedQuickSort {

    private int findPivot(int[] arr) {
        Random random = new Random();
        return random.nextInt(arr.length);
    }

    private static void shuffle(int[] arr) {
        Random random = new Random();
        int bound = arr.length;
        for(int i=0; i<arr.length; i++) {
            int idx = random.nextInt(bound);

            int temp = arr[i];
            arr[i] = arr[idx];
            arr[idx] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,0};
        shuffle(arr);
        Arrays.stream(arr).forEach(System.out::println);
    }
}

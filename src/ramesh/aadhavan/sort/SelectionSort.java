package ramesh.aadhavan.sort;

public class SelectionSort {


    public static void main(String[] args) {
        int[] a = {3,4,5,62,1,324,55,23, -1, 2, 4, -4, 123, 32, 12, 445, 334, 12324, 34};
        System.out.println(""+findMin(a, 0, 3));
        selectionSort(a);
        for(int i : a) {
            System.out.print(i+" ");
        }
    }


    private static void selectionSort(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            int loc = findMin(arr, i, arr.length);
            int temp = arr[i];
            arr[i] = arr[loc];
            arr[loc] = temp;
        }
    }

    private static int findMin(int [] arr, int start, int end) {
        int min = arr[start];
        int loc = start;
        for(int i=start+1; i<end; i++) {
            if(min > arr[i]) {
                min = arr[i];
                loc = i;
            }
        }
        return loc;
    }
}

package ramesh.aadhavan.sort;

public class InsertionSort {
    public static int[] sort(int[] arr) {
        int n = arr.length;

        for(int i=1; i<n; i++) {
            int key = arr[i];
            int j = i-1;

            while(j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 5, 7, 100, 101, 99, 23 ,2 ,434 ,23, 1233, 1,12};
        int[] sorted = sort(arr);

        for(int i : sorted) {
            System.out.print(i + " ");
        }
    }

    public static void insertionSort(int arr[]) {
        for(int i=1; i<arr.length; i++) {
            int key = arr[i];
            int j=i-1;
            while(j>=0 && arr[j] > key) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = key;
        }
    }
}

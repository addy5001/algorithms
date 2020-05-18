package ramesh.aadhavan.misc;

public class QuickSelect {

    private static int partition(int[] arr, int low, int high) {
        int i=low;
        int j= high+1;

        while(true) {
            while(i < high && arr[++i] < arr[low]);
            while (j > low && arr[low] < arr[--j]);

            if(i>=j) break;

            int temp = arr[i];
            arr[i]   = arr[j];
            arr[j]   = temp;
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j]   = temp;

        return j;
    }

    public static int select(int[] arr, int k) {
        int low = 0;
        int high = arr.length-1;

        while(low <= high) {
            int pivotPos = partition(arr, low, high);

            if(pivotPos == k)
                return arr[pivotPos];
            else if(pivotPos < k)
                low = pivotPos + 1;
            else
                high = pivotPos - 1;
        }

        return -1;
    }
}

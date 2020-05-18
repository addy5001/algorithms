package ramesh.aadhavan.sort;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 5, 7, 100, 123, 234, 123, 121, 1232, 12, 12, 5, 235, 2, 142, 101, 99, 23 ,2 , 2, 2, 2, 4, 5, 6, 7};

        sort(arr, 0, arr.length-1);

        for(int i : arr) {
            System.out.print(i + " ");
        }
    }

    public static void sort(int[] arr, int p, int r) {
        if(p < r) {
            int q = (p + r)/2;
            sort(arr, p, q);
            sort(arr, q+1, r);
            merge(arr, p, q, r);
        }
    }

    private static void merge(int[] arr, int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1];
        int[] R = new int [n2];

        for(int i=0; i<n1; i++) {
            L[i] = arr[p+i];
        }
        for(int j=0; j<n2; j++) {
            R[j] = arr[q+1+j];
        }

        int i = 0;
        int j = 0;
        int k = p;

        while(i<n1 && j<n2) {
            if(L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while(i<n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while(j<n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


}

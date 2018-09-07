package ramesh.aadhavan.sort;

public class CountingSort {

    public static int[] sort(int[] arr) {
        int max = findMax(arr);
        int[] countingArr = new int[max+1];

        for(int i=0; i<max; i++)
            countingArr[i] = 0;

        for(int element : arr)
            countingArr[element]+=1;

        int[] sortedArr = new int[arr.length];
        int j = 0;

        for(int i=0; i<countingArr.length; i++) {
            while(countingArr[i] > 0) {
                sortedArr[j] = i;
                j++;
                countingArr[i]--;
            }
        }

        return sortedArr;
    }

    private static int findMax(int[] arr) {
        int max = 0;
        for(int item : arr)
            if(max < item)
                max = item;

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {12, 3, 4, 5, 7, 100, 123, 234, 123, 121, 1232, 12, 12, 5, 235, 2, 142, 101, 99, 23 ,2 , 2, 2, 2, 4, 5, 6, 7};
        int[] sorted = sort(arr);

        for(int i : sorted) {
            System.out.print(i + " ");
        }
    }
}

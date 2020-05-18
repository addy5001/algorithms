package ramesh.aadhavan.sort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class QuickSort {

    public static List<Integer> sort(List<Integer> list) {
        if(list.size() < 2)
            return list;
        else {
            int pivot = list.get(0);
            List<Integer> lesser = list.subList(1, list.size())
                    .stream()
                    .filter(item -> item <= pivot)
                    .collect(Collectors.toList());

            List<Integer> greater = list.subList(1, list.size())
                    .stream()
                    .filter(item -> item > pivot)
                    .collect(Collectors.toList());

            List<Integer> sorted = new ArrayList<>();
            sorted.addAll(sort(lesser));
            sorted.add(pivot);

            List<Deque<Integer>> list1 = new ArrayList<>();
            list1.stream().filter(Objects::nonNull).map(Deque::size).mapToInt(Integer::intValue).min().getAsInt();
            sorted.addAll(sort(greater));
            return sorted;
        }
    }

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    public static void sort(int[] arr, int low, int high) {
        if(low < high) {
            int pivotPos = partition(arr, low, high);

            sort(arr, low, pivotPos-1);
            sort(arr, pivotPos+1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];

        int i=low;
        int j=high+1;

        while(true) {
            while(i < high && arr[++i] <= pivot);
            while (j > low && arr[--j] > pivot);

            if(i >= j)
                break;

            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j;
    }
}

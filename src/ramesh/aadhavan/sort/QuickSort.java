package ramesh.aadhavan.sort;

import java.util.ArrayList;
import java.util.List;
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
            sorted.addAll(sort(greater));
            return sorted;
        }
    }
}

package ramesh.aadhavan.misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SetPartitionProblem {

    public static void setPartition(List<Integer> initial) {
        int sum = initial.stream().mapToInt(Integer::intValue).sum();

        if((sum & 1) == 1) {
            System.out.println("Unable to partition odd sum set");
            return;
        }

        int n = initial.size();
        int[][] matrix = new int[n][n];
        int halfSum = sum/2;
        List<Integer> set1 = new ArrayList<>();
        List<Integer> set2 = new ArrayList<>();
        boolean found = false;

        for(int i=0; i<n; i++) {
            List<Integer> s1 = new ArrayList<>();
            List<Integer> s2 = new ArrayList<>();
            for(int j=0; j<n; j++) {
                if(i==0 && j==0) {
                    matrix[i][j] = initial.get(i);
                    s1.add(initial.get(i));
                }
                else if(j == 0) {
                    int columnSum = initial.get(i) + initial.get(0);
                    s1.add(initial.get(i));
                    if(columnSum <= halfSum) {
                        matrix[i][0] = columnSum;
                        s1.add(initial.get(0));
                    }
                    else {
                        matrix[i][0] = initial.get(i);
                        s2.add(initial.get(0));
                    }
                }
                else if(i == j)
                    matrix[i][j] = matrix[i][j-1];
                else {
                    int rowSum = matrix[i][j-1];
                    int columnElement = initial.get(j);
                    if(rowSum + columnElement <= halfSum) {
                        matrix[i][j] = rowSum + columnElement;
                        s1.add(columnElement);
                    }
                    else {
                        matrix[i][j] = matrix[i][j-1];
                        s2.add(columnElement);
                    }
                }
            }
            if(matrix[i][n-1] == halfSum) {
                set1 = s1;
                set2 = s2;
                found = true;
                break;
            }
        }

        if(found) {
            System.out.print("Set 1: ");
            set1.forEach(i -> System.out.print(i+" "));
            System.out.println();
            System.out.print("Set 2: ");
            set2.forEach(i -> System.out.print(i+" "));
            System.out.println();
        }
        else {
            System.out.println("No equal sum partitions");
        }
    }

    public static void main(String[] args) {
        setPartition(Arrays.asList(12,4,8,2));
    }
}

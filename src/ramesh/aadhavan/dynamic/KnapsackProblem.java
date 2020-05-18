package ramesh.aadhavan.dynamic;

import ramesh.aadhavan.misc.Item;

import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KnapsackProblem {
    public static void findItems(Item[] items, int knapsackWeight) {
        int minWeight = Stream.of(items)
                .mapToInt(Item::getWeight)
                .min()
                .orElseThrow(() -> new IllegalArgumentException("No minimum found"));

        int gridSeparator;
        if(knapsackWeight%minWeight == 0) gridSeparator = minWeight;
        else gridSeparator = 1;

        int numColumns = knapsackWeight/gridSeparator;
        int numRows = items.length;

        int[][] grid = new int[numRows][numColumns];

        for(int i=0; i<numRows; i++) {
            for(int j=0; j<numColumns; j++) {
                if(items[i].getWeight() < gridSeparator * (j+1)) {
                    if((i-1) < 0 || (j-1)<0 || j-items[i].getWeight() < 0) {
                        if((i-1) < 0)
                            grid[i][j] = items[i].getWorth();
                        else
                            grid[i][j] = Math.max(grid[i-1][j], items[i].getWorth());
                    }
                    else {
                        grid[i][j] = Math.max(grid[i-1][j], items[i].getWorth() +
                                grid[i-1][j-items[i].getWeight()]);
                    }
                }
            }
        }

        System.out.println("------------GRID FOR KNAPSACK------------");
        for(int i=0; i<numRows; i++) {
            for (int j = 0; j < numColumns; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        Item[] items = new Item[3];
        items[0] = new Item(3, 200);
        items[1] = new Item(2, 1000);
        items[2] = new Item(1, 700);

        Stream.of(items).forEach(System.out::println);
        System.out.println();

        findItems(items, 4);
    }
}

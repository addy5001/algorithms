package ramesh.aadhavan.greedy;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KnapsackProblem {

    public static class Item {
        private final int weight;
        private final int worth;

        public Item(int weight, int worth) {
            this.weight = weight;
            this.worth = worth;
        }

        public int getWeight() {
            return weight;
        }

        public int getWorth() {
            return worth;
        }

        @Override
        public String toString() {
            return String.format("[Weight=%d,Worth=%d]", weight, worth);
        }
    }

    public static Item[] findItems(Item[] items, int knapSackWeight) {
        List<Item> itemList = Arrays.asList(items);
        Collections.sort(itemList, (i1, i2) -> Integer.compare(i2.getWorth(), i1.getWorth()));

        int weightCounter = 0;
        List<Item> finalItems = new ArrayList<>();
        for(Item item : itemList) {
            if(weightCounter + item.getWeight() < knapSackWeight) {
                finalItems.add(item);
                weightCounter = weightCounter + item.getWeight();
            }
            else break;
        }

        return finalItems.toArray(new Item[0]);
    }

    public static void main(String[] args) {
        Random random = new Random();
        KnapsackProblem.Item[] items = new KnapsackProblem.Item[10];
        IntStream.range(0, 10)
                .forEach(i -> items[i] = new KnapsackProblem.Item(random.nextInt(50), random.nextInt(1000)));

        Stream.of(items).forEach(System.out::println);
        System.out.println();

        KnapsackProblem.Item[] finalItems = KnapsackProblem.findItems(items, 50);
        System.out.println("-----FINAL ITEMS-----");
        Stream.of(finalItems).forEach(System.out::println);
        int totalWorth = Stream.of(finalItems).mapToInt(KnapsackProblem.Item::getWorth).sum();
        System.out.println("-----TOTAL WORTH-----");
        System.out.println(totalWorth);
    }
}


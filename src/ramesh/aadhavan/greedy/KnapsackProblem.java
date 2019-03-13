package ramesh.aadhavan.greedy;

import ramesh.aadhavan.misc.Item;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KnapsackProblem {

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
        Item[] items = new Item[10];
        IntStream.range(0, 10)
                .forEach(i -> items[i] = new Item(random.nextInt(50), random.nextInt(1000)));

        Stream.of(items).forEach(System.out::println);
        System.out.println();

        Item[] finalItems = KnapsackProblem.findItems(items, 50);
        System.out.println("-----FINAL ITEMS-----");
        Stream.of(finalItems).forEach(System.out::println);
        int totalWorth = Stream.of(finalItems).mapToInt(Item::getWorth).sum();
        System.out.println("-----TOTAL WORTH-----");
        System.out.println(totalWorth);
    }
}


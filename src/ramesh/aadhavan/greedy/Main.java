package ramesh.aadhavan.greedy;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
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

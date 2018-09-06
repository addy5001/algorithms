package ramesh.aadhavan.greedy;

import java.util.*;

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
        int listCounter = 0;
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
}


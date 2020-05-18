package ramesh.aadhavan.misc;

import java.util.*;

public class KFrequentElements {

    static class Element {
        private final int val;
        private int freq = 1;

        public Element(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }

        public void incrementFreq() {
            freq++;
        }
    }

    static class FreqComparator implements Comparator<Element> {
        @Override
        public int compare(Element o1, Element o2) {
            return o1.freq - o2.freq;
        }
    }

    public static List<Integer> getKFreqElements(int[] elements, int k) {
        if(elements == null || elements.length == 0)
            return Collections.emptyList();

        Queue<Element> freqQ = new PriorityQueue<>(new FreqComparator().reversed());
        Map<Integer, Element> map = new HashMap<>();

        for(int i=0; i<elements.length; i++) {
            if(map.containsKey(elements[i])) {
                map.get(elements[i]).incrementFreq();
            }
            else {
                map.put(elements[i], new Element(elements[i]));
            }
        }

        freqQ.addAll(map.values());
        List<Integer> kFreqValues = new ArrayList<>();
        for(int i=0; i<k; i++) {
            if(freqQ.peek() == null)
                break;

            kFreqValues.add(freqQ.poll().getVal());
        }

        return kFreqValues;
    }
}

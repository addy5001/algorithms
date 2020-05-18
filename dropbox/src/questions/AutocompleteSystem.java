package questions;

import java.util.*;
import java.util.stream.Collectors;

public class AutocompleteSystem {

    class SentenceItem {
        String sentence;
        int times;

        public SentenceItem(String sentence, int times) {
            this.sentence = sentence;
            this.times = times;
        }

        public int getTimes() {
            return times;
        }

        public String getSentence() {
            return sentence;
        }
    }

    Map<String, SentenceItem> map;
    private StringBuilder currentTrackedSentence = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
        map = new HashMap<>();
        for(int i=0; i<sentences.length; i++) {
            map.put(sentences[i], new SentenceItem(sentences[i], times[i]));
        }
    }

    public List<String> input(char c) {
        List<String> results = new ArrayList<>();

        if(c == '#') {
            map.compute(currentTrackedSentence.toString(), (key, oldVal) -> {
                if(oldVal == null) {
                    return new SentenceItem(key, 1);
                }
                else {
                    oldVal.times++;
                    return oldVal;
                }
            });

            currentTrackedSentence.setLength(0);
        }
        else {
            currentTrackedSentence.append(c);
            results = map.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().indexOf(currentTrackedSentence.toString()) == 0)
                    .map(Map.Entry::getValue)
                    .sorted(Comparator
                            .comparingInt(SentenceItem::getTimes).reversed()
                            .thenComparing(SentenceItem::getSentence))
                    .limit(3)
                    .map(SentenceItem::getSentence)
                    .collect(Collectors.toList());
        }

        return results;
    }
}

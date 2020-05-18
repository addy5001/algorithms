package questions;

import java.util.*;

public class ReorganizeString {

    class AnnotatedNode {
        char c;
        int count;

        public AnnotatedNode(char c, int count) {
            this.c = c;
            this.count = count;
        }

        public char getC() {
            return c;
        }

        public int getCount() {
            return count;
        }
    }

    public String reorganizeString(String S) {
        return _reorganizeStringHeap(S);
    }

    private String _reorganizeStringHeap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int moreThanHalf = (n+1)/2;

        for(char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if(map.get(c) > moreThanHalf)
                return "";
        }

        Queue<AnnotatedNode> queue = new PriorityQueue<>(Comparator.comparingInt(AnnotatedNode::getCount).reversed());
        map.entrySet()
                .stream()
                .map(entry -> new AnnotatedNode(entry.getKey(), entry.getValue()))
                .forEach(queue::add);

        StringBuilder sb = new StringBuilder();
        while(queue.size() >= 2) {
            AnnotatedNode first = queue.poll();
            AnnotatedNode second = queue.poll();

            sb.append(first.c);
            sb.append(second.c);

            first.count--;
            if(first.count > 0)
                queue.add(first);

            second.count--;
            if(second.count > 0)
                queue.add(second);
        }

        if(!queue.isEmpty()) {
            sb.append(queue.poll().c);
        }

        return sb.toString();
    }
}

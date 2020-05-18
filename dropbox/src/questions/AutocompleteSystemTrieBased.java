package questions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class AutocompleteSystemTrieBased {
    class Node {
        String sentence;
        int times;

        Node(String st, int t) {
            sentence = st;
            times = t;
        }

        public String getSentence() {
            return sentence;
        }

        public int getTimes() {
            return times;
        }
    }

    class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    private Trie root;
    private StringBuilder cur_sent = new StringBuilder();

    public AutocompleteSystemTrieBased(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }

    private int toInt(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(Trie t, String s, int times) {
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[toInt(s.charAt(i))] == null) {
                t.branches[toInt(s.charAt(i))] = new Trie();
            }
            t = t.branches[toInt(s.charAt(i))];
        }
        t.times += times;
    }

    private List<Node> lookup(Trie t, String s) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[toInt(s.charAt(i))] == null) {
                return new ArrayList<>();
            }
            t = t.branches[toInt(s.charAt(i))];
        }
        traverse(s, t, list);
        return list;
    }

    private void traverse(String s, Trie t, List<Node> list) {
        if (t.times > 0)
            list.add(new Node(s, t.times));
        for (char i = 'a'; i <= 'z'; i++) {
            if (t.branches[i - 'a'] != null) {
                traverse(s + i, t.branches[i - 'a'], list);
            }
        }
        if (t.branches[26] != null) {
            traverse(s + ' ', t.branches[26], list);
        }
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(root, cur_sent.toString(), 1);
            cur_sent.setLength(0);
        } else {
            cur_sent.append(c);
            res = lookup(root, cur_sent.toString()).stream()
                    .sorted(
                            Comparator.comparingInt(Node::getTimes).reversed()
                            .thenComparing(Node::getSentence)
                    )
                    .map(node -> node.sentence)
                    .limit(3)
                    .collect(Collectors.toList());
        }
        return res;
    }
}

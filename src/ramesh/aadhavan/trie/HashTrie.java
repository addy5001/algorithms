package ramesh.aadhavan.trie;

import java.util.HashMap;
import java.util.Optional;

public class HashTrie<T> {
    static class Node<T> {
        private T val;
        private HashMap<Character, Node<T>> nodes = new HashMap<>();

        public Node() {
        }

        public Node(T val) {
            this.val = val;
        }

        public T getVal() {
            return val;
        }
    }

    private HashMap<Character, Node<T>> root = new HashMap<>();

    public void put(String key, T val) {
        char[] keys = key.toCharArray();

        HashMap<Character, Node<T>> nodes = root;
        for(int i=0; i<keys.length; i++) {
            if(i == keys.length - 1) {
                nodes.put(keys[i], new Node<>(val));
                return;
            }

            if(!nodes.containsKey(keys[i])) {
                nodes.put(keys[i], new Node<>());
            }
            nodes = nodes.get(keys[i]).nodes;
        }
    }

    public Optional<T> get(String key) {
        char[] keys = key.toCharArray();

        HashMap<Character, Node<T>> nodes = root;
        for(int i=0; i<keys.length; i++) {
            if(i == keys.length - 1) {
                return Optional.ofNullable(nodes.get(keys[i]).val);
            }

            if(!nodes.containsKey(keys[i])) {
                return Optional.empty();
            }
            nodes = nodes.get(keys[i]).nodes;
        }

        return Optional.empty();
    }
}

package ramesh.aadhavan.trie;

public class Trie {
    static class Node {
        private int value;
        private Node[] nodes = new Node[26];

        public int getValue() {
            return value;
        }
    }

    private Node[] root = new Node[26];

    public void put(String key, int val) {
        char[] chars = key.toCharArray();
        if(root[_getIndex(chars[0])] == null) {
            root[_getIndex(chars[0])] = new Node();
        }

        if(chars.length == 1) {
            root[_getIndex(chars[0])].value = val;
            return;
        }

        Node node = root[_getIndex(chars[0])];

        for(int i=1; i<chars.length; i++) {
            if(node.nodes[_getIndex(chars[i])] == null) {
                node.nodes[_getIndex(chars[i])] = new Node();
            }

            if(i == chars.length - 1) {
                node.nodes[_getIndex(chars[i])].value = val;
            }
            else {
                node = node.nodes[_getIndex(chars[i])];
            }
        }
    }

    public int get(String key) {
        char[] keys = key.toCharArray();

        Node[] nodes = root;
        for(int i=0; i<keys.length; i++) {
            if(i == keys.length - 1) {
                if(nodes[_getIndex(keys[i])] == null) {
                    throw new IllegalArgumentException("Key doesn't exist");
                }
                else {
                    return nodes[_getIndex(keys[i])].value;
                }
            }

            Node next = nodes[_getIndex(keys[i])];
            if(next == null) {
                throw new IllegalArgumentException("Key doesn't exist");
            }
            else {
                nodes = next.nodes;
            }
        }

        throw new IllegalArgumentException("Key doesn't exist");
    }

    private int _getIndex(char x) {
        if(x < 97 || x > 122)
            throw new IllegalArgumentException("Index not within accepted range");

        return x - 97;
    }
}

package questions;

import java.util.function.Function;

public class AddSearchWordDictionary {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String item = "";

        TrieNode() { }
    }

    TrieNode root;

    private Function<Character, Integer> getIndexFunc = character -> character - 'a';

    /**
     * addWord() - O(n), n = length of the new word
     * search() - Worst case: O(m), m = the total number of characters in the Trie
     */

    /** Initialize your data structure here. */
    public AddSearchWordDictionary() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        TrieNode temp = root;
        for(char w : word.toCharArray()) {
            if(temp.children[w - 'a'] == null) {
                temp.children[w - 'a'] = new TrieNode();
            }

            temp = temp.children[w - 'a'];
        }

        temp.item = word;
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return _search(root, word, 0, word.length());
    }

    private boolean _search(TrieNode node, String word, int begin, int end) {
        if(node == null || begin > end)
            return false;

        if(begin == end) {
            return !node.item.equals("");
        }

        char c = word.charAt(begin);

        if(c != '.') {
            return (node.children[c - 'a'] != null && _search(node.children[c - 'a'], word, begin+1, end));
        }
        else {
            for(int i=0; i<node.children.length; i++) {
                if(node.children[i] != null && _search(node.children[i], word, begin+1, end))
                    return true;
            }

            return false;
        }
    }
}

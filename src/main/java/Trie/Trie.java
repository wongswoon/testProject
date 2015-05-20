package Trie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 15-5-10.
 */


public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    // Inserts a word into the trie.
    public void insert(String word) {
        root.insert(word, 1);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        int res = root.find(word);
        return res != -1 && res != 0;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return root.find(prefix) != -1;
    }
}

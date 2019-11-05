package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Implement a trie with insert, search, and startsWith methods.
Example:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true
Note:
You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */
public class __208_ImplementTriePrefixTree {

    @Test
    public void given() {
        Trie trie = new Trie();
        trie.insert("apple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertFalse(trie.search("app"));
        Assert.assertTrue(trie.startsWith("app"));
        trie.insert("app");
        Assert.assertTrue(trie.search("app"));
        trie.insert("appleapple");
        Assert.assertTrue(trie.search("apple"));
        Assert.assertTrue(trie.startsWith("apple"));

    }

    class Trie {
        private class TrieNode {
            TrieNode[] sons;
            boolean wordEnd;
            public TrieNode() {
                sons = new TrieNode['z' - 'a'];
                wordEnd = false;
            }

            public TrieNode getOrCreate(char c) {
                if (sons[c - 'a'] == null) {
                    sons[c - 'a'] = new TrieNode();
                }
                return sons[c - 'a'];
            }

            public TrieNode get(char c) {
                return sons[c - 'a'];
            }

            public void setWordEnd() {
                wordEnd = true;
            }

            public boolean isWordEnd() {
                return wordEnd;
            }
        }
        private final TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode support = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                support = support.getOrCreate(c);
            }
            support.setWordEnd();
        }

        public boolean search(String word) {
            TrieNode support = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                support = support.get(c);
                if (support == null)
                    return false;
            }
            return support.isWordEnd();
        }

        public boolean startsWith(String prefix) {
            TrieNode support = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                support = support.get(c);
                if (support == null)
                    return false;
            }
            return true;
        }
    }
}

package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 * <p>
 * Implement the WordDictionary class:
 * <p>
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 */
public class _211_DesignAddAndSearchWordsDataStructure {

    @Test
    public void test() {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        assertFalse(wordDictionary.search("pad"));
        assertTrue(wordDictionary.search("bad"));
        assertTrue(wordDictionary.search(".ad"));
        assertTrue(wordDictionary.search("b.."));
    }

    class WordDictionary {

        private final Node root;

        public WordDictionary() {
            root = new Node();
        }

        public void addWord(String word) {
            root.add(word);
        }

        public boolean search(String word) {
            return root.search(word);
        }

        private class Node {
            Node[] siblings = new Node[26];

            public void add(String word) {
                if (word.length() == 0)
                    return;
                char c = word.charAt(0);
                if (siblings[c - 'a'] == null)
                    siblings[c - 'a'] = new Node();
                siblings[c - 'a'].add(word.substring(1));
            }

            public boolean search(String word) {
                if (word.length() == 0)
                    return true;

                char c = word.charAt(0);
                if (c == '.') {
                    for (Node node : siblings) {
                        if (node != null) {
                            if (node.search(word.substring(1)))
                                return true;
                        }
                    }
                    return false;
                } else {
                    if (siblings[c - 'a'] == null)
                        return false;
                    else
                        return siblings[c - 'a'].search(word.substring(1));
                }
            }
        }
    }
}

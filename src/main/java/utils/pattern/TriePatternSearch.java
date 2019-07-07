package utils.pattern;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://www.geeksforgeeks.org/pattern-searching-using-trie-suffixes/
 */
public class TriePatternSearch {
    private String text;
    private TrieNode root;

    public TriePatternSearch(String text) {
        this.text = text;

        root = new TrieNode();

        for (int i = 0; i < text.length(); i++) {
            root.insertSuffix(text.substring(i), i);
        }
    }

    public Collection<Integer> search(String ee) {
        List<Integer> matches = root.search(ee);
        return (matches==null)?null:matches.stream().map(i -> i-ee.length()).collect(Collectors.toList());
    }

    class TrieNode {
        private TrieNode[] next = new TrieNode[256];
        private List<Integer> position = new LinkedList<>();

        public List<Integer> getPosition() {
            return position;
        }

        public void insertSuffix(String substring, int i) {
            position.add(i);
            if (substring.length() > 0) {
                if (next[substring.charAt(0)] == null) {
                    next[substring.charAt(0)] = new TrieNode();
                }
                next[substring.charAt(0)].insertSuffix(substring.substring(1), i + 1);
            }
        }

        public List<Integer> search(String ee) {
            if (ee.length() == 0)
                return new LinkedList<>(getPosition());
            TrieNode jump = next[ee.charAt(0)];
            if(jump == null)
                return null;
            else
                return jump.search(ee.substring(1));
        }
    }
}

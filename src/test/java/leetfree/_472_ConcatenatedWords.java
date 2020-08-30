package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/*
Given a list of words (without duplicates), please write a program that returns all concatenated words in the given
list of words.
A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
Note:
The number of elements of the given array will not exceed 10,000
The length sum of elements in the given array will not exceed 600,000.
All the input string will only include lower case letters.
The returned elements order does not matter.
 */
public class _472_ConcatenatedWords {

    @Test
    public void given() {
        String[] input = new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> output = findAllConcatenatedWordsInADict(input);
        Assert.assertEquals(3, output.size());
        Assert.assertTrue(output.containsAll(List.of("catsdogcats", "dogcatsdog", "ratcatdogcat")));
    }

    @Test
    public void givenWithTrie() {
        String[] input = new String[]{"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> trieOutput = findAllConcatenatedWordsInADictWithTrie(input);
        Assert.assertEquals(3, trieOutput.size());
        Assert.assertTrue(trieOutput.containsAll(List.of("catsdogcats", "dogcatsdog", "ratcatdogcat")));
    }

    /**
     * Recursive solution
     * Time complexity O(N*logN) + O (N^2) (sorting plus matching)
     */
    public List<String> findAllConcatenatedWordsInADict(String[] input) {
        List<String> sorted = Arrays.stream(input).sorted((a, b) -> b.length() - a.length()).collect(Collectors.toList());
        List<String> output = new LinkedList<>();

        //last one can be skipped
        for (int i = 0; i < sorted.size() - 1; i++) {
            if (reduceByKnownWord(sorted.get(i), sorted.subList(i + 1, sorted.size())))
                output.add(sorted.get(i));
        }

        return output;
    }

    private boolean reduceByKnownWord(String word, List<String> possibilities) {
        if (word.length() == 0)
            return true;
        for (String tentative : possibilities) {
            if (word.startsWith(tentative)) {
                boolean test = reduceByKnownWord(word.substring(tentative.length()), possibilities);
                if (test)
                    return true;
            }
        }
        return false;
    }

    public List<String> findAllConcatenatedWordsInADictWithTrie(String[] input) {
        TrieNode root = new TrieNode();
        for (String word : input) {
            root.addPrefix(word);
        }

        List<String> output = new LinkedList<>();

        for (String word : input) {
            if (recursiveSearch(root, word, 0) > 1)
                output.add(word);
        }

        return output;
    }

    private int recursiveSearch(TrieNode root, String word, int start) {
        if (start >= word.length())
            return 0;
        int words = -1;
        TrieNode current = root;
        for (int i = start; i < word.length(); i++) {
            char c = word.charAt(i);
            if (current.sons[c - 'a'] == null) {
                break;
            } else {
                if (current.sons[c - 'a'].end) {
                    int result = recursiveSearch(root, word, i + 1);
                    if (result != -1) {
                        words = Math.max(words, result + 1);
                    }
                }
                current = current.sons[c - 'a'];
            }
        }
        return words;
    }

    private class TrieNode {
        TrieNode[] sons = new TrieNode['z' - 'a'];
        boolean end = false;

        public void addPrefix(String word) {
            if (word.length() == 0)
                return;
            char c = word.charAt(0);
            if (sons[c - 'a'] == null) {
                sons[c - 'a'] = new TrieNode();
            }
            sons[c - 'a'].addPrefix(word.substring(1));
            if (word.length() == 1)
                sons[c - 'a'].end = true;
        }
    }
}

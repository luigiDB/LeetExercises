package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;

/*
Implement the StreamChecker class as follows:
StreamChecker(words): Constructor, init the data structure with the given words.
query(letter): returns true if and only if for some k >= 1, the last k characters queried (in order from oldest to
newest, including this letter just queried) spell one of the words in the given list.
Example:
StreamChecker streamChecker = new StreamChecker(["cd","f","kl"]); // init the dictionary.
streamChecker.query('a');          // return false
streamChecker.query('b');          // return false
streamChecker.query('c');          // return false
streamChecker.query('d');          // return true, because 'cd' is in the wordlist
streamChecker.query('e');          // return false
streamChecker.query('f');          // return true, because 'f' is in the wordlist
streamChecker.query('g');          // return false
streamChecker.query('h');          // return false
streamChecker.query('i');          // return false
streamChecker.query('j');          // return false
streamChecker.query('k');          // return false
streamChecker.query('l');          // return true, because 'kl' is in the wordlist
Note:
1 <= words.length <= 2000
1 <= words[i].length <= 2000
Words will only consist of lowercase English letters.
Queries will only consist of lowercase English letters.
The number of queries is at most 40000.
 */
public class __1032_StreamOfCharacters {

    @Test
    public void given() {
        StreamChecker streamChecker = new StreamChecker(new String[]{"cd", "f", "kl"});
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('b'));
        Assert.assertFalse(streamChecker.query('c'));
        Assert.assertTrue(streamChecker.query('d'));
        Assert.assertFalse(streamChecker.query('e'));
        Assert.assertTrue(streamChecker.query('f'));
        Assert.assertFalse(streamChecker.query('g'));
        Assert.assertFalse(streamChecker.query('h'));
        Assert.assertFalse(streamChecker.query('i'));
        Assert.assertFalse(streamChecker.query('j'));
        Assert.assertFalse(streamChecker.query('k'));
        Assert.assertTrue(streamChecker.query('l'));
        Assert.assertFalse(streamChecker.query('c'));
        Assert.assertFalse(streamChecker.query('e'));
    }

    @Test
    public void testWithWordsWithCommonSubstrings() {
        StreamChecker streamChecker = new StreamChecker(new String[]{"ab", "abcb"});
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertTrue(streamChecker.query('b'));
        Assert.assertFalse(streamChecker.query('c'));
        Assert.assertTrue(streamChecker.query('b'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertTrue(streamChecker.query('b'));
    }

    @Test
    public void testHowToComeBack() {
        StreamChecker streamChecker = new StreamChecker(new String[]{"ab", "ba", "aaab", "abab", "baa"});
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertTrue(streamChecker.query('b'));

        streamChecker = new StreamChecker(new String[]{"aab", "aaac"});
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertFalse(streamChecker.query('a'));
        Assert.assertTrue(streamChecker.query('b'));
    }

    class StreamChecker {

        private final TrieNode root;
        private int trieDepth;
        private final LinkedList<Character> lastInputs;

        public StreamChecker(String[] words) {
            root = new TrieNode();
            trieDepth = Integer.MIN_VALUE;
            for (String word : words)
                trieDepth = Math.max(trieDepth, root.addWord(word, 0));
            lastInputs = new LinkedList<>();
        }

        public boolean query(char letter) {
            lastInputs.addLast(letter);
            while (lastInputs.size() > trieDepth)
                lastInputs.removeFirst();

            TrieNode support = root;
            for (int i = lastInputs.size() - 1; i >= 0; i--) {
                support = support.getNext(lastInputs.get(i));
                if (support == null)
                    return false;
                if (support.isEndOfTheWord())
                    return true;
            }
            return false;
        }

        private class TrieNode {

            private final TrieNode[] trieNodes;
            private boolean isEndOfTheWord;

            public TrieNode() {
                trieNodes = new TrieNode['z' - 'a'];
                isEndOfTheWord = false;
            }

            public int addWord(String word, int depth) {
                int lastChar = word.charAt(word.length() - 1) - 'a';
                if (trieNodes[lastChar] == null)
                    trieNodes[lastChar] = new TrieNode();
                TrieNode next = trieNodes[lastChar];
                if (word.length() == 1) {
                    //if this is the last letter we mark the node as word end
                    next.isEndOfTheWord = true;
                } else {
                    return next.addWord(word.substring(0, word.length() - 1), depth + 1);
                }
                return depth + 1;
            }

            public TrieNode getNext(char next) {
                return trieNodes[next - 'a'];
            }

            public boolean isEndOfTheWord() {
                return isEndOfTheWord;
            }
        }
    }
}

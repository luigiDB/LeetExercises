package leetfree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some
 characters of the given string. If there are more than one possible results, return the longest word with the smallest
  lexicographical order. If there is no possible result, return the empty string.
Example 1:
Input:
s = "abpcplea", d = ["ale","apple","monkey","plea"]
Output:
"apple"
Example 2:
Input:
s = "abpcplea", d = ["a","b","c"]
Output:
"a"
Note:
All the strings in the input will only contain lower-case letters.
The size of the dictionary won't exceed 1,000.
The length of all the strings in the input won't exceed 1,000.
 */
public class __524_LongestWordInDictionaryThroughDeleting {
    /**
     * The following solution is the most obvious one (order the dictionary and search if substring)
     * Another solution to read s only one time is to create a trie of the dictionary and recursively search for all
     * the substring in one pass. In pseudocode:
     * void foo(TrieNode node, String s, String matchSoFar) {
     *     List<Character> cl = node.nextsList();
     *     if(cl.isEmpty())
     *          match found check matchSoFar is best candidate (on a global variable)
     *     for( int i =0; i < s.length, i++ ) {
     *         if(cl.contains(s[i]))
     *              foo(node.get(s[i]), s.substring(i +1), matchSoFar + s[i]);
     *     }
     * }
     */
    public String findLongestWord(String s, List<String> d) {
        Collections.sort(d, new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s2.length() != s1.length() ? s2.length() - s1.length() : s1.compareTo(s2);
            }
        });
        for (String str : d) {
            if (isSubsequence(str, s))
                return str;
        }
        return "";
    }

    private boolean isSubsequence(String x, String y) {
        int j = 0;
        for (int i = 0; i < y.length() && j < x.length(); i++)
            if (x.charAt(j) == y.charAt(i))
                j++;
        return j == x.length();
    }
}

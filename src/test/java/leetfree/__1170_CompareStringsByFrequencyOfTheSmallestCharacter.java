package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Let's define a function f(s) over a non-empty string s, which calculates the frequency of the smallest character in s. For example, if s = "dcce" then f(s) = 2 because the smallest character is "c" and its frequency is 2.
Now, given string arrays queries and words, return an integer array answer, where each answer[i] is the number of words such that f(queries[i]) < f(W), where W is a word in words.
Example 1:
Input: queries = ["cbd"], words = ["zaaaz"]
Output: [1]
Explanation: On the first query we have f("cbd") = 1, f("zaaaz") = 3 so f("cbd") < f("zaaaz").
Example 2:
Input: queries = ["bbb","cc"], words = ["a","aa","aaa","aaaa"]
Output: [1,2]
Explanation: On the first query only f("bbb") < f("aaaa"). On the second query both f("aaa") and f("aaaa") are both > f("cc").
Constraints:
1 <= queries.length <= 2000
1 <= words.length <= 2000
1 <= queries[i].length, words[i].length <= 10
queries[i][j], words[i][j] are English lowercase letters.
 */
public class __1170_CompareStringsByFrequencyOfTheSmallestCharacter {

    @Test
    public void test1() {
        Assert.assertArrayEquals(new int[]{1}, numSmallerByFrequency(
                new String[]{"cbd"},
                new String[]{"zaaaz"}));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(new int[]{1, 2}, numSmallerByFrequency(
                new String[]{"bbb", "cc"},
                new String[]{"a", "aa", "aaa", "aaaa"}));
    }

    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        List<Long> collect = Arrays.stream(queries)
                .map(this::minCharFrequency)
                .map(minFreq -> {
                    return Arrays.stream(words)
                            .map(this::minCharFrequency)
                            .filter(w -> minFreq < w)
                            .count();
                })
                .collect(Collectors.toList());
        int[] ints = new int[collect.size()];
        for (int i = 0; i < collect.size(); i++) {
            ints[i] = collect.get(i).intValue();
        }
        return ints;
    }

    private int minCharFrequency(String s) {
        int[] frequencies = new int['z' - 'a' + 1];
        for (int i = 0; i < s.length(); i++) {
            frequencies[s.charAt(i) - 'a']++;
        }
        for (int freq : frequencies) {
            if (freq != 0)
                return freq;
        }
        return 0;
    }
}

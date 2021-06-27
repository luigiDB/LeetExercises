package leetfree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Given a string s and an integer k, return the length of the longest substring of s that contains at most k
 * distinct characters.
 */
public class _340_LongestSubstringWithAtMostKDistinctCharacters {

    @Test
    public void a() {
        assertEquals(3, lengthOfLongestSubstringKDistinct("eceba", 2));
        assertEquals(2, lengthOfLongestSubstringKDistinct("aa", 1));
    }

    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> window = new HashMap<>();

        int maxWindow = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            char current = s.charAt(end);
            window.compute(current, (c, i) -> i == null ? 1 : i + 1);
            while (window.size() > k) {
                char startChar = s.charAt(start);
                if (window.get(startChar) == 1)
                    window.remove(startChar);
                else
                    window.compute(startChar, (c, i) -> i - 1);
                start++;
            }
            end++;
            maxWindow = Math.max(maxWindow, end - start);
        }
        return maxWindow;
    }
}

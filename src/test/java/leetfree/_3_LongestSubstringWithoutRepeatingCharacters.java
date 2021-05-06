package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
Given a string s, find the length of the longest substring without repeating characters.
Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
Example 4:
Input: s = ""
Output: 0
Constraints:
0 <= s.length <= 5 * 104
s consists of English letters, digits, symbols and spaces.
 */
public class _3_LongestSubstringWithoutRepeatingCharacters {
    @Test
    public void given1() {
        Assert.assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0)
            return 0;
        Map<Character, Integer> view = new HashMap<>();
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (view.containsKey(c)) {
                int newStart = view.get(c);
                //Delete everything between startWindow and newStart
                for (int j = i - view.size(); j <= newStart; j++) {
                    view.remove(s.charAt(j));
                }
            }
            view.put(c, i);
            maxLen = Math.max(maxLen, view.size());
        }
        return maxLen;
    }
}

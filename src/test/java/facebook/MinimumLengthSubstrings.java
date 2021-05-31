package facebook;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * You are given two strings s and t. You can select any substring of string s and rearrange the characters of the
 * selected substring. Determine the minimum length of the substring of s such that string t is a substring of the
 * selected substring.
 */
public class MinimumLengthSubstrings {

    @Test
    public void a() {
        assertEquals(2, minLengthSubstring("cdaefgba", "ab"));
    }

    int minLengthSubstring(String s, String t) {
        // Write your code here
        Map<Character, Integer> tMap = new HashMap<>();

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }


        int minWin = Integer.MAX_VALUE;
        int left = 0, right = 0;
        Map<Character, Integer> sMap = new HashMap<>();
        while (right < s.length()) {

            char c = s.charAt(right);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);

            if (isSub(sMap, tMap)) {
                minWin = Math.min(minWin, count(sMap));

                while (left < right && isSub(sMap, tMap)) {
                    minWin = Math.min(minWin, count(sMap));
                    char d = s.charAt(left);
                    sMap.put(d, sMap.get(d)-1);
                    left++;
                }

            }
            right++;
        }

        return minWin == Integer.MAX_VALUE ? -1 : minWin;
    }

    private boolean isSub(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        for(Map.Entry<Character, Integer> entry: tMap.entrySet()) {
            if(!sMap.containsKey(entry.getKey()))
                return false;
            if(!sMap.get(entry.getKey()).equals(entry.getValue()))
                return false;
        }
        return true;
    }

    private int count(Map<Character, Integer> m) {
        int sum = 0;
        for (int i : m.values())
            sum += i;
        return sum;
    }
}

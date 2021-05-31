package facebook;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Given two strings s and t of length N, find the maximum number of possible matching pairs in strings s and t after
 * swapping exactly two characters within s.
 * A swap is switching s[i] and s[j], where s[i] and s[j] denotes the character that is present at the ith and jth
 * index of s, respectively. The matching pairs of the two strings are defined as the number of indices for which
 * s[i] and t[i] are equal.
 * Note: This means you must swap two characters at different indices.
 */
public class MatchingPairs {

    @Test
    public void name() {
        String s_1 = "abcde";
        String t_1 = "adcbe";
        assertEquals(5, matchingPairs(s_1, t_1));

        String s_2 = "abcd";
        String t_2 = "abcd";
        assertEquals(2, matchingPairs(s_2, t_2));
    }

    int matchingPairs(String s, String t) {
        // Write your code here
        int matching = 0;
        Set<Character> a = new HashSet<>();
        Set<Character> b = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i))
                matching++;
            else
                a.add(s.charAt(i));
            b.add(t.charAt(i));
        }

        if (matching == s.length())
            return matching - 2;
        a.retainAll(b);
        if (b.size() == 1 && a.isEmpty())
            return matching - 1;
        if (a.size() == 1)
            return matching + 1;
        else if (a.size() == 0)
            return matching;
        else
            return matching + 2;
    }
}

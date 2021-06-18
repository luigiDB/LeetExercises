package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the
 * mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 */
public class _91_DecodeWays {

    @Test
    public void a() {
        // "AB" (1 2) or "L" (12).
        assertEquals(2, numDecodings("12"));
    }

    @Test
    public void b() {
        // "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6)
        assertEquals(3, numDecodings("226"));
    }

    @Test
    public void c() {
        // "JF" (10 6)
        assertEquals(1, numDecodings("106"));
    }

    public int numDecodings(String s) {
        return recursiveSearch(s, 0);
    }

    private int recursiveSearch(String s, int index) {
        if (index == s.length() || index == s.length() - 1)
            return 1;

        int counter = 0;
        if (s.length() >= index + 1 && s.charAt(index + 1) != '0')
            counter += recursiveSearch(s, index + 1);

        if (s.length() >= index + 2) {
            int twoCiphers = Integer.parseInt(s.substring(index, index + 2));
            if (twoCiphers < 27)
                counter += recursiveSearch(s, index + 2);
        }

        return counter;
    }
}

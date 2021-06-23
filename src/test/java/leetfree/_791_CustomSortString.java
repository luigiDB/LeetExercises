package leetfree;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;

/**
 * order and str are strings composed of lowercase letters. In order, no letter occurs more than once.
 * order was sorted in some custom order previously. We want to permute the characters of str so that they match the
 * order that order was sorted. More specifically, if x occurs before y in order, then x should occur before y in the
 * returned string.
 * Return any permutation of str (as a string) that satisfies this property.
 */
public class _791_CustomSortString {

    @Test
    public void a() {
        assertEquals("cbad", customSortString("cba", "abcd"));
        assertEquals("cbbaad", customSortString("cba", "abcdab"));
    }

    @Test
    public void aWithCountingSort() {
        assertEquals("cbad", countingSortSolution("cba", "abcd"));
        assertEquals("cbbaad", countingSortSolution("cba", "abcdab"));
    }

    public String customSortString(String order, String str) {
        Character[] charObjectArray = new Character[str.length()];
        for (int i = 0; i < str.length(); i++) {
            charObjectArray[i] = str.charAt(i);
        }

        int[] prio = new int[26];
        Arrays.fill(prio, Integer.MAX_VALUE);
        for (int i = 0; i < order.length(); i++) {
            prio[order.charAt(i) - 'a'] = i;
        }
        Arrays.sort(charObjectArray, Comparator.comparingInt(a -> prio[a - 'a']));

        StringBuilder sb = new StringBuilder();
        for (Character c: charObjectArray)
            sb.append(c);
        return sb.toString();
    }


    public String countingSortSolution(String order, String str) {
        int[] count = new int[26];
        for (char c: str.toCharArray())
            count[c - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (char c: order.toCharArray()) {
            for (int i = 0; i < count[c - 'a']; ++i)
                sb.append(c);
            count[c - 'a'] = 0;
        }

        for (char c = 'a'; c <= 'z'; ++c)
            for (int i = 0; i < count[c - 'a']; ++i)
                sb.append(c);

        return sb.toString();
    }
}

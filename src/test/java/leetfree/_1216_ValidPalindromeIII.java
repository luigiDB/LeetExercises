package leetfree;

import org.apache.commons.lang3.tuple.Triple;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given a string s and an integer k, return true if s is a k-palindrome.
 * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
 */
public class _1216_ValidPalindromeIII {

    @Test
    public void a() {
        assertTrue(isValidPalindrome("abcdeca", 2));
    }

    @Test
    public void b() {
        assertTrue(isValidPalindrome("abbababa", 1));
    }

    @Test
    public void c() {
        assertFalse(isValidPalindrome("cccabbcccbdcaaabbcdbddccaddccbabbabdbaaabbbbdcabacccbbdbbbdbdcdd", 1));
    }

    /**
     * Too slow
     */
    @Test
    public void d() {
        assertTrue(isValidPalindrome("fcgihcgeadfehgiabegbiahbeadbiafgcfchbcacedbificicihibaeehbffeidiaiighceegbfdggggcfaiibefbgeegbcgeadcfdfegfghebcfceiabiagehhibiheddbcgdebdcfegaiahibcfhheggbheebfdahgcfcahafecfehgcgdabbghddeadecidicchfgicbdbecibddfcgbiadiffcifiggigdeedbiiihfgehhdegcaffaggiidiifgfigfiaiicadceefbhicfhbcachacaeiefdcchegfbifhaeafdehicfgbecahidgdagigbhiffhcccdhfdbd", 216));
    }

    public boolean isValidPalindrome(String s, int k) {
        memory.clear();
        return searchKPalindrome(s, k, 0, 0, s.length() - 1);
    }

    Map<Triple<Integer, Integer, Integer>, Boolean> memory = new HashMap<>();

    private boolean searchKPalindrome(String s, int k, int removed, int left, int right) {

        Triple<Integer, Integer, Integer> memoryIndex = Triple.of(removed, left, right);
        if (memory.containsKey(memoryIndex))
            return memory.get(memoryIndex);

        if (removed > k)
            return false;

        while (s.charAt(left) == s.charAt(right) && left <= right) {
            left++;
            right--;
        }

        boolean result = false;
        if (left >= right)
            result = true;
        else {
            result = searchKPalindrome(s, k, removed + 1, left + 1, right)
                    || searchKPalindrome(s, k, removed + 1, left, right - 1);
        }

        memory.put(memoryIndex, result);
        return result;
    }

}

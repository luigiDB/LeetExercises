package leetfree;

import org.junit.Test;

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
    public void d() {
        assertFalse(isValidPalindrome("fcgihcgeadfehgiabegbiahbeadbiafgcfchbcacedbificicihibaeehbffeidiaiighceegbfdggggcfaiibefbgeegbcgeadcfdfegfghebcfceiabiagehhibiheddbcgdebdcfegaiahibcfhheggbheebfdahgcfcahafecfehgcgdabbghddeadecidicchfgicbdbecibddfcgbiadiffcifiggigdeedbiiihfgehhdegcaffaggiidiifgfigfiaiicadceefbhicfhbcachacaeiefdcchegfbifhaeafdehicfgbecahidgdagigbhiffhcccdhfdbd", 216));
    }

    public boolean isValidPalindrome(String s, int k) {
        return searchKPalindrome(s, k, 0, 0, s.length() - 1);
    }

    private boolean searchKPalindrome(String s, int k, int removed, int left, int right) {

        if (removed > k)
            return false;

        while (s.charAt(left) == s.charAt(right) && left <= right) {
            left++;
            right--;
        }

        if (left >= right)
            return true;
        else {
            return searchKPalindrome(s, k, removed + 1, left + 1, right)
                    || searchKPalindrome(s, k, removed + 1, left, right - 1);
        }
    }
}

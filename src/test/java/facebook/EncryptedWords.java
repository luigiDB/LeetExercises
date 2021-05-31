package facebook;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You've devised a simple encryption method for alphabetic strings that shuffles the characters in such a way that the
 * resulting string is hard to quickly read, but is easy to convert back into the original string.
 * When you encrypt a string S, you start with an initially-empty resulting string R and append characters to it as
 * follows:
 * Append the middle character of S (if S has even length, then we define the middle character as the left-most of the
 * two central characters)
 * Append the encrypted version of the substring of S that's to the left of the middle character (if non-empty)
 * Append the encrypted version of the substring of S that's to the right of the middle character (if non-empty)
 * For example, to encrypt the string "abc", we first take "b", and then append the encrypted version of "a" (which is
 * just "a") and the encrypted version of "c" (which is just "c") to get "bac".
 * If we encrypt "abcxcba" we'll get "xbacbca". That is, we take "x" and then append the encrypted version "abc" and
 * then append the encrypted version of "cba".
 */
public class EncryptedWords {

    @Test
    public void a() {
        assertEquals("bac", findEncryptedWord("abc"));
        assertEquals("bacd", findEncryptedWord("abcd"));
    }

    // Add any helper functions you may need here
    private void sub(String s, int left, int right, StringBuilder sb) {
        if(left <= right) {
            int mid = (left + right)/2;
            sb.append(s.charAt(mid));
            sub(s, left, mid -1, sb);
            sub(s, mid + 1, right, sb);
        }
    }

    String findEncryptedWord(String s) {
        // Write your code here
        StringBuilder sb = new StringBuilder();
        sub(s, 0, s.length()-1, sb);
        return sb.toString();
    }

}

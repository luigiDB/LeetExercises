package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace
 character.
Example 1:
Input: S = "ab#c", T = "ad#c"
Output: true
Explanation: Both S and T become "ac".
Example 2:
Input: S = "ab##", T = "c#d#"
Output: true
Explanation: Both S and T become "".
Example 3:
Input: S = "a##c", T = "#a#c"
Output: true
Explanation: Both S and T become "c".
Example 4:
Input: S = "a#c", T = "b"
Output: false
Explanation: S becomes "c" while T becomes "b".
Note:
1 <= S.length <= 200
1 <= T.length <= 200
S and T only contain lowercase letters and '#' characters.
Follow up:
Can you solve it in O(N) time and O(1) space?
 */
public class __844_BackspaceStringCompare {

    @Test
    public void trueTests() {
        Assert.assertTrue(backspaceCompare("", ""));
        Assert.assertTrue(backspaceCompare("a", "a"));
        Assert.assertTrue(backspaceCompare("ab#c", "ad#c"));
        Assert.assertTrue(backspaceCompare("ab##", "a#d#"));
        Assert.assertTrue(backspaceCompare("a##c", "#a#c"));
    }

    @Test
    public void falseTests() {
        Assert.assertFalse(backspaceCompare("a", "b"));
        Assert.assertFalse(backspaceCompare("a#c", "b"));
    }

    public boolean backspaceCompare(String S, String T) {
        int i = S.length() - 1;
        int j = T.length() - 1;

        while (i >= 0 || j >= 0) {
            if (i >= 0 && S.charAt(i) == '#') {
                int countBackspace = 0;
                while (S.charAt(i - countBackspace) == '#')
                    countBackspace++;
                i -= countBackspace * 2;
            }
            if (j >= 0 && T.charAt(j) == '#') {
                int countBackspace = 0;
                while (T.charAt(j - countBackspace) == '#')
                    countBackspace++;
                j -= countBackspace * 2;
            }
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j))
                return false;
            i--;
            j--;
        }
        return true;
    }
}

package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If
no such solution, return -1.
For example, with A = "abcd" and B = "cdabcdab".
Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A
repeated two times ("abcdabcd").
Note:
The length of A and B will be between 1 and 10000.
 */
public class __686_RepeatedStringMatch {

    @Test
    public void given() {
        Assert.assertEquals(3, repeatedStringMatch("abcd", "cdabcdab"));
    }

    @Test
    public void firstStringWithMultipleOccurrencesOfTheFirstCharOfTheSecondString() {
        Assert.assertEquals(3, repeatedStringMatch("abac", "acabacaba"));
    }

    @Test
    public void naiveTets() {
        Assert.assertEquals(1, repeatedStringMatch("abcd", "abcd"));
    }

    public int repeatedStringMatch(String A, String B) {
        int bIndex = 0;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) == B.charAt(bIndex)) {
                int j = i;
                while (j < A.length() && A.charAt(j) == B.charAt(bIndex)) {
                    j++;
                    bIndex++;
                }
                if (j == A.length())
                    break;
                else
                    bIndex = 0;
            }
        }

        return (((B.length() - (bIndex + 1)) + A.length() - 1) / A.length()) + 1;
    }
}

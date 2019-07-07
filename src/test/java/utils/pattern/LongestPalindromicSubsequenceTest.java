package utils.pattern;

import org.junit.Assert;
import org.junit.Test;

public class LongestPalindromicSubsequenceTest {

    @Test
    public void trivialCase() {
        String lps = LongestPalindromicSubsequence.find("abaaba");
        Assert.assertEquals("abaaba", lps);
    }

    @Test
    public void normalCase() {
        String lps = LongestPalindromicSubsequence.find("casa");
        Assert.assertEquals("asa", lps);
    }

    @Test
    public void multiplePossibilities() {
        String lps = LongestPalindromicSubsequence.find("babcbabcbaccba");
        Assert.assertEquals("abcbabcba", lps);
    }
}
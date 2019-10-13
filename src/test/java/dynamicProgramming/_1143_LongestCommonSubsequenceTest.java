package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _1143_LongestCommonSubsequenceTest {

    private _1143_LongestCommonSubsequence longestCommonSubsequence;

    @Before
    public void setUp() throws Exception {
        longestCommonSubsequence = new _1143_LongestCommonSubsequence();
    }

    @Test
    public void given1() {
        Assert.assertEquals(3, longestCommonSubsequence.LCS("abcde", "ace"));
    }

    @Test
    public void given2() {
        Assert.assertEquals(3, longestCommonSubsequence.LCS("abc", "abc"));
    }

    @Test
    public void given3() {
        Assert.assertEquals(0, longestCommonSubsequence.LCS("abc", "def"));
    }
}
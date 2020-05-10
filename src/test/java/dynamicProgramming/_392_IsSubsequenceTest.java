package dynamicProgramming;

import junit.framework.TestCase;
import org.junit.Assert;

public class _392_IsSubsequenceTest extends TestCase {

    private _392_IsSubsequence subsequence;

    @Override
    public void setUp() throws Exception {
        subsequence = new _392_IsSubsequence();
    }

    public void testPositive() {
        Assert.assertTrue(subsequence.isSubsequence("abc", "ahbgdc"));
        Assert.assertTrue(subsequence.isSubsequence("abc", "ahbgcd"));
    }

    public void testNegative() {
        Assert.assertFalse(subsequence.isSubsequence("axc", "ahbgdc"));
    }
}
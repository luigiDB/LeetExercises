package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _410_SplitArrayLargestSumTest {

    private _410_SplitArrayLargestSum splitArrayLargestSum;

    @Before
    public void setUp() throws Exception {
        splitArrayLargestSum = new _410_SplitArrayLargestSum();
    }

    @Test
    public void test() {
        Assert.assertEquals(14, splitArrayLargestSum.splitArray(new int[]{7, 2, 5, 10, 8}, 3));
    }
}
package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _931_MinimumFallingPathSumTest {

    private _931_MinimumFallingPathSum minimumFallingPathSum;

    @Before
    public void setUp() throws Exception {
        minimumFallingPathSum = new _931_MinimumFallingPathSum();
    }

    @Test
    public void given1() {
        int[][] input = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        Assert.assertEquals(12, minimumFallingPathSum.minPath(input));
    }

}
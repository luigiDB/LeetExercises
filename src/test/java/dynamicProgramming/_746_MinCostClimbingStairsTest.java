package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _746_MinCostClimbingStairsTest {

    private _746_MinCostClimbingStairs stairs;

    @Before
    public void setUp() throws Exception {
        stairs = new _746_MinCostClimbingStairs();
    }

    @Test
    public void given1() {
        Assert.assertEquals(15, stairs.minCost(new int[]{10, 15, 20}));
    }

    @Test
    public void given2() {
        Assert.assertEquals(6, stairs.minCost(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
    }

    @Test
    public void given3() {
        Assert.assertEquals(2, stairs.minCost(new int[]{0, 2, 2, 1}));
    }
}
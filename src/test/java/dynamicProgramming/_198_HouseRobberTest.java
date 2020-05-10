package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class _198_HouseRobberTest {

    private _198_HouseRobber houseRobber;

    @Before
    public void setUp() throws Exception {
        houseRobber = new _198_HouseRobber();
    }

    @Test
    public void testGivenCases() {
        Assert.assertEquals(4, houseRobber.bestBounty(new int[]{1, 2, 3, 1}));
        Assert.assertEquals(12, houseRobber.bestBounty(new int[]{2, 7, 9, 3, 1}));
    }
}
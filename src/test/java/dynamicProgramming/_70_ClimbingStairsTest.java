package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class _70_ClimbingStairsTest {

    private _70_ClimbingStairs stairsClimber;

    @Before
    public void setUp() throws Exception {
        stairsClimber = new _70_ClimbingStairs();
    }

    @Test
    public void testIterativeSolution() {
        Assert.assertEquals(2, stairsClimber.iterativeCount(2));
        Assert.assertEquals(3, stairsClimber.iterativeCount(3));
        Assert.assertEquals(8, stairsClimber.iterativeCount(5));
    }

    @Test
    public void testRecursiveSolution() {
        Assert.assertEquals(2, stairsClimber.recursiveCount(2));
        Assert.assertEquals(3, stairsClimber.recursiveCount(3));
        Assert.assertEquals(8, stairsClimber.recursiveCount(5));
    }
}
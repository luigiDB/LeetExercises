package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _256_PaintHouseTest {

    private _256_PaintHouse paintHouse;

    @Before
    public void setUp() throws Exception {
        paintHouse = new _256_PaintHouse();
    }

    @Test
    public void givenTest() {
        Assert.assertEquals(10, paintHouse.minimumCost(new int[][]{{17, 2, 17}, {16, 16, 5}, {14, 3, 19}}));
    }
}
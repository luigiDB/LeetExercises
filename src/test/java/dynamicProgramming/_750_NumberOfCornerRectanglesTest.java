package dynamicProgramming;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _750_NumberOfCornerRectanglesTest {

    private _750_NumberOfCornerRectangles countRectangles;

    @Before
    public void setUp() throws Exception {
        countRectangles = new _750_NumberOfCornerRectangles();
    }

    @Test
    public void baseTest() {
        int[][] input = new int[][]{
                {1, 1},
                {1, 1}
        };
        Assert.assertEquals(1, countRectangles.countCornerRectangles(input));
    }

    @Test
    public void testGiven() {
        int[][] input = new int[][]{
                {1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1},
        };
        Assert.assertEquals(1, countRectangles.countCornerRectangles(input));
    }

    @Test
    public void testMultipleOverImposedRectangles() {
        int[][] input = new int[][]{
                {1, 0, 1},
                {1, 0, 1},
                {1, 0, 1},
        };
        Assert.assertEquals(3, countRectangles.countCornerRectangles(input));
    }

    @Test
    public void testFullMatrixOf1() {
        int[][] input = new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1},
        };
        Assert.assertEquals(9, countRectangles.countCornerRectangles(input));
    }
}
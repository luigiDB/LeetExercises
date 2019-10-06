package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

public class _85_MaximalRectangleTest {

    @Test
    public void given() {
        int[][] input = new int[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        };

        Assert.assertEquals(6, _85_MaximalRectangle.maxRectangle(input));
    }
}
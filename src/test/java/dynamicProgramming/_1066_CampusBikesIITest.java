package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _1066_CampusBikesIITest {

    private _1066_CampusBikesII campusBikesII;

    @Before
    public void setUp() throws Exception {
        campusBikesII = new _1066_CampusBikesII();
    }

    @Test
    public void assignBikes() {
        int[][] workers = new int[][]{{0, 0}, {2, 1}};
        int[][] bikes = new int[][]{{1, 2}, {3, 3}};
        Assert.assertEquals(6, campusBikesII.assignBikes(workers, bikes));
    }
}
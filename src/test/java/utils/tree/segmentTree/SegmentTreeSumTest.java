package utils.tree.segmentTree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SegmentTreeSumTest {

    private SegmentTreeSum tree;
    private int[] freq;

    @Before
    public void setUp() {
        freq = new int[]{2, 1, 1, 3, 2};
        tree = new SegmentTreeSum(freq);
    }

    @Test
    public void queryOverAllLegth() {
        Assert.assertEquals(9, tree.query(0, freq.length - 1));
    }

    @Test
    public void queryOverSegmentsLegth() {
        Assert.assertEquals(7, tree.query(0, 3));
        Assert.assertEquals(6, tree.query(2, 4));
        Assert.assertEquals(2, tree.query(1, 2));
    }

    @Test
    public void testUpdate() {
        tree.update(1, 10);
        Assert.assertEquals(16, tree.query(0, 3));
        Assert.assertEquals(10, tree.query(1, 1));
        tree.update(2, 20);
        Assert.assertEquals(20, tree.query(2, 2));
        Assert.assertEquals(37, tree.query(0, freq.length - 1));
    }

}
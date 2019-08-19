package utils.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SegmentTreeTest {

    private SegmentTree tree;
    private int[] freq;

    @Before
    public void setUp() {
        freq = new int[]{2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        tree = new SegmentTree(freq);
    }

    @Test
    public void queryOverAllLegth() {
        Assert.assertEquals(9, tree.query(0, freq.length - 1));
    }

    @Test
    public void queryOverSegmentsLegth() {
        Assert.assertEquals(3, tree.query(0, 3));
        Assert.assertEquals(3, tree.query(1, 4));
        Assert.assertEquals(7, tree.query(3, 9));
    }

    @Test
    public void testUpdate() {
        tree.update(1, 10);
        Assert.assertEquals(10, tree.query(0, 3));
        Assert.assertEquals(10, tree.query(1, 1));
        tree.update(2, 20);
        Assert.assertEquals(20, tree.query(2, 2));
        Assert.assertEquals(20, tree.query(0, 3));
        Assert.assertEquals(20, tree.query(0, freq.length - 1));
    }
}
package utils.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryIndexedTreeTest {

    private BinaryIndexedTree tree;

    @Before
    public void setUp() {
        int freq[] = {2, 1, 1, 3, 2, 3, 4, 5, 6, 7, 8, 9};
        tree = new BinaryIndexedTree(freq);
    }

    @Test
    public void OverhallTest() {
        Assert.assertEquals(12, tree.getSum(0, 5));
        Assert.assertEquals(8, tree.getSum(3, 5));

        tree.update(2, 6);

        Assert.assertEquals(18, tree.getSum(0, 5));
        Assert.assertEquals(8, tree.getSum(3, 5));
    }

    @Test
    public void testNonStartingZeroRange() {
        Assert.assertEquals(8, tree.getSum(3, 5));
        Assert.assertEquals(9, tree.getSum(2, 5));
        Assert.assertEquals(12, tree.getSum(3, 6));
    }
}
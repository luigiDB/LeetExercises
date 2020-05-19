package utils.tree.segmentTree.genericImplementation;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GenericSegmentTreeTest extends TestCase {

    private class SumNode extends AbstractNode<Integer> {

        public SumNode() {
            setValue(0);
        }

        @Override
        public void init(SegmentNode<Integer> l, SegmentNode<Integer> r) {
            setValue(l.get() + r.get());
//            setValue(l.get() + r.get());
        }

        @Override
        public void merge(SegmentNode<Integer> node) {
            setValue(get() + node.get());
        }
    }

    private GenericSegmentTree<Integer> tree;
    private Integer[] freq;

    @Before
    public void setUp() {
        freq = new Integer[]{2, 1, 1, 3, 2};
        tree = new GenericSegmentTree<Integer>(freq, SumNode::new);
    }

    @Test
    public void testQueryOverAllLegth() {
        SegmentNode queryResult = tree.query(0, freq.length - 1);
        Assert.assertEquals(9, queryResult.get());
    }

    @Test
    public void testQueryOverSegmentsLegth() {
        Assert.assertEquals(7, tree.query(0, 3).get());
        Assert.assertEquals(6, tree.query(2, 4).get());
        Assert.assertEquals(2, tree.query(1, 2).get());
    }

    @Test
    public void testUpdate() {
        tree.update(1, 10);
        Assert.assertEquals(16, tree.query(0, 3).get());
        Assert.assertEquals(10, tree.query(1, 1).get());
        tree.update(2, 20);
        Assert.assertEquals(20, tree.query(2, 2).get());
        Assert.assertEquals(37, tree.query(0, freq.length - 1).get());
    }
}
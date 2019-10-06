package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class _173_BinarySearchTreeIteratorTest {

    @Test
    public void given() {
        _173_BinarySearchTreeIterator.Node root = new _173_BinarySearchTreeIterator.Node(7);
        root.left = new _173_BinarySearchTreeIterator.Node(3);
        root.right = new _173_BinarySearchTreeIterator.Node(15);
        root.right.left = new _173_BinarySearchTreeIterator.Node(9);
        root.right.right = new _173_BinarySearchTreeIterator.Node(20);


        _173_BinarySearchTreeIterator iterator = new _173_BinarySearchTreeIterator(root);
        Assert.assertEquals(3, iterator.next());    // return 3
        Assert.assertEquals(7, iterator.next());    // return 7
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(9, iterator.next());    // return 9
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(15, iterator.next());    // return 15
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(20, iterator.next());    // return 20
        Assert.assertFalse(iterator.hasNext()); // return false
    }

    @Test
    public void complex() {
        _173_BinarySearchTreeIterator.Node root = new _173_BinarySearchTreeIterator.Node(10);
        root.left = new _173_BinarySearchTreeIterator.Node(5);
        root.left.left = new _173_BinarySearchTreeIterator.Node(3);
        root.left.right = new _173_BinarySearchTreeIterator.Node(8);
        root.left.left.left = new _173_BinarySearchTreeIterator.Node(2);
        root.left.left.right = new _173_BinarySearchTreeIterator.Node(4);
        root.right = new _173_BinarySearchTreeIterator.Node(20);
        root.left.right.left = new _173_BinarySearchTreeIterator.Node(7);

        _173_BinarySearchTreeIterator iterator = new _173_BinarySearchTreeIterator(root);
        Assert.assertEquals(2, iterator.next());
        Assert.assertEquals(3, iterator.next());
        Assert.assertEquals(4, iterator.next());
        Assert.assertEquals(5, iterator.next());
        Assert.assertEquals(7, iterator.next());
        Assert.assertEquals(8, iterator.next());
        Assert.assertEquals(10, iterator.next());
        Assert.assertEquals(20, iterator.next());
    }

}
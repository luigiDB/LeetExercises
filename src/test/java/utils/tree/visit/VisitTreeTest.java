package utils.tree.visit;

import junit.framework.TestCase;
import org.junit.Assert;
import utils.tree.Node;

import java.util.Arrays;

public class VisitTreeTest extends TestCase {

    public void testBase() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        VisitTree<Integer> visitor = new VisitTree<Integer>(root);
        Assert.assertEquals(Arrays.asList(2, 1, 3), visitor.inOrder());
        Assert.assertEquals(Arrays.asList(2, 3, 1), visitor.postOrder());
        Assert.assertEquals(Arrays.asList(1, 2, 3), visitor.preOrder());
    }

    public void testMoreComplexExamples() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right = new Node<>(3);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);
        VisitTree<Integer> visitor = new VisitTree<Integer>(root);
        Assert.assertEquals(Arrays.asList(4, 2, 5, 1, 6, 3, 7), visitor.inOrder());
        Assert.assertEquals(Arrays.asList(4, 5, 2, 6, 7, 3, 1), visitor.postOrder());
        Assert.assertEquals(Arrays.asList(1, 2, 4, 5, 3, 6, 7), visitor.preOrder());
    }

    public void testLeftCurve() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.right = new Node<>(5);
        VisitTree<Integer> visitor = new VisitTree<Integer>(root);
        Assert.assertEquals(Arrays.asList(2, 5, 1), visitor.inOrder());
        Assert.assertEquals(Arrays.asList(5, 2, 1), visitor.postOrder());
        Assert.assertEquals(Arrays.asList(1, 2, 5), visitor.preOrder());
    }

    public void testRightCurve() {
        Node<Integer> root = new Node<>(1);
        root.right = new Node<>(3);
        root.right.left = new Node<>(6);
        VisitTree<Integer> visitor = new VisitTree<Integer>(root);
        Assert.assertEquals(Arrays.asList(1, 6, 3), visitor.inOrder());
        Assert.assertEquals(Arrays.asList(6, 3, 1), visitor.postOrder());
        Assert.assertEquals(Arrays.asList(1, 3, 6), visitor.preOrder());
    }
}
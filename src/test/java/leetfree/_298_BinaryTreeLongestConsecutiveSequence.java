package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 *     The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child
 *     connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 *
 *     For example,
 *        1
 *         \
 *          3
 *         / \
 *        2   4
 *             \
 *              5
 *     Longest consecutive sequence path is 3-4-5, so return 3.
 *        2
 *         \
 *          3
 *         /
 *        2
 *       /
 *      1
 *     Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class _298_BinaryTreeLongestConsecutiveSequence {

    @Test
    public void testBase() {
        Node root = new Node(1);
        Assert.assertEquals(1, maxConsecutiveSequence(root));
    }

    @Test
    public void testLinear() {
        Node root = new Node(1);
        root.right = new Node(2);
        Assert.assertEquals(2, maxConsecutiveSequence(root));
    }

    @Test
    public void testWrongLinear() {
        Node root = new Node(2);
        root.right = new Node(1);
        Assert.assertEquals(1, maxConsecutiveSequence(root));
    }

    @Test
    public void test1() {
        Node root = new Node(1);
        root.right = new Node(3);
        root.right.left = new Node(2);
        root.right.right = new Node(4);
        root.right.right.right = new Node(5);

        Assert.assertEquals(3, maxConsecutiveSequence(root));
    }

    @Test
    public void test2() {
        Node root = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(2);
        root.right.left.left = new Node(1);

        Assert.assertEquals(2, maxConsecutiveSequence(root));
    }

    private int maxConsecutiveSequence(Node root) {
        if (root.left == null && root.right == null)
            return 1;
        int leftRes = 0;
        int rightRes = 0;
        if (root.left != null) {
            int maxLeft = maxConsecutiveSequence(root.left);
            leftRes = root.left.data == root.data + 1 ? maxLeft + 1 : maxLeft;
        }
        if (root.right != null) {
            int maxRight = maxConsecutiveSequence(root.right);
            rightRes = root.right.data == root.data + 1 ? maxRight + 1 : maxRight;
        }
        return Math.max(leftRes, rightRes);
    }

    class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            this.data = data;
        }
    }
}

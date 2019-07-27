package leetfree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * <p>
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both
 * considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child
 * order, where not necessarily be parent-child order.
 * <p>
 * Example 1:
 * Input:
 * 1
 * / \
 * 2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 * Input:
 * 2
 * / \
 * 1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 */
public class _549_BinaryTreeLongestConsecutiveSequenceII {

    @Before
    public void setUp() throws Exception {
        maxLen = 0;
    }

    @Test
    public void test1() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        Assert.assertEquals(2, longestSequence(root));
    }

    @Test
    public void test2() {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(3);
        Assert.assertEquals(3, longestSequence(root));
    }

    private int maxLen = 0;

    private int longestSequence(Node root) {
        dfs(root);
        return maxLen;
    }

    private Pair dfs(Node root) {
        if (root == null)
            return new Pair();

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        int inc = 1;
        int dec = 1;
        if (root.left != null) {
            if (root.left.value < root.value)
                inc = Math.max(inc, left.incr + 1);
            if (root.left.value > root.value)
                dec = Math.max(dec, left.dec + 1);
        }
        if (root.right != null) {
            if (root.right.value < root.value)
                inc = Math.max(inc, right.incr + 1);
            if (root.right.value > root.value)
                dec = Math.max(dec, right.dec + 1);
        }

        maxLen = Math.max(maxLen, inc + dec - 1);
        return new Pair(inc, dec);
    }


    class Node {
        Node left;
        Node right;
        int value;

        public Node(int i) {
            value = i;
        }
    }

    class Pair {
        int incr;
        int dec;
        boolean isLeaf;

        public Pair(int inc, int dec) {
            this.incr = inc;
            this.dec = dec;
            isLeaf = false;
        }

        public Pair() {
            isLeaf =true;
        }
    }
}

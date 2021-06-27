package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 * Each root-to-leaf path in the tree represents a number.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a
 * 32-bit integer.
 * A leaf node is a node with no children.
 */
public class _129_SumRootToLeafNumbers {

    @Test
    public void a() {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2),
                new TreeNode(3)
        );
        assertEquals(25, sumNumbers(root));
    }

    @Test
    public void b() {
        TreeNode root = new TreeNode(
                4,
                new TreeNode(9,
                        new TreeNode(5),
                        new TreeNode(1)
                ),
                new TreeNode(0)
        );
        assertEquals(1026, sumNumbers(root));
    }

    public int sumNumbers(TreeNode root) {
        return searchLeavesWithPath(root, 0);
    }

    private int searchLeavesWithPath(TreeNode root, int number) {

        if (root == null)
            return 0;

        int numberSoFar = number * 10 + root.val;

        if (root.left == null & root.right == null) {
            return numberSoFar;
        }

        return searchLeavesWithPath(root.left, numberSoFar)
                + searchLeavesWithPath(root.right, numberSoFar);

    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

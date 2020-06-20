package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
For a binary tree T, we can define a flip operation as follows: choose any node, and swap the left and right child subtrees.
A binary tree X is flip equivalent to a binary tree Y if and only if we can make X equal to Y after some number of flip operations.
Write a function that determines whether two binary trees are flip equivalent.  The trees are given by root nodes root1 and root2.
Example 1:
Input: root1 = [1,2,3,4,5,6,null,null,null,7,8], root2 = [1,3,2,null,6,4,5,null,null,null,null,8,7]
Output: true
Explanation: We flipped at nodes with values 1, 3, and 5.
Note:
Each tree will have at most 100 nodes.
Each value in each tree will be a unique integer in the range [0, 99].
 */
public class _951_FlipEquivalentBinaryTrees {

    @Test
    public void verifyTrueOnSimpleCase() {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(3, null, null));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(3, null, null),
                new TreeNode(2, null, null));
        Assert.assertTrue(flipEquiv(root1, root2));
    }

    @Test
    public void verifyImpossibleCase() {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(3, null, null));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(2, null, null),
                new TreeNode(4, null, null));
        Assert.assertFalse(flipEquiv(root1, root2));
    }

    @Test
    public void verifyTrueOnEqualsTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5,
                                new TreeNode(7, null, null),
                                new TreeNode(8, null, null))),
                new TreeNode(3,
                        new TreeNode(6, null, null),
                        null));
        Assert.assertTrue(flipEquiv(root, root));
    }

    @Test
    public void givenTest() {
        TreeNode root1 = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5,
                                new TreeNode(7, null, null),
                                new TreeNode(8, null, null))),
                new TreeNode(3,
                        new TreeNode(6, null, null),
                        null));
        TreeNode root2 = new TreeNode(1,
                new TreeNode(3,
                        null,
                        new TreeNode(6, null, null)),
                new TreeNode(2,
                        new TreeNode(4, null, null),
                        new TreeNode(5,
                                new TreeNode(8, null, null),
                                new TreeNode(7, null, null))));
        Assert.assertTrue(flipEquiv(root1, root2));
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        return iterativeSearch(root1, root2);
    }

    private boolean iterativeSearch(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null)
            return true;

        if (root1 == null || root2 == null)
            return false;

        if(root1.val != root2.val)
            return false;

        if (iterativeSearch(root1.left, root2.left) && iterativeSearch(root1.right, root2.right)) {
            return true;
        }

        return iterativeSearch(root1.left, root2.right) && iterativeSearch(root1.right, root2.left);
    }


    private class TreeNode {
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

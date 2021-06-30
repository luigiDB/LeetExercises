package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given the root of a binary tree, the depth of each node is the shortest distance to the root.
 * Return the smallest subtree such that it contains all the deepest nodes in the original tree.
 * A node is called the deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is tree consisting of that node, plus the set of all descendants of that node.
 * Note: This question is the same as 1123: https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/
 */
public class _865_SmallestSubtreeWithAllTheDeepestNodes {

    private Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution();
    }

    @Test
    public void a() {
        TreeNode deepestSubtree = new TreeNode(2,
                new TreeNode(7),
                new TreeNode(4)
        );
        TreeNode root = new TreeNode(
                3,
                new TreeNode(5,
                        new TreeNode(6),
                        deepestSubtree
                ),
                new TreeNode(1,
                        new TreeNode(0),
                        new TreeNode(8)
                )
        );
        assertEquals(deepestSubtree, solution.subtreeWithAllDeepest(root));
    }

    @Test
    public void b() {
        TreeNode deepestSubtree = new TreeNode(2);
        TreeNode root = new TreeNode(
                0,
                new TreeNode(1,
                        null,
                        deepestSubtree
                ),
                new TreeNode(3)
        );
        assertEquals(deepestSubtree, solution.subtreeWithAllDeepest(root));
    }

    @Test
    public void c() {

        TreeNode root = new TreeNode(
                0,
                new TreeNode(1,
                        null,
                        new TreeNode(2)
                ),
                new TreeNode(3,
                        new TreeNode(4),
                        null
                )
        );
        assertEquals(root, solution.subtreeWithAllDeepest(root));
    }

    class Solution {
        Pair<TreeNode, Integer> candidate = null;

        public TreeNode subtreeWithAllDeepest(TreeNode root) {
            if (root == null)
                return null;

            candidate = Pair.of(root, 0);
            searchDeepestSubtree(root, 0);
            return candidate.getLeft();
        }

        /**
         * Recursively iterate the tree returning the depth of the deepest branch
         */
        private int searchDeepestSubtree(TreeNode root, int i) {
            if (root == null)
                return i - 1;

            int leftDepth = searchDeepestSubtree(root.left, i + 1);
            int rightDepth = searchDeepestSubtree(root.right, i + 1);

            //If both branches are equals and the depth is bigger or equals to the candidate means that we found a
            // new candidate
            if (leftDepth == rightDepth) {
                if (leftDepth >= candidate.getRight())
                    candidate = Pair.of(root, leftDepth);
            }

            return Math.max(leftDepth, rightDepth);
        }

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

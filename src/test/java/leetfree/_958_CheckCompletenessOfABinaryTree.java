package leetfree;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Given the root of a binary tree, determine if it is a complete binary tree.
 * In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last
 * level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
 */
public class _958_CheckCompletenessOfABinaryTree {

    @Test
    public void a() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        new TreeNode(6),
                        null
                )
        );
        assertTrue(isCompleteTree(root));
    }

    @Test
    public void b() {
        TreeNode root = new TreeNode(1,
                new TreeNode(2,
                        new TreeNode(4),
                        new TreeNode(5)
                ),
                new TreeNode(3,
                        null,
                        new TreeNode(6)
                )
        );
        assertFalse(isCompleteTree(root));
    }

    public boolean isCompleteTree(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();

            if(poll == null) {
                return queue.stream().noneMatch(Objects::nonNull);
            }

            queue.add(poll.left);
            queue.add(poll.right);
        }

        return true;
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

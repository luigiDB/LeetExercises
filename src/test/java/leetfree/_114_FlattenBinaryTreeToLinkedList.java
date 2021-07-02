package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the
 * list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class _114_FlattenBinaryTreeToLinkedList {


    @Test
    public void a() {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(2,
                        new TreeNode(3),
                        new TreeNode(4)
                ),
                new TreeNode(5,
                        null,
                        new TreeNode(6)
                )
        );
        System.out.println(root);
        flatten(root);
        System.out.println(root);
        assertEquals("1,2,3,4,5,6,,,,,,", root.toString());
    }

    public void flatten(TreeNode root) {
        recursiveFlat(root);
    }

    private TreeNode recursiveFlat(TreeNode root) {
        if (root == null)
            return root;

        TreeNode left = recursiveFlat(root.left);
        TreeNode right = recursiveFlat(root.right);

        root.left = null;
        root.right = left;
        TreeNode current = root.right;
        if (current != null) {
            while (current.right != null)
                current = current.right;
            current.right = right;
        } else {
            root.right = right;
        }


        return root;
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

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(val);
            sb.append(",");
            if (left != null) {
                sb.append(left);
                sb.append(",");
            }
            if (right != null) {
                sb.append(right);
                sb.append(",");
            }
            return sb.toString();
        }
    }
}

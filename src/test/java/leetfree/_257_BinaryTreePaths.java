package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Given the root of a binary tree, return all root-to-leaf paths in any order.
 * A leaf is a node with no children.
 */
public class _257_BinaryTreePaths {

    @Test
    public void a() {
        TreeNode root = new TreeNode(
                1,
                new TreeNode(
                        2,
                        null,
                        new TreeNode(5)
                ),
                new TreeNode(3)
        );
        Assert.assertThat(binaryTreePaths(root), containsInAnyOrder(
                "1->2->5",
                "1->3"
        ));
    }

    private List<String> leaves = new LinkedList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        List<Integer> steps = new LinkedList<>();
        leafSearch(root, steps);
        return leaves;
    }

    private void leafSearch(TreeNode root, List<Integer> steps) {
        if (root == null)
            return;

        steps.add(root.val);

        if (root.left == null && root.right == null)
            leaves.add(convert(steps));
        else {
            leafSearch(root.left, steps);
            leafSearch(root.right, steps);
        }

        steps.remove(steps.size() - 1);
    }

    private String convert(List<Integer> nodes) {
        StringBuilder sb = new StringBuilder();

        ListIterator<Integer> iterator = nodes.listIterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            if (iterator.hasNext())
                sb.append("->");
        }
        return sb.toString();
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

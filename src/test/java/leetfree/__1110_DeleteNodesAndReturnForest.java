package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;

/*
Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest.  You may return the result in any order.
Example 1:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Constraints:
The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */
public class __1110_DeleteNodesAndReturnForest {

    @Test
    public void givenTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        int[] delete = new int[]{3, 5};
        Assert.assertThat(delNodes(root, delete), containsInAnyOrder(
                new TreeNode(6),
                new TreeNode(7),
                new TreeNode(1)
        ));
    }

    @Test
    public void secondTest() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        int[] delete = new int[]{2, 3};
        Assert.assertThat(delNodes(root, delete), containsInAnyOrder(
                new TreeNode(1),
                new TreeNode(4)
        ));
    }

    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<TreeNode> res = new HashSet<>();
        Set<Integer> toBeDeleted = new HashSet<>();
        for (int x : to_delete)
            toBeDeleted.add(x);

        Queue<TreeNode> queue = new LinkedList<>();
        //split logic on root
        if (!toBeDeleted.contains(root.val)) {
            res.add(root);
        }
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null)
                continue;

            queue.add(poll.left);
            queue.add(poll.right);

            //cut branch if next is a banned value
            if (poll.left != null && toBeDeleted.contains(poll.left.val)) {
                poll.left = null;
            }

            if (poll.right != null && toBeDeleted.contains(poll.right.val)) {
                poll.right = null;
            }

            //add the two sub trees to solution
            if (toBeDeleted.contains(poll.val)) {
                res.add(poll.left);
                res.add(poll.right);
            }
        }

        res.remove(null);
        List<TreeNode> list = new ArrayList<>();
        list.addAll(res);
        return list;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TreeNode treeNode = (TreeNode) o;
            return val == treeNode.val;
        }

        @Override
        public int hashCode() {
            return Objects.hash(val);
        }
    }
}

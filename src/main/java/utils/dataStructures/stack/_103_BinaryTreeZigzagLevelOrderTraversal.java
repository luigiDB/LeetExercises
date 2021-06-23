package utils.dataStructures.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 * <p>
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 * return its zigzag level order traversal as:
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */
public class _103_BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 1_ Use a recursive function that keep in a map with key the level the displacement of each node
     * (left -> -1, right -> +1  WARNING possible clashes). If we traverse the tree using BFS always left before right
     * we know that we encounter nodes in left right order without evaluating displacement.
     * 2_ Traverse the tree level by level evaluating each time the next level from the level before. This can be done
     * iteratively. See the following solution
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        byte dir = -1;

        List<List<Integer>> solution = new ArrayList<>();

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            List<Integer> vals = new ArrayList<>();

            if (dir == -1) {
                for (int i = 0; i < queue.size(); i++) {
                    vals.add(queue.get(i).val);
                }
            } else {
                for (int i = queue.size() - 1; i >= 0; i--) {
                    vals.add(queue.get(i).val);
                }
            }

            dir *= -1;
            solution.add(vals);

            List<TreeNode> newQueue = new ArrayList<>();
            for (TreeNode node : queue) {
                if (node.left != null) {
                    newQueue.add(node.left);
                }

                if (node.right != null) {
                    newQueue.add(node.right);
                }
            }

            queue = newQueue;
        }

        return solution;
    }

    private class TreeNode {
        public Integer val;
        public TreeNode left;
        public TreeNode right;
    }
}

package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
Given the root of a binary tree, find the maximum average value of any subtree of that tree.
(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its
values, divided by the number of nodes.)
Example 1:
Input: [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
Note:
The number of nodes in the tree is between 1 and 5000.
Each node will have a value between 0 and 100000.
Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 */
public class _1120_MaximumAverageSubtree {

    @Test
    public void given() {
        TreeNode root = new TreeNode(5,
                new TreeNode(6),
                new TreeNode(1)
        );
        Assert.assertEquals(6.0, maximumAverageSubtree(root), 0.00001);
    }

    @Test
    public void testWithNonBanalTree() {
        TreeNode root = new TreeNode(1,
                new TreeNode(98,
                        new TreeNode(4),
                        new TreeNode(6)),
                new TreeNode(3,
                        new TreeNode(5),
                        null));
        Assert.assertEquals(36.0, maximumAverageSubtree(root), 0.00001);
    }

    public double maximumAverageSubtree(TreeNode root) {
        if (root == null)
            return 0;

        PriorityQueue<Double> averageSubTree = new PriorityQueue<>(Comparator.comparingDouble(Double::doubleValue).reversed());
        exploreTree(root, averageSubTree);
        return averageSubTree.remove();
    }

    private Pair<Integer, Integer> exploreTree(TreeNode node, PriorityQueue<Double> averageSubTree) {

        int nodeSum = node.val;
        int nodeCount = 1;

        if (node.left != null) {
            Pair<Integer, Integer> left = exploreTree(node.left, averageSubTree);
            nodeSum += left.getLeft();
            nodeCount += left.getRight();
        }

        if (node.right != null) {
            Pair<Integer, Integer> right = exploreTree(node.right, averageSubTree);
            nodeSum += right.getLeft();
            nodeCount += right.getRight();
        }

        averageSubTree.offer(((double) nodeSum) / nodeCount);

        return Pair.of(nodeSum, nodeCount);
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

package leetfree;

import utils.tree.visit.Tree;

import java.util.*;

/*
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?
*/
public class _272_ClosestBinarySearchTreeValueII {

    /**
     *  In practise we keep a max heap of the closest values this way the space overhead is just K and the time
     *  complexity is N since we simply read all the tree.
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

        Queue<TreeNode> bfsQueue = new LinkedList<>();
        while (!bfsQueue.isEmpty()) {
            TreeNode poll = bfsQueue.poll();
            if (maxHeap.size() < k)
                maxHeap.offer(poll.val);
            else {
                if (Math.abs(target - maxHeap.peek()) > Math.abs(target - poll.val)) {
                    maxHeap.poll();
                    maxHeap.offer(poll.val);
                }
            }
            bfsQueue.add(poll.left);
            bfsQueue.add(poll.right);
        }

        return new ArrayList<>(maxHeap);
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}

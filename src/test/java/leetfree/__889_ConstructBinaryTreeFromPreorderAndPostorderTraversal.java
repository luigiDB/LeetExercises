package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/*
Return any binary tree that matches the given preorder and postorder traversals.
Values in the traversals pre and post are distinct positive integers.
Example 1:
Input: pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output: [1,2,3,4,5,6,7]
Note:
1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
 */
public class __889_ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    @Test
    public void given() {
        TreeNode root = constructFromPrePost(new int[]{1, 2, 4, 5, 3, 6, 7}, new int[]{4, 5, 2, 6, 7, 3, 1});
        Assert.assertNotNull(root);
        Assert.assertEquals(1, root.val);
        Assert.assertEquals(2, root.left.val);
        Assert.assertEquals(3, root.right.val);
        Assert.assertEquals(4, root.left.left.val);
        Assert.assertEquals(5, root.left.right.val);
        Assert.assertEquals(6, root.right.left.val);
        Assert.assertEquals(7, root.right.right.val);
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(pre[0]);
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int i = find(pre, node.val);
            int j = find(post, node.val);
            if (i + 1 >= pre.length || j - 1 <= 0)
                continue;
            if (pre[i + 1] != post[j - 1]) {
                //two sons
                if (find(post, pre[i + 1]) < j && find(pre, post[j - 1]) > i) {
                    //To be a valid sons the canddidates must be
                    // on the right for pre and
                    // on the left for post
                    // with respect for the current i and j
                    TreeNode son = new TreeNode(pre[i + 1]);
                    node.left = son;
                    queue.offer(son);
                    son = new TreeNode(post[j - 1]);
                    node.right = son;
                    queue.offer(son);
                }
            } else {
                //only one son
                TreeNode son = new TreeNode(pre[i + 1]);
                node.left = son;
                queue.offer(son);
            }

        }

        return root;
    }

    private int find(int[] list, int elem) {
        //can be optimized by keeping a map value position for the two arrays
        int i = 0;
        for (; i < list.length; i++) {
            if (list[i] == elem)
                break;
        }
        return i;
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

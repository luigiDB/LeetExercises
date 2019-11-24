package leetfree;

import org.junit.Assert;
import org.junit.Test;

/*
Two players play a turn based game on a binary tree.  We are given the root of this binary tree, and the number of
nodes n in the tree.  n is odd, and each node has a distinct value from 1 to n.
Initially, the first player names a value x with 1 <= x <= n, and the second player names a value y with 1 <= y <= n
and y != x.  The first player colors the node with value x red, and the second player colors the node with value y blue.
Then, the players take turns starting with the first player.  In each turn, that player chooses a node of their color
(red if player 1, blue if player 2) and colors an uncolored neighbor of the chosen node (either the left child, right
child, or parent of the chosen node.)
If (and only if) a player cannot choose such a node in this way, they must pass their turn.  If both players pass
their turn, the game ends, and the winner is the player that colored more nodes.
You are the second player.  If it is possible to choose such a y to ensure you win the game, return true.  If it is
not possible, return false.
Example 1:
Input: root = [1,2,3,4,5,6,7,8,9,10,11], n = 11, x = 3
Output: true
Explanation: The second player can choose the node with value 2
Constraints:
root is the root of a binary tree with n nodes and distinct node values from 1 to n.
n is odd.
1 <= x <= n <= 100
 */
public class __1145_BinaryTreeColoringGame {
    /*
    Please note that two solution are presented depending on how strictly you interpret the request
     */
    /**
     * The blue guy would select either red's parent node, left node or right node in order to win. If he wins in any
     * of the above three condition then blue can beat the red.
     */
    @Test
    public void given() {
        Assert.assertTrue(btreeGameWinningMove(null, 11, 3));
        Assert.assertTrue(btreeGameWinningMove(null, 25, 3));

        Assert.assertFalse(btreeGameWinningMove(null, 11, 2));
    }

    /**
     * The exercise text is not very clear.
     * Strictly following the specification we work on a full tree without holes with all the nodes on the left of
     * the last level. This means that the size of  the left and right subtree can be evaluated without viewing the
     * tree.
     */
    public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
        int left = countSunNodes(n, x * 2);
        int right = countSunNodes(n, x * 2 + 1);
        int father = n - left - right - 1;
        if (father > (left + right) || left > (father + right) || right > (father + left))
            return true;
        return false;
    }

    private int countSunNodes(int treeSize, int num) {
        int left = num;
        int right = num;
        int count = 0;

        while (left <= treeSize) {
            count += (Math.min(right, treeSize) - left + 1);
            left *= 2;
            right = (right * 2) + 1;
        }

        return count;
    }

    /**
     * The more correct solution is to iterate the tree to found left and right subtree size as in following develop
     */
    class Solution {
        private boolean foundRed  = false;
        private boolean canWin = false;
        public boolean btreeGameWinningMove(TreeNode root, int n, int x) {
            btree(root, n, x);
            return canWin;
        }

        private int btree(TreeNode current, int n , int x){
            if(current == null || foundRed){
                return 0;
            }
            int left = btree(current.left, n, x);
            int right = btree(current.right, n, x);
            if(x == current.val){
                foundRed = true;
                int parents = n-(left + right + 1);
                if(parents > (left + right + 1) || right > (parents + left + 1) || left > (parents + right + 1))
                    canWin = true;
            }
            return left + right + 1;
        }
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

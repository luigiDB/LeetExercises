package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.contains;

/**
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * <p>
 * If two nodes are in the same row and column, the order should be from left to right.
 * <p>
 * Examples:
 * <p>
 * Given binary tree [3,9,20,null,null,15,7],
 * 3
 * /\
 * /  \
 * 9  20
 * /\
 * /  \
 * 15   7
 * return its vertical order traversal as:
 * [
 * [9],
 * [3,15],
 * [20],
 * [7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7],
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * return its vertical order traversal as:
 * [
 * [4],
 * [9],
 * [3,0,1],
 * [8],
 * [7]
 * ]
 * Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 * 3
 * /\
 * /  \
 * 9   8
 * /\  /\
 * /  \/  \
 * 4  01   7
 * /\
 * /  \
 * 5   2
 * return its vertical order traversal as:
 * [
 * [4],
 * [9,5],
 * [3,0,1],
 * [8,2],
 * [7]
 * ]
 */
public class _314_BinaryTreeVerticalOrderTraversal {

    @Test
    public void firstTest() {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Assert.assertThat(verticalOrder(root), contains(
                Arrays.asList(9),
                Arrays.asList(3, 15),
                Arrays.asList(20),
                Arrays.asList(7)
        ));
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        LinkedList<Position> queue = new LinkedList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();

        queue.add(new Position(root, 0));
        while (!queue.isEmpty()) {
            Position position = queue.remove();
            List<Integer> list = map.computeIfAbsent(position.column, k -> new ArrayList<>());
            list.add(position.node.val);
            if (position.node.left != null) {
                queue.add(new Position(position.node.left, position.column - 1));
            }
            if (position.node.right != null) {
                queue.add(new Position(position.node.right, position.column + 1));
            }
        }

        List<List<Integer>> results = new ArrayList<>();
        map.keySet().stream().sorted().forEach(c -> {
            results.add(map.get(c));
        });
        return results;
    }

    class Position {
        TreeNode node;
        int column;

        Position(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
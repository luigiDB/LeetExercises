package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node. Right boundary is defined as the path from root to the right-most node. If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary. Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. If not, travel to the right subtree. Repeat until you reach a leaf node.
 *
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 * Input:
 *   1
 *    \
 *     2
 *    / \
 *   3   4
 *
 * Ouput:
 * [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 * Example 2
 * Input:
 *     ____1_____
 *    /          \
 *   2            3
 *  / \          /
 * 4   5        6
 *    / \      / \
 *   7   8    9  10
 *
 * Ouput:
 * [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 */
public class _545_BoundaryOfBinaryTree {

    @Test
    public void base() {
        Node tree = new Node(1);
        Assert.assertEquals(Arrays.asList(1), treePerimeter(tree));
    }

    @Test
    public void firstExample() {
        Node tree = new Node(1);
        Node tmp = new Node(2);
        tmp.setLeft(new Node(3));
        tmp.setRight(new Node(4));
        tree.setRight(tmp);
        Assert.assertEquals(Arrays.asList(1, 3, 4, 2), treePerimeter(tree));
    }

    @Test
    public void complexExample() {
        Node tree = new Node(1);

        Node tmp = new Node(5);
        tmp.setLeft(new Node(7));
        tmp.setRight(new Node(8));

        Node tmp2 = new Node(2);
        tmp2.setLeft(new Node(4));
        tmp2.setRight(tmp);

        tree.setLeft(tmp2);

        tmp = new Node(6);
        tmp.setLeft(new Node(9));
        tmp.setRight(new Node(10));

        tmp2 = new Node(3);
        tmp2.setLeft(tmp);

        tree.setRight(tmp2);

        Assert.assertEquals(Arrays.asList(1,2,4,7,8,9,10,6,3), treePerimeter(tree));
    }

    private List<Integer> treePerimeter(Node tree) {
        List<Node> visitOrder = new LinkedList<>();
        visitOrder.add(tree);

        Node tmp = tree;
        while (tmp.getLeft() != null) {
            tmp = tmp.getLeft();
            visitOrder.add(tmp);
        }

        List<Node> leaves = new LinkedList<>();
        getAllLeaves(tree, leaves);

        leaves = leaves.stream().filter(l -> !visitOrder.contains(l)).collect(Collectors.toList());
        visitOrder.addAll(leaves);

        tmp = tree;
        leaves  = new LinkedList<>();
        while(tmp.getLeft() != null || tmp.getRight() != null) {
            if (tmp.getRight() != null)
                tmp = tmp.getRight();
            else
                tmp = tmp.getLeft();
            leaves.add(0, tmp);
        }

        leaves = leaves.stream().filter(l -> !visitOrder.contains(l)).collect(Collectors.toList());
        visitOrder.addAll(leaves);

        return visitOrder.stream().map(Node::getValue).collect(Collectors.toList());
    }

    private void getAllLeaves(Node node, List<Node> leaves) {
        if(node.getLeft() == null && node.getRight() == null)
            leaves.add(node);

        if(node.getLeft() != null)
            getAllLeaves(node.getLeft(), leaves);

        if(node.getRight() != null)
            getAllLeaves(node.getRight(), leaves);
    }

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

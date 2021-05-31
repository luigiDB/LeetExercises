package facebook;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * There is a binary tree with N nodes. You are viewing the tree from its left side and can see only the leftmost
 * nodes at each level. Return the number of visible nodes.
 * Note: You can see only the leftmost nodes, but that doesn't mean they have to be left nodes. The leftmost node at a
 * level could be a right node.
 */
public class NumberOfVisibleNodes {

    @Test
    public void a() {

        Node root_1 = new Node(8);
        root_1.left = new Node(3);
        root_1.right = new Node(10);
        root_1.left.left = new Node(1);
        root_1.left.right = new Node(6);
        root_1.right.right = new Node(14);
        root_1.left.right.left = new Node(4);
        root_1.left.right.right = new Node(7);
        root_1.right.right.left = new Node(13);
        assertEquals(4, visibleNodes(root_1));

        Node root_2 = new Node(10);
        root_2.left = new Node(8);
        root_2.right = new Node(15);
        root_2.left.left = new Node(4);
        root_2.left.left.right = new Node(5);
        root_2.left.left.right.right = new Node(6);
        root_2.right.left = new Node(14);
        root_2.right.right = new Node(16);

        assertEquals(5, visibleNodes(root_2));
    }

    class Node {
        int data;
        Node left;
        Node right;
        Node() {
            this.data = 0;
            this.left = null;
            this.right = null;
        }
        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Add any helper functions you may need here
    private int depth(Node node, int level) {

        if(node == null)
            return level;
        return Math.max(
                depth(node.left, level + 1),
                depth(node.right, level + 1)
        );
    }

    int visibleNodes(Node root) {
        // Write your code here
        return depth(root, 0);
    }
}

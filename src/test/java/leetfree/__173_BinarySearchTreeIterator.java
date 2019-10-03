package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 */
public class __173_BinarySearchTreeIterator {

    @Test
    public void given() {
        Node root = new Node(7);
        root.left = new Node(3);
        root.right = new Node(15);
        root.right.left = new Node(9);
        root.right.right = new Node(20);


        BSTIterator iterator = new BSTIterator(root);
        Assert.assertEquals(3, iterator.next());    // return 3
        Assert.assertEquals(7, iterator.next());    // return 7
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(9, iterator.next());    // return 9
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(15, iterator.next());    // return 15
        Assert.assertTrue(iterator.hasNext()); // return true
        Assert.assertEquals(20, iterator.next());    // return 20
        Assert.assertFalse(iterator.hasNext()); // return false
    }

    @Test
    public void complex() {
        Node root = new Node(10);
        root.left = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(8);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(4);
        root.right = new Node(20);
        root.left.right.left = new Node(7);

        BSTIterator iterator = new BSTIterator(root);
        Assert.assertEquals(2, iterator.next());
        Assert.assertEquals(3, iterator.next());
        Assert.assertEquals(4, iterator.next());
        Assert.assertEquals(5, iterator.next());
        Assert.assertEquals(7, iterator.next());
        Assert.assertEquals(8, iterator.next());
        Assert.assertEquals(10, iterator.next());
        Assert.assertEquals(20, iterator.next());
    }

    private class BSTIterator {
        private Node root;
        private final Stack<Node> stack;

        public BSTIterator(Node root) {
            this.root = root;
            stack = new Stack<>();
            stack.push(root);

            moveLeft();
        }

        private void moveLeft() {
            while (stack.peek().left != null) {
                stack.push(stack.peek().left);
            }
        }

        public int next() {
            Node toBeReturned = stack.pop();

            if (toBeReturned.right != null) {
                // we are not in a leaf
                stack.push(toBeReturned.right);
                moveLeft();
            } else {
                // leaf
                // nothing to do
            }

            return toBeReturned.value;
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}

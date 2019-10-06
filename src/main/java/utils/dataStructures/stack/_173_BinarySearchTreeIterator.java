package utils.dataStructures.stack;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * <p>
 * Calling next() will return the next smallest number in the BST.
 */
public class _173_BinarySearchTreeIterator {

    private Node root;
    private final Stack<Node> stack;

    public _173_BinarySearchTreeIterator(Node root) {
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


    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
}


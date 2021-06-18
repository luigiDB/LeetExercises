package utils.dataStructures.stack;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 */
public class _173_BinarySearchTreeIterator {

    private Node node;
    private final Stack<Node> stack;

    public _173_BinarySearchTreeIterator(Node root) {
        this.node = root;
        stack = new Stack<>();
    }

    public int next() {
        if (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            Node tmp = stack.pop();
            node = tmp.right;
            return tmp.value;
        }
        return -1;
    }

    public boolean hasNext() {
        return node != null || !stack.isEmpty();
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


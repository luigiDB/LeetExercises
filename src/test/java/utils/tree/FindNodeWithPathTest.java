package utils.tree;

import junit.framework.TestCase;

import java.util.List;

public class FindNodeWithPathTest extends TestCase {

    public void test() {
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right = new Node<>(3);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        List<Integer> integers = FindNodeWithPath.find(root, 5);
        assertEquals("[1, 2, 5]", integers.toString());
    }
}
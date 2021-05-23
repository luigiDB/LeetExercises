package utils.tree;

public class Node<T> {

    public T info;
    public Node<T> left;
    public Node<T> right;

    public Node(T info) {
        this.info = info;
        left = null;
        right = null;
    }

    public static <T> Node<T> of(T info) {
        return new Node<>(info);
    }

    public static <T> Node<T> of(T info, Node<T> left, Node<T> right) {
        Node<T> node = new Node<>(info);
        node.left = left;
        node.right = right;
        return node;
    }
}

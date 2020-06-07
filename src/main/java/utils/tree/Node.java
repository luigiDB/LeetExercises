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
}

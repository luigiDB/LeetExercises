package utils.tree;

public class Node<T> {

    T info;
    T left;
    T right;

    public Node(T info) {
        this.info = info;
        left = null;
        right = null;
    }
}

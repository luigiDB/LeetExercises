package utils.tree.visit;

import utils.tree.Node;

import java.util.List;

public class Tree<T> {
    //TODO

    private Node<T> root;

    public Tree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getTree() {
        return root;
    }

    public List<T> inOrder() {
        return null;
    }

    public List<T> postOrder() {
        return null;
    }

    public List<T> outOrder() {
        return null;
    }
}

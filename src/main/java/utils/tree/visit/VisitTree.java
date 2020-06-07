package utils.tree.visit;

import utils.dataStructures.stack._94_BinaryTreeInorderTraversal;
import utils.tree.Node;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class VisitTree<T> {

    private Node<T> root;

    public VisitTree(Node<T> root) {
        this.root = root;
    }

    public Node<T> getTree() {
        return root;
    }

    public List<T> inOrder() {
        List<T> res = new ArrayList<>();
        Stack<Node> stack = new Stack<>();
        Node<T> curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.info);
            curr = curr.right;
        }
        return res;
    }

    public List<T> postOrder() {
        List<T> res = new ArrayList<>();
        recursivePostVisit(res, root);
        return res;
    }

    private void recursivePostVisit(List<T> visit, Node<T> node) {
        if (node == null)
            return;
        recursivePostVisit(visit, node.left);
        recursivePostVisit(visit, node.right);
        visit.add(node.info);
    }

    public List<T> preOrder() {
        List<T> res = new ArrayList<>();
        recursivePreVisit(res, root);
        return res;
    }

    private void recursivePreVisit(List<T> visit, Node<T> node) {
        if (node == null)
            return;
        visit.add(node.info);
        recursivePreVisit(visit, node.left);
        recursivePreVisit(visit, node.right);
    }
}

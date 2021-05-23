package utils.tree.visit;

import utils.tree.Node;

import java.util.*;

public class ThreeVisitAlgorithms {

    public static <T> List<T> recursiveDfs(Node<T> root) {
        List<T> visit = new ArrayList<>();
        dfs(root, visit);
        return visit;
    }

    private static <T> void dfs(Node<T> root, List<T> visit) {
        if(root == null)
            return;
        visit.add(root.info);
        dfs(root.left, visit);
        dfs(root.right, visit);
    }

    public static <T> List<T> recursiveDfs2(Node<T> root) {
        return dfsWithList(root);
    }

    private static <T> List<T> dfsWithList(Node<T> root) {
        if(root == null)
            return Collections.emptyList();

        List<T> res = new ArrayList<>();
        res.add(root.info);
        res.addAll(dfsWithList(root.left));
        res.addAll(dfsWithList(root.right));
        return res;
    }

    public static <T> List<T> iterativeDfs(Node<T> root) {
        List<T> visit = new ArrayList<>();

        Stack<Node<T>> queue = new Stack<>();
        queue.push(root);

        while(!queue.isEmpty()) {
            Node<T> pop = queue.pop();
            if(pop == null)
                continue;
            visit.add(pop.info);

            queue.push(pop.right);
            queue.push(pop.left);
        }

        return visit;
    }

    public static <T> List<T> iterativeBfs(Node<T> root) {
        List<T> visit = new ArrayList<>();
        Queue<Node<T>> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> poll = queue.poll();

            if(poll == null)
                continue;

            visit.add(poll.info);
            queue.add(poll.left);
            queue.add(poll.right);
        }

        return visit;
    }

    public static <T> List<T> inOrderWithStack(Node<T> root) {
        List<T> visit = new ArrayList<>();
        Stack<Node<T>> stack = new Stack<>();

        Node<T> current = root;
        while (current != null || !stack.isEmpty()) {
            while (current!=null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            visit.add(current.info);
            current = current.right;

        }

        return visit;
    }
}

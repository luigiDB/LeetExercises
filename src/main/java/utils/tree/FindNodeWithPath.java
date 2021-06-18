package utils.tree;

import java.util.LinkedList;
import java.util.List;

public class FindNodeWithPath {

    public static List<Integer> find(Node<Integer> root, int target) {
        List<Integer> visited = new LinkedList<>();

        return dfs(root, visited, target);
    }

    private static List<Integer> dfs(Node<Integer> root, List<Integer> visited, int target) {

        if(root == null)
            return null;
        if(root.info == target) {
            visited.add(root.info);
            return visited;
        }

        List<Integer> tmp = new LinkedList<>(visited);
        tmp.add(root.info);
        List<Integer> a = dfs(root.left, tmp, target);
        if(a != null)
            return a;
        List<Integer> b = dfs(root.right, tmp, target);
        if(b != null)
            return b;

        return null;
    }
}

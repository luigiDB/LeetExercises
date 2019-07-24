package utils.graph.visit;

import utils.graph.egde.IEdge;

import java.util.List;
import java.util.Map;

/**
 * The graph is considered Acyclic by definition
 *
 * @param <T>
 */
public class DFSWithDepth<T> {

    private Map<T, List<IEdge<T>>> matrixGraph;
    private Object[] nodes;
    int maxDepth = 0;

    public DFSWithDepth(Map<T, List<IEdge<T>>> matrixGraph, Object[] nodes) {
        this.matrixGraph = matrixGraph;
        this.nodes = nodes;
    }


    public int visit(T c) {
        dfs(c, 0);
        return maxDepth;
    }

    private void dfs(T root, int depth) {
        maxDepth = Math.max(maxDepth, depth);
        List<IEdge<T>> edges = matrixGraph.get(root);
        if (edges != null)
            for (IEdge<T> edge : edges) {
                dfs(edge.getNodeF(), depth+1);
            }
    }
}

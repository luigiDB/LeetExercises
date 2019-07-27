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

    public DFSWithDepth(Map<T, List<IEdge<T>>> matrixGraph, Object[] nodes) {
        this.matrixGraph = matrixGraph;
        this.nodes = nodes;
    }


    public int visit(T c) {
        return dfs(c);
    }

    private int dfs(T root) {
        int subCount = 0;
        List<IEdge<T>> edges = matrixGraph.get(root);
        if (edges != null)
            for (IEdge<T> edge : edges) {
                subCount = Math.max(subCount, dfs(edge.getNodeF())+1);
            }
        return subCount;
    }
}

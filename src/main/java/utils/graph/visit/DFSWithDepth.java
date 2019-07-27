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
        int depth = 0;
        List<IEdge<T>> iEdges = matrixGraph.get(root);
        if (iEdges != null) {
            for (IEdge<T> edge : iEdges) {
                depth = Math.max(depth, dfs(edge.getNodeF()) + 1);
            }
        }
        return depth;
    }
}

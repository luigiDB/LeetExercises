package utils.graph.algorithm;

import utils.graph.egde.IEdge;

import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSort<T> {

    private final List<IEdge<T>> edges;
    private final T[] nodes;
    private final Map<T, List<IEdge<T>>> graph;

    public TopologicalSort(List<IEdge<T>> edges, T[] nodes) {
        this.edges = edges;
        this.nodes = nodes;
        graph = edges.stream().collect(Collectors.groupingBy(
                IEdge::getNodeS,
                Collectors.mapping(i -> i, Collectors.toList())
        ));
    }

    public Stack<T> sort() {
        Stack<T> result = new Stack<>();
        Set<T> toBeVisitedNodes = new HashSet<>(Arrays.asList(nodes));

        while (!toBeVisitedNodes.isEmpty()) {
            T anyNode = toBeVisitedNodes.stream().findAny().get();

            visit(anyNode, result, toBeVisitedNodes);
        }
        return result;
    }

    private void visit(T node, Stack<T> result, Set<T> toBeVisitedNodes) {
        toBeVisitedNodes.remove(node);

        List<IEdge<T>> nextNodes = graph.get(node);
        if (nextNodes != null)
            for (IEdge<T> edge : nextNodes) {
                if (toBeVisitedNodes.contains(edge.getNodeF())) {
                    visit(edge.getNodeF(), result, toBeVisitedNodes);
                }
            }
        result.push(node);
    }
}

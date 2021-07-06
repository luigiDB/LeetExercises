package utils.graph.topologicalSort;

import utils.graph.egde.IEdge;

import java.util.*;
import java.util.stream.Collectors;

public class TopologicalSort<T> {

    private final T[] nodes;
    private final Map<T, List<IEdge<T>>> graph;

    public TopologicalSort(List<IEdge<T>> edges, T[] nodes) {
        this.nodes = nodes;
        graph = edges.stream().collect(Collectors.groupingBy(
                IEdge::getNodeS,
                Collectors.mapping(i -> i, Collectors.toList())
        ));
    }

    public Stack<T> sort() {
        Stack<T> result = new Stack<>();
        Set<T> visited = new HashSet<>();

        while (visited.size() != nodes.length) {
            for (T node : nodes) {
                if (!visited.contains(node)) {
                    topologicalSort(node, result, visited);
                }
            }
        }

        return result;
    }

    private void topologicalSort(T node, Stack<T> result, Set<T> visited) {
        visited.add(node);

        for (IEdge<T> next : graph.getOrDefault(node, Collections.emptyList())) {
            if (!visited.contains(next.getNodeF())) {
                topologicalSort(next.getNodeF(), result, visited);
            }
        }
        result.push(node);
    }
}



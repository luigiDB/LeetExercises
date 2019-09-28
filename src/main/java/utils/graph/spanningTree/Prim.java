package utils.graph.spanningTree;

import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.undirected.WeightedUndirectedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Prim<T> {
    private final List<WeightedUndirectedEdge<T>> edges;
    private final T[] vertexes;
    private final Map<T, Set<WeightedUndirectedEdge<T>>> graph;

    public Prim(List<WeightedUndirectedEdge<T>> edges, T[] vertexes) {
        this.edges = edges;
        this.vertexes = vertexes;
        graph = new HashMap<>();
        for (T vertex : vertexes) {
            graph.put(vertex, new HashSet<>());
        }
        edges.stream().forEach(e -> {
            graph.compute(e.getNodeS(), (k, v) -> {
                Set<WeightedUndirectedEdge<T>> tmp = new HashSet<>();
                tmp.addAll(v);
                tmp.add(e);
                return tmp;
            });

            graph.compute(e.getNodeF(), (k, v) -> {
                Set<WeightedUndirectedEdge<T>> tmp = new HashSet<>();
                tmp.addAll(v);
                tmp.add(new WeightedUndirectedEdge<>(e.getNodeF(), e.getNodeS(), e.getCost()));
                return tmp;
            });
        });
    }

    public List<WeightedUndirectedEdge<T>> mst() {
        List<WeightedUndirectedEdge<T>> returnList = new LinkedList<>();

        int[] distances = new int[vertexes.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Set<T> visited = new HashSet<>();
        Queue<WeightedUndirectedEdge<T>> heap = new PriorityQueue<>(Comparator.comparingInt(WeightedUndirectedEdge::getCost));

        visited.add(vertexes[0]);
        heap.addAll(graph.getOrDefault(vertexes[0], Collections.emptySet()));

        while (!heap.isEmpty()) {
            WeightedUndirectedEdge<T> poll = heap.poll();
            if (!visited.contains(poll.getNodeF())) {
                visited.add(poll.getNodeF());
                heap.addAll(graph.getOrDefault(poll.getNodeF(), Collections.emptySet()));
                returnList.add(poll);
            }
        }

        return returnList;
    }
}


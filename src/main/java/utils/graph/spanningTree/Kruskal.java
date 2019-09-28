package utils.graph.spanningTree;

import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.undirected.WeightedUndirectedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Kruskal<T> {

    private final List<WeightedUndirectedEdge<T>> edges;
    private final T[] vertexes;
    private final Map<T, List<WeightedUndirectedEdge<T>>> graph;
    private int[] sets;
    private Queue<WeightedUndirectedEdge<T>> heap;

    public Kruskal(List<WeightedUndirectedEdge<T>> edges, T[] vertexes) {
        this.edges = edges;
        this.vertexes = vertexes;
        graph = edges.stream().collect(
                Collectors.groupingBy(
                        IWeightedEdge::getNodeS,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
    }

    public List<WeightedUndirectedEdge<T>> mst() {
        List<WeightedUndirectedEdge<T>> returnList = new LinkedList<>();
        sets = new int[vertexes.length];
        for (int i = 0; i < sets.length; i++) {
            sets[i] = i;
        }
        heap = new PriorityQueue<>(Comparator.comparingInt(WeightedUndirectedEdge::getCost));
        heap.addAll(edges);

        while (!heap.isEmpty()) {
            WeightedUndirectedEdge<T> edge = heap.poll();
            int s = treeOf(edge.getNodeS());
            int f = treeOf(edge.getNodeF());
            boolean b = s != f;
            if (b) {
                merge(edge.getNodeS(), edge.getNodeF());
                returnList.add(edge);
            }
        }

        return returnList;
    }

    private void merge(T nodeS, T nodeF) {
        int s = Arrays.asList(vertexes).indexOf(nodeS);
        int f = Arrays.asList(vertexes).indexOf(nodeF);
        if (sets[s] < sets[f])
            sets[f] = sets[s];
        else
            sets[s] = sets[f];
    }

    private int treeOf(T node) {
        int index = Arrays.asList(vertexes).indexOf(node);
        while (sets[index] != index) {
            index = sets[index];
        }
        return index;
    }
}

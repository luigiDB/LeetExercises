package utils.graph.algorithm;

import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dijkstra<T> {

    private List<IWeightedEdge<T>> edges;
    private T[] vertexes;
    private final Map<T, List<IWeightedEdge<T>>> graph;
    private Integer[] distances;
    private Integer[] previouses;

    public Dijkstra(List<IWeightedEdge<T>> edges, T[] vertexes) {
        this.edges = edges;
        this.vertexes = vertexes;
        graph = edges.stream().collect(
                Collectors.groupingBy(
                        IWeightedEdge::getNodeS,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
    }

    public List<Integer> getDistances() {
        List<Integer> ints = Arrays.asList(distances);
        return ints;
    }

    public List<Integer> getPreviouses() {
        return Arrays.asList(previouses);
    }

    public void dijkstraDistance(T startNode) {
        Queue<IWeightedEdge> priorityQueue = new PriorityQueue<>(Comparator.comparing(IWeightedEdge::getCost));

        distances = new Integer[vertexes.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        previouses = new Integer[vertexes.length];
        Arrays.fill(previouses, 0);

        priorityQueue.add(new WeightedDirectedEdge(startNode, startNode, 0));
        distances[Arrays.asList(vertexes).indexOf(startNode)] = 0;

        while (!priorityQueue.isEmpty()) {
            IWeightedEdge<T> poll = priorityQueue.poll();

            List<IWeightedEdge<T>> nextEdges = graph.get(poll.getNodeF());
            if (nextEdges != null)
                for (IWeightedEdge<T> edge : nextEdges) {
                    int indexOfNextVertex = Arrays.asList(vertexes).indexOf(edge.getNodeF());
                    int indexOfCurrentVertex = Arrays.asList(vertexes).indexOf(edge.getNodeS());

                    if (distances[indexOfNextVertex] > distances[indexOfCurrentVertex] + edge.getCost()) {
                        distances[indexOfNextVertex] = distances[indexOfCurrentVertex] + edge.getCost();
                        previouses[indexOfNextVertex] = indexOfCurrentVertex;
                        priorityQueue.add(edge);
                    }
                }
        }
    }
}
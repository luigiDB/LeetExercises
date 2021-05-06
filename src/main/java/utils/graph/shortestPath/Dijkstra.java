package utils.graph.shortestPath;

import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Dijkstra<T> {
    /**
     * Dijkstra’s spanningTree is a Greedy spanningTree and time complexity is O(VLogV)
     * Dijkstra doesn’t work for Graphs with negative weight edges
     */

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

    public List<Integer> getDistances() { return Arrays.asList(distances); }

    public List<Integer> getPreviouses() {
        return Arrays.asList(previouses);
    }

    public void dijkstraDistance(T startNode) {
        Queue<IWeightedEdge<T>> priorityQueue = new PriorityQueue<>(Comparator.comparing(IWeightedEdge::getCost));

        distances = new Integer[vertexes.length];
        Arrays.fill(distances, Integer.MAX_VALUE);

        previouses = new Integer[vertexes.length];
        Arrays.fill(previouses, 0);

        priorityQueue.add(new WeightedDirectedEdge<T>(startNode, startNode, 0));
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
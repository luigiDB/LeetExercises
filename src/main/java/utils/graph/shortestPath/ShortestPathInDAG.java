package utils.graph.shortestPath;

import utils.graph.egde.IEdge;
import utils.graph.egde.IWeightedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ShortestPathInDAG {
    /**
     * https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
     * <p>
     * For a general weighted graph, we can calculate single source shortest distances in O(VE) time using
     * Bellman–Ford Algorithm. For a graph with no negative weights, we can do better and calculate single source
     * shortest distances in O(E + VLogV) time using Dijkstra’s spanningTree. Can we do even better for Directed Acyclic
     * Graph (DAG)? We can calculate single source shortest distances in O(V+E) time for DAGs. The idea is to use
     * Topological Sorting.
     * <p>
     * Use djikstra on topological sort
     */

    private List<IWeightedEdge<Integer>> edges;
    private Integer[] vertexes;
    private final Map<Integer, List<IWeightedEdge<Integer>>> graph;
    private Integer[] distances;
    private Integer[] previouses;

    public ShortestPathInDAG(List<IWeightedEdge<Integer>> edges, Integer[] vertexes) {
        this.edges = edges;
        this.vertexes = vertexes;
        graph = edges.stream().collect(
                Collectors.groupingBy(
                        IWeightedEdge::getNodeS,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
        distances = new Integer[vertexes.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        previouses = new Integer[vertexes.length];
        Arrays.fill(previouses, 0);
    }

    public void minDistanceFrom(int i) {
        Stack<Integer> topologicalSort = topologicalSort();
//        Stack<Integer> topologicalSort = new TopologicalSort(edges, vertexes).sort();

        distances[i] = 0;
        while (!topologicalSort.isEmpty()) {
            Integer current = topologicalSort.pop();

            /**THIS IS THE IMPORTANT PART
             * we update update the distance only if the current distance is not INF
             */
            if (distances[current] != Integer.MAX_VALUE) {
                for (IWeightedEdge<Integer> next : graph.getOrDefault(current, Collections.emptyList())) {
                    if (distances[next.getNodeF()] > distances[next.getNodeS()] + next.getCost()) {
                        distances[next.getNodeF()] = distances[next.getNodeS()] + next.getCost();
                        previouses[next.getNodeF()] = next.getNodeS();
                    }
                }
            }
        }
    }

    private Stack<Integer> topologicalSort() {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            if (!visited[i])
                topologicalSortDiscovery(i, visited, stack);
        }

        return stack;
    }

    private void topologicalSortDiscovery(int i, boolean[] visited, Stack<Integer> stack) {
        visited[i] = true;

        for (IEdge<Integer> next : graph.getOrDefault(i, Collections.emptyList())) {
            if (!visited[next.getNodeF()]) {
                topologicalSortDiscovery(next.getNodeF(), visited, stack);
            }
        }

        stack.push(i);
    }

    public List<Integer> getDistances() {
        return Arrays.asList(distances);
    }

    public List<Integer> getPreviouses() {
        return Arrays.asList(previouses);
    }

}

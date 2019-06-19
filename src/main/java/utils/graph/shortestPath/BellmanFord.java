package utils.graph.shortestPath;

import utils.graph.egde.IWeightedEdge;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BellmanFord {
    /**
     * https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/
     * Given a graph and a source vertex src in graph, find shortest paths from src to all vertices in the given graph.
     * Bellman-Ford works on Graphs with negative weight edges. Bellman-Ford is also simpler than Dijkstra and suites
     * well for distributed systems. But time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
     */

    private List<IWeightedEdge<Integer>> edges;
    private Integer[] vertexes;
    private final Map<Integer, List<IWeightedEdge<Integer>>> graph;
    private Integer[] distances;
    private Integer[] previouses;

    public BellmanFord(List<IWeightedEdge<Integer>> edges, Integer[] vertexes) {
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

    public void bellmanFordDistance(int startNode) {

        distances[startNode] = 0;

        for (int i = 0; i < vertexes.length; i++) {
            for (IWeightedEdge<Integer> edge : edges) {
                /**IMPORTANT PART
                 * We update update the distance only if the current distance is not INF
                 */
                if (distances[edge.getNodeS()] != Integer.MAX_VALUE
                        && distances[edge.getNodeF()] > distances[edge.getNodeS()] + edge.getCost()) {
                    distances[edge.getNodeF()] = distances[edge.getNodeS()] + edge.getCost();
                    previouses[edge.getNodeF()] = edge.getNodeS();
                }
            }
        }

        for (IWeightedEdge<Integer> edge : edges) {
            if (distances[edge.getNodeS()] != Integer.MAX_VALUE
                    && distances[edge.getNodeF()] > distances[edge.getNodeS()] + edge.getCost()) {
                throw (new RuntimeException("Graph contains negative cycles"));
            }
        }
    }

    public List<Integer> getDistances() {
        return Arrays.asList(distances);
    }

    public List<Integer> getPreviouses() {
        return Arrays.asList(previouses);
    }
}

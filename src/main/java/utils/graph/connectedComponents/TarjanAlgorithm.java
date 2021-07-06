package utils.graph.connectedComponents;

import org.apache.commons.lang3.tuple.Pair;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Linear time algorithm for finding the bridges in a graph was described by Robert Tarjan in 1974.
 * For implementation follow
 * {@link leetfree._1192_CriticalConnectionsInANetwork}
 */
public class TarjanAlgorithm {

    private int[] visitedTime;
    private int[] lowTime;
    private int time;
    private Map<Integer, List<UnweightedDirectedEdge<Integer>>> graph;

    public TarjanAlgorithm(Map<Integer, List<UnweightedDirectedEdge<Integer>>> graph) {
        this.graph = graph;
        int v = graph.size();
        visitedTime = new int[v];
        lowTime = new int[v];
        time = 0;
        boolean[] visited = new boolean[v];
        dfs(0, visited);
    }

    public List<Set<Integer>> findStronglyConnectedComponents() {
        //Pair -> {index, value}
        Map<Integer, List<Pair<Integer, Integer>>> collect = IntStream.range(0, lowTime.length)
                .mapToObj(i -> Pair.of(i, lowTime[i]))
                .collect(Collectors.groupingBy(Pair::getRight));
        return collect
                .values()
                .stream().map(lp -> lp.stream()
                        .map(Pair::getLeft)
                        .collect(Collectors.toSet())
                )
                .collect(Collectors.toList());
    }

    public List<UnweightedDirectedEdge<Integer>> findCriticalConnection() {
        return graph.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(edge -> lowTime[edge.getNodeS()] != lowTime[edge.getNodeF()])
                .collect(Collectors.toList());
    }

    private void dfs(int current, boolean[] visited) {
        visited[current] = true;
        visitedTime[current] = lowTime[current] = time++;
        for (UnweightedDirectedEdge<Integer> next : graph.get(current)) {
            Integer nextNode = next.getNodeF();
            if (visited[nextNode]) {
                lowTime[current] = Math.min(lowTime[current], visitedTime[nextNode]);
            } else {
                dfs(nextNode, visited);
                lowTime[current] = Math.min(lowTime[current], lowTime[nextNode]);
            }
        }
    }
}


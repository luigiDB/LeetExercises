package utils.graph.circuit;

import utils.graph.egde.IEdge;

import java.util.*;
import java.util.stream.Collectors;

public class BacktrackingCircuitSearch {

    private final List<IEdge<Integer>> edgeList;
    private final Integer[] nodes;
    private final Map<Integer, List<IEdge<Integer>>> graph;

    public BacktrackingCircuitSearch(List<IEdge<Integer>> edgeList, Integer[] nodes) {
        this.edgeList = edgeList;
        this.nodes = nodes;

        graph = edgeList.stream().collect(Collectors.groupingBy(IEdge::getNodeS, Collectors.toList()));
    }

    public List<LinkedList<Integer>> discoverCircuit() {
        List<LinkedList<Integer>> possiblePaths = new LinkedList<>();
        LinkedList<Integer> path = new LinkedList<>();
        Set<IEdge<Integer>> visisted = new HashSet<>();

        path.add(nodes[0]);

        recursiveExploration(path, possiblePaths, visisted);

        return possiblePaths;
    }

    private void recursiveExploration(LinkedList<Integer> path, List<LinkedList<Integer>> possiblePaths, Set<IEdge<Integer>> visited) {
        List<IEdge<Integer>> nexts = graph.get(path.getLast());
        if (nexts != null) {
            for (IEdge<Integer> next : nexts) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    path.add(next.getNodeF());

                    recursiveExploration(path, possiblePaths, visited);

                    path.removeLast();
                    visited.remove(next);
                }
            }
        }

        if (path.getFirst().equals(path.getLast())) {
            if (exausted(visited)) {
                possiblePaths.add(new LinkedList<>(path));
            }
        }
    }

    private boolean exausted(Set<IEdge<Integer>> visited) {
        Set<IEdge<Integer>> tmp = new HashSet<>(edgeList);
        tmp.removeAll(visited);
        return tmp.isEmpty();
        /*for (Integer node : graph.keySet()) {
            List<IEdge<Integer>> nexts = graph.get(node);
            if (nexts != null && nexts.size() != 0)
                return false;
        }
        return true;*/
    }
}

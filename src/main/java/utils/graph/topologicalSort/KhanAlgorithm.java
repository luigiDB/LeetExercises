package utils.graph.topologicalSort;

import utils.graph.egde.IEdge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KhanAlgorithm {
    /**
     * A DAG G has at least one vertex with in-degree 0 and one vertex with out-degree 0.
     * The Idea is to keep at each iteration the node with zero fanIn.
     */

    private final List<IEdge<Integer>> edgeList;
    private final Integer[] nodes;
    private final Map<Integer, List<IEdge<Integer>>> graph;


    public KhanAlgorithm(List<IEdge<Integer>> edgeList, Integer[] nodes) {
        this.edgeList = edgeList;
        this.nodes = nodes;

        graph = edgeList.stream().collect(Collectors.groupingBy(
                IEdge::getNodeS,
                Collectors.mapping(i -> i, Collectors.toList())
        ));
    }

    public List<Integer> sort() {
        List<Integer> visitOrder = new LinkedList<>();
        boolean[] visited = new boolean[nodes.length];
        int[] fanIn = evaluateStartingFanIn();

        while (checkNonVisitedNodes(visited))
            khanTopologicalSort(visited, fanIn, visitOrder);

        return visitOrder;
    }

    private int[] evaluateStartingFanIn() {
        int[] fanIn = new int[nodes.length];
        for (Integer node : nodes) {
            List<IEdge<Integer>> nexts = graph.get(node);
            if (nexts != null) {
                for (IEdge<Integer> next : nexts) {
                    fanIn[next.getNodeF()]++;
                }
            }
        }
        return fanIn;
    }

    private boolean checkNonVisitedNodes(boolean[] visited) {
        for (boolean b : visited)
            if (!b)
                return true;
        return false;
    }

    private void khanTopologicalSort(boolean[] visited, int[] fanIn, List<Integer> visitOrder) {
        for (int i = 0; i < nodes.length; i++) {
            if (!visited[i] && fanIn[i] == 0) {
                visitOrder.add(i);
                visited[i] = true;
                List<IEdge<Integer>> nexts = graph.get(nodes[i]);
                if (nexts != null) {
                    for (IEdge<Integer> next : nexts) {
                        fanIn[next.getNodeF()]--;
                    }
                }
            }
        }
    }
}

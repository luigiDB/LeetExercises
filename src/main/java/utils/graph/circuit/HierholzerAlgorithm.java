package utils.graph.circuit;

import utils.graph.egde.IEdge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * With Heirholzer’s Algorithm, we can find the circuit/path in O(E), i.e., linear time.
 * Remember that a directed graph has an Eulerian cycle if following conditions are true
 * (1) All vertices with nonzero degree belong to a single strongly connected component.
 * (2) In degree and out degree of every vertex is same. The algorithm assumes that the given graph has Eulerian Circuit
 */
public class HierholzerAlgorithm {
    /**
     * https://www.geeksforgeeks.org/hierholzers-algorithm-directed-graph/
     * http://stones333.blogspot.com/2013/11/find-eulerian-path-in-directed-graph.html
     */

    private final List<IEdge<Integer>> edgeList;
    private final Integer[] nodes;
    private final Map<Integer, List<IEdge<Integer>>> graph;

    public HierholzerAlgorithm(List<IEdge<Integer>> edgeList, Integer[] nodes) {
        this.edgeList = edgeList;
        this.nodes = nodes;

        graph = edgeList.stream().collect(Collectors.groupingBy(IEdge::getNodeS, Collectors.toList()));
    }

    public List<Integer> discoverCircuit(Integer start) {
        Stack<IEdge<Integer>> forward = new Stack<>();
        Stack<IEdge<Integer>> backtrack = new Stack<>();

        straightforwardWalk(start, forward);
        IEdge<Integer> next;

        while (!forward.isEmpty()) {
            IEdge<Integer> peek = forward.pop();
            backtrack.push(peek);

            straightforwardWalk(peek.getNodeS(),forward);
        }

        List<Integer> path = new LinkedList<>();
        while(!backtrack.isEmpty()){
            path.add(backtrack.pop().getNodeS());
        }
        return path;
    }

    private void straightforwardWalk(Integer start, Stack<IEdge<Integer>> forward) {
        IEdge<Integer> next = getNext(start);
        while (next != null) {
            forward.push(next);
            next = getNext(next.getNodeF());
        }
    }

    private IEdge<Integer> getNext(Integer nodeF) {
        List<IEdge<Integer>> edges = graph.get(nodeF);
        if (edges != null) {
            if (edges.size() > 0) {
                IEdge<Integer> edge = edges.get(0);
                edges.remove(edge);
                return edge;
            }
        }
        return null;
    }

}

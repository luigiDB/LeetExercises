package utils.graph.circuit;

import utils.graph.egde.IEdge;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
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

        path.add(nodes[0]);

        recursiveExploration(path, possiblePaths);

        return possiblePaths;
    }

    private boolean recursiveExploration(LinkedList<Integer> path, List<LinkedList<Integer>> possiblePaths) {
        //TODO: only find the first one neeed to be extended to search all
        String p = path.toString();

        if (path.getFirst().equals(path.getLast())) {
            if(exausted()) {
                possiblePaths.add(new LinkedList<>(path));
                return true;
            }
        }

        List<IEdge<Integer>> nexts = graph.get(path.getLast());
        if (nexts != null) {
            ListIterator<IEdge<Integer>> listIterator = nexts.listIterator();
            while (listIterator.hasNext()) {
                IEdge<Integer> next = listIterator.next();
                listIterator.remove();
                path.add(next.getNodeF());

                if(recursiveExploration(path, possiblePaths)){
                    return true;
                }

                path.removeLastOccurrence(path.getLast());
                listIterator.add(next);


            }
        }
        return false;
    }

    private boolean exausted() {
        for (Integer node : graph.keySet()) {
            List<IEdge<Integer>> nexts = graph.get(node);
            if (nexts != null) {
                if (nexts.size() != 0)
                    return false;
            }
        }

        return true;
    }
}

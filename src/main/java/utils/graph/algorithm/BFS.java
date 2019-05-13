package utils.graph.algorithm;

import utils.graph.egde.IEdge;

import java.util.*;

public class BFS<T> {

    private int[][] matrixGraph;
    private T[] nodes;
    private Map<T, List<IEdge<T>>> mapGraph;

    public BFS(int[][] matrixGraph, T[] nodes) {
        if(matrixGraph.length != nodes.length || matrixGraph[0].length != nodes.length) {
            throw new RuntimeException();
        }
        this.matrixGraph = matrixGraph;
        this.nodes = nodes;
    }

    public BFS(Map<T, List<IEdge<T>>> mapGraph) {
        this.mapGraph = mapGraph;
    }

    public List<T> visit(T startNode) {
        if(mapGraph == null) {
            return matrixVisit(startNode);
        } else {
            return graphVisit(startNode);
        }
    }

    private List<T> matrixVisit(T startNode) {
        List<T> visitOrder = new LinkedList<>();
        Set<T> toBeVisited = new HashSet<>(Arrays.asList(nodes));
        toBeVisited.remove(startNode);

        Queue<T> bfsQueue = new LinkedList<>();
        bfsQueue.add(startNode);

        while(!bfsQueue.isEmpty()) {
            T poll = bfsQueue.poll();
            visitOrder.add(poll);

            int index = Arrays.asList(nodes).indexOf(poll);
            for (int i = 0; i < matrixGraph[index].length; i++) {
                if(matrixGraph[index][i] != 0) {
                    T nextVisit = nodes[i];
                    if (toBeVisited.contains(nextVisit)) {
                       toBeVisited.remove(nextVisit);
                       bfsQueue.add(nextVisit);
                   }
                }
            }
        }

        return visitOrder;
    }

    private List<T> graphVisit(T startNode) {
        return null;
    }
}

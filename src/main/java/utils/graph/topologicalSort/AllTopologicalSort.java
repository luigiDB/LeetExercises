package utils.graph.topologicalSort;

import utils.graph.egde.IEdge;

import java.util.*;
import java.util.stream.Collectors;

public class AllTopologicalSort {
    /**
     * https://www.geeksforgeeks.org/all-topological-sorts-of-a-directed-acyclic-graph/
     */

    private final List<IEdge<Integer>> edgeList;
    private final Integer[] nodes;
    private final Map<Integer, List<IEdge<Integer>>> graph;


    public AllTopologicalSort(List<IEdge<Integer>> edgeList, Integer[] nodes) {
        this.edgeList = edgeList;
        this.nodes = nodes;

        graph = edgeList.stream().collect(Collectors.groupingBy(
                IEdge::getNodeS,
                Collectors.mapping(i -> i, Collectors.toList())
        ));
    }


    public List<String> sort() {
        List<String> possibleSortings = new LinkedList<>();

        boolean visited[] = new boolean[nodes.length];
        int fanIn[] = new int[nodes.length];
        List<Integer> stack = new ArrayList<>();

        for (int i = 0; i < nodes.length; i++) {
            List<IEdge<Integer>> nexts = graph.get(nodes[i]);
            if (nexts != null) {
                for (IEdge<Integer> next : nexts) {
                    fanIn[next.getNodeF()]++;
                }
            }
        }

        allTopologicalSort(visited, stack, fanIn, possibleSortings);

        return possibleSortings;
    }

    private void allTopologicalSort(boolean[] visited, List<Integer> stack, int[] fanIn, List<String> possibleSortings) {
        boolean exahustedAllNodes = true;

        for (int i = 0; i < nodes.length; i++) {

            if(!visited[i] && fanIn[i] == 0) {

                visited[i] = true;
                stack.add(i);
                List<IEdge<Integer>> nexts = graph.get(nodes[i]);
                if (nexts != null) {
                    for (IEdge<Integer> next : nexts) {
                        fanIn[next.getNodeF()]--;
                    }
                }

                allTopologicalSort(visited, stack, fanIn, possibleSortings);

                //recover all for backtracking
                visited[i] = false;
                stack.remove(stack.size()-1);
                nexts = graph.get(nodes[i]);
                if (nexts != null) {
                    for (IEdge<Integer> next : nexts) {
                        fanIn[next.getNodeF()]++;
                    }
                }

                exahustedAllNodes = false;
            }
        }

        //Only if we haven't done anything on the for loop
        if(exahustedAllNodes) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : stack) {
                sb.append(i);
            }
            possibleSortings.add(sb.toString());
        }

    }
}

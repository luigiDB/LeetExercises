package utils.graph.topologicalSort;

import utils.graph.egde.IEdge;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AllTopologicalSortFromANode {
    private final List<IEdge<Integer>> edgeList;
    private final Integer[] nodes;
    private final Map<Integer, List<IEdge<Integer>>> graph;


    public AllTopologicalSortFromANode(List<IEdge<Integer>> edgeList, Integer[] nodes) {
        this.edgeList = edgeList;
        this.nodes = nodes;

        graph = edgeList.stream().collect(Collectors.groupingBy(
                IEdge::getNodeS,
                Collectors.mapping(i -> i, Collectors.toList())
        ));
    }

    public List<String> sort(int startNode) {
        List<String> possibleSortings = new LinkedList<>();
        boolean visited[] = new boolean[nodes.length];
        List<Integer> stack = new LinkedList<>();
        int fanIn[] = new int[nodes.length];

        for (int i = 0; i < nodes.length ; i++) {
            List<IEdge<Integer>> nexts = graph.get(nodes[i]);
            if (nexts != null) {
                for (IEdge<Integer> next : nexts) {
                    fanIn[next.getNodeF()]++;
                }
            }
        }

        //put forcefully startNode
        visited[startNode] = true;
        stack.add(startNode);
        List<IEdge<Integer>> nexts = graph.get(nodes[startNode]);
        if (nexts != null) {
            for (IEdge<Integer> next : nexts) {
                fanIn[next.getNodeF()]--;
            }
        }

        allTopologicalSort(possibleSortings, visited, stack, fanIn);

        return possibleSortings;
    }

    private void allTopologicalSort(List<String> possibleSortings, boolean[] visited, List<Integer> stack, int[] fanIn) {

        boolean end = true;
        for (int i = 0; i < nodes.length ; i++) {
            if(!visited[i] && fanIn[i]==0) {
                visited[i] = true;
                stack.add(i);
                List<IEdge<Integer>> nexts = graph.get(nodes[i]);
                if (nexts != null) {
                    for (IEdge<Integer> next : nexts) {
                        fanIn[next.getNodeF()]--;
                    }
                }

                allTopologicalSort(possibleSortings, visited, stack, fanIn);

                visited[i] = false;
                stack.remove(stack.size()-1);
                nexts = graph.get(nodes[i]);
                if (nexts != null) {
                    for (IEdge<Integer> next : nexts) {
                        fanIn[next.getNodeF()]++;
                    }
                }

                end = false;
            }
        }

        if(end) {
            StringBuilder sb  = new StringBuilder();
            for(int i : stack) {
                sb.append(i);
            }
            possibleSortings.add(sb.toString());
        }
    }
}

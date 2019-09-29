package utils.graph.visit;

import utils.graph.egde.IWeightedEdge;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindAllPaths<T> {
    private final List<IWeightedEdge<T>> edges;
    private final T[] vertexes;
    private final Map<T, List<IWeightedEdge<T>>> graph;

    public FindAllPaths(List<IWeightedEdge<T>> edges, T[] vertexes) {
        this.edges = edges;
        this.vertexes = vertexes;
        graph = edges.stream().collect(
                Collectors.groupingBy(
                        IWeightedEdge::getNodeS,
                        Collectors.mapping(Function.identity(), Collectors.toList())
                )
        );
    }

    public List<T> findRecursivelyAnyPath(T start, T end) {
        return singleDfs(start, end);
    }

    private LinkedList<T> singleDfs(T start, T end) {
        if (start.equals(end)) {
            LinkedList<T> tmp = new LinkedList<>();
            tmp.add(start);
            return tmp;
        }
        for (IWeightedEdge<T> edge : graph.getOrDefault(start, Collections.emptyList())) {
            LinkedList<T> list = singleDfs(edge.getNodeF(), end);
            if (list != null) {
                list.add(0, start);
                return list;
            }
        }
        return null;
    }

    public Set<List<T>> findRecursivelyAllPaths(T start, T end) {
        Set<List<T>> allPossiblePaths = new HashSet<>();
        LinkedList<T> currentPath = new LinkedList<>();
        multipleRecursiveDfs(start, end, allPossiblePaths, currentPath);
        return allPossiblePaths;
    }

    private void multipleRecursiveDfs(T start, T end, Set<List<T>> allPossiblePaths, LinkedList<T> currentPath) {
        if (start.equals(end)) {
            currentPath.addLast(start);
            LinkedList<T> copy = new LinkedList<>();
            copy.addAll(currentPath);
            allPossiblePaths.add(copy);
            currentPath.removeLast();
            /**
             * TODO: remember well
             * when use backtracking always undo the do
             * in this case add last and remove last in this branch
             */
            return;
        }

        for (IWeightedEdge<T> edge : graph.getOrDefault(start, Collections.emptyList())) {
            currentPath.addLast(start);
            multipleRecursiveDfs(edge.getNodeF(), end, allPossiblePaths, currentPath);
            currentPath.removeLast();
        }
    }

    public List<T> findIterativelyAnyPath(T start, T end) {
        LinkedList<T> currentPath = new LinkedList<>();
        T[] parent = vertexes.clone();
        LinkedList<T> stack = new LinkedList<>();

        stack.push(start);
        while (!stack.isEmpty()) {
            T node = stack.pop();

            for (IWeightedEdge<T> edge : graph.getOrDefault(node, Collections.emptyList())) {
                T nodeF = edge.getNodeF();
                int indexEnd = Arrays.asList(vertexes).indexOf(nodeF);
                if (parent[indexEnd].equals(nodeF)) {
                    parent[indexEnd] = edge.getNodeS();
                    stack.push(nodeF);
                }
            }
        }

        currentPath.add(end);
        while (!currentPath.getFirst().equals(start)) {
            int index = Arrays.asList(vertexes).indexOf(currentPath.getFirst());
            currentPath.addFirst(parent[index]);
        }

        return currentPath;
    }

    public Set<List<T>> findIterativelyAllPaths(T start, T end) {
        Set<List<T>> allPossiblePaths = new HashSet<>();
        LinkedList<T> currentPath = new LinkedList<>();

        currentPath.add(start);
        //TODO: it's possble to do this iteratively?

        return allPossiblePaths;
    }
}

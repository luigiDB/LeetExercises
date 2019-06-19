package utils.graph.shortestPath;

public class ShortestPathInDAG {
    /**
     * https://www.geeksforgeeks.org/shortest-path-for-directed-acyclic-graphs/
     *
     * For a general weighted graph, we can calculate single source shortest distances in O(VE) time using
     * Bellman–Ford Algorithm. For a graph with no negative weights, we can do better and calculate single source
     * shortest distances in O(E + VLogV) time using Dijkstra’s spanningTree. Can we do even better for Directed Acyclic
     * Graph (DAG)? We can calculate single source shortest distances in O(V+E) time for DAGs. The idea is to use
     * Topological Sorting.
     */
    /**
     * Use djikstra on topological sort
     */
}

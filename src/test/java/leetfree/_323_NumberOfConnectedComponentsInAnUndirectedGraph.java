package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a
function to find the number of connected components in an undirected graph.
Example 1:
   0          3
   |          |
   1 --- 2    4
Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.
Example 2:
   0           4
   |           |
   1 --- 2 --- 3
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same
as [1, 0] and thus will not appear together in edges.
*/
public class _323_NumberOfConnectedComponentsInAnUndirectedGraph {
    /**
     * This a basic example of union find or disjoint set algorithm
     */

    @Test
    public void given1() {
        Assert.assertEquals(2, countComponents(5, new int[][]{
                {0,1},
                {1,2},
                {3,4}
        }));
    }

    @Test
    public void given2() {
        Assert.assertEquals(1, countComponents(5, new int[][]{
                {0,1},
                {1,2},
                {2,3},
                {3,4}
        }));
    }

    public int countComponents(int n, int[][] edges) {
        int[] nodes = new int[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = i;
        }

        for (int[] edge : edges) {
            union(edge, nodes);
        }

        HashSet<Object> segragatedComponents = new HashSet<>();
        for (int edge : nodes)
            segragatedComponents.add(findRoot(edge, nodes));
        return segragatedComponents.size();
    }

    private void union(int[] edge, int[] nodes) {
        int a = findRoot(edge[0], nodes);
        int b = findRoot(edge[1], nodes);
        nodes[b] = a;
    }

    private int findRoot(int node, int[] nodes) {
        int pivot = nodes[node];
        while (nodes[pivot] != pivot)
            pivot = nodes[pivot];
        return pivot;
    }

}

package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class _261_GraphValidTree {

    /*Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
    For example:
    Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
    Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.*/
    @Test
    public void noCycleNoOrphan() {
        List<Edge> edges = new LinkedList<>();
        edges.add(new Edge(0,1));
        edges.add(new Edge(0,2));
        edges.add(new Edge(0,3));
        edges.add(new Edge(1,4));

        Assert.assertTrue(isValidTree(edges, 5));
    }

    @Test
    public void CycleNoOrphan() {
        List<Edge> edges = new LinkedList<>();
        edges.add(new Edge(0,1));
        edges.add(new Edge(1,2));
        edges.add(new Edge(2,3));
        edges.add(new Edge(1,3));
        edges.add(new Edge(1,4));

        Assert.assertFalse(isValidTree(edges, 5));
    }

    @Test
    public void noCycleOrphan() {
        List<Edge> edges = new LinkedList<>();
        edges.add(new Edge(0,1));
        edges.add(new Edge(0,2));
        edges.add(new Edge(0,3));
        edges.add(new Edge(1,4));

        Assert.assertFalse(isValidTree(edges, 6));
    }

    @Test
    public void CycleOrphan() {
        List<Edge> edges = new LinkedList<>();
        edges.add(new Edge(0,1));
        edges.add(new Edge(1,2));
        edges.add(new Edge(2,3));
        edges.add(new Edge(1,3));
        edges.add(new Edge(1,4));

        Assert.assertFalse(isValidTree(edges, 6));
    }

    private boolean isValidTree(List<Edge> edges, int size) {
        // A graph is a tree if there are no cycles and if all nodes are connected

        int[] sets = new int[size];
        for (int i = 0; i < size; i++) {
            sets[i] = i;
        }

        for (Edge edge: edges) {
            int rootLeft = findRoot(sets, edge.getLeft());
            int rootRight = findRoot(sets, edge.getRight());

            if(rootLeft == rootRight ) {
                //cycle
                return false;
            }
            merge(sets, rootLeft, rootRight);
        }

        for (int i = 0; i < size; i++) {
            if(sets[i] != sets[0])
                return false;
        }

        return true;
    }

    private void merge(int[] sets, int rootLeft, int rootRight) {
        sets[rootRight] = rootLeft;
    }

    private int findRoot(int[] sets, int node) {
        int root = node;

        while(sets[root] != root) {
            root = sets[root];
        }
        return root;
    }


    class Edge {
        int left;
        int right;

        public Edge(int left, int right) {
            this.left = left;
            this.right = right;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }
    }
}

package utils.dataStructures.disjointSet;

public class DisjointSet {
    /**
     * Detect Cycle in an Undirected Graph
     */

    public Edge edge[];
    private int v;
    private int e;

    class Edge {
        int src, dest;
    }

    public DisjointSet(int v, int e) {
        this.v = v;
        this.e = e;
        edge = new Edge[e];
        for (int i = 0; i < e; ++i)
            edge[i] = new Edge();
    }

    public boolean isCycle() {
        int parent[] = new int[v];
        for (int i = 0; i < v; i++) {
            parent[i] = -1;
        }

        for (Edge e : edge) {
            int parentSource = find(parent, e.src);
            int parentDest = find(parent, e.dest);

            if (parentSource == parentDest) {
                return true;
            } else {
                union(parent, parentSource, parentDest);
            }
        }

        return false;
    }

    private int find(int[] parent, int node) {
        int pivot = node;
        while (parent[pivot] != -1) {
            pivot = parent[pivot];
        }
        return pivot;
    }

    private void union(int[] parent, int branch1, int branch2) {
        int x = find(parent, branch1);
        int y = find(parent, branch2);

        parent[x] = y;

    }

}

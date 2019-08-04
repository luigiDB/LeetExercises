package utils.dataStructures;

import org.junit.Assert;
import org.junit.Test;

public class DisjointSetTest {

    @Test
    public void CycleDetectionTest() {
        int V = 3, E = 3;
        DisjointSet graph = new DisjointSet(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        Assert.assertTrue(graph.isCycle());
    }

    @Test
    public void NoCycleDetectionTest() {
        int V = 3, E = 2;
        DisjointSet graph = new DisjointSet(V, E);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        Assert.assertFalse(graph.isCycle());
    }


}
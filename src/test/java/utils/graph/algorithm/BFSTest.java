package utils.graph.algorithm;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class BFSTest {

    private BFS<Character> bfs;

    @Before
    public void setUp() throws Exception {
        int[][] graph = new int[][] {
                {0, 10, 10, 0,  0,  0},
                {0, 0,  0,  20, 5,  0},
                {0, 0,  0,  0,  0,  0},
                {0, 0,  0,  0,  0,  0},
                {0, 0,  0,  7,  0,  10},
                {0, 0,  0,  2,  0,  0}};
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        bfs = new BFS(graph, nodes);
    }

    @Test
    public void testBFSOnALeaf() {
        List<Character> visit = bfs.visit('C');
        Assert.assertEquals(Arrays.asList('C'), visit);
    }

    @Test
    public void testBFSOnAMiddleNode() {
        List<Character> visit = bfs.visit('E');
        Assert.assertEquals(Arrays.asList('E', 'D', 'F'), visit);
    }

    @Test
    public void testBFSOnFullGraph() {
        List<Character> visit = bfs.visit('A');
        Assert.assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'), visit);
    }
}
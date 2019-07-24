package utils.graph.visit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.graph.visit.DFS;

import java.util.Arrays;
import java.util.List;

public class DFSTest {

    private DFS<Character> dfs;

    @Before
    public void setUp() {
        int[][] graph = new int[][] {
                {0, 10, 10, 0,  0,  0},
                {0, 0,  0,  20, 5,  0},
                {0, 0,  0,  0,  0,  0},
                {0, 0,  0,  0,  0,  0},
                {0, 0,  0,  7,  0,  10},
                {0, 0,  0,  2,  0,  0}};
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        dfs = new DFS(graph, nodes);
    }

    @Test
    public void testDFSOnALeaf() {
        List<Character> visit = dfs.visit('C');
        Assert.assertEquals(Arrays.asList('C'), visit);
    }

    @Test
    public void testDFSOnAMiddleNode() {
        List<Character> visit = dfs.visit('E');
        Assert.assertEquals(Arrays.asList('E', 'F', 'D'), visit);
    }

    @Test
    public void testDFSOnFullGraph() {
        List<Character> visit = dfs.visit('A');
        Assert.assertEquals(Arrays.asList('A', 'C', 'B', 'E', 'F', 'D'), visit);
    }
}
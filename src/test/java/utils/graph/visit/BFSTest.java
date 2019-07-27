package utils.graph.visit;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.*;

public class BFSTest {

    private BFS<Character> bfs;

    private void initMatrixGraph() {
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
    public void testMatrixBFSOnALeaf() {
        initMatrixGraph();
        List<Character> visit = bfs.visit('C');
        Assert.assertEquals(Arrays.asList('C'), visit);
    }

    @Test
    public void testMatrixBFSOnAMiddleNode() {
        initMatrixGraph();
        List<Character> visit = bfs.visit('E');
        Assert.assertEquals(Arrays.asList('E', 'D', 'F'), visit);
    }

    @Test
    public void testMatrixBFSOnFullGraph() {
        initMatrixGraph();
        List<Character> visit = bfs.visit('A');
        Assert.assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'), visit);
    }

    private void initAdjacencyGraph() {
        Map<Character, List<IEdge<Character>>> mapGraph = new HashMap<>();
        mapGraph.put('A', new LinkedList<>(Arrays.asList(
                new WeightedDirectedEdge<>('A', 'B', 10),
                new WeightedDirectedEdge<>('A', 'C', 10)
        )));
        mapGraph.put('B', new LinkedList<>(Arrays.asList(
                new WeightedDirectedEdge<>('B', 'D', 20),
                new WeightedDirectedEdge<>('B', 'E', 5)
        )));
        mapGraph.put('E', new LinkedList<>(Arrays.asList(
                new WeightedDirectedEdge<>('E', 'D', 7),
                new WeightedDirectedEdge<>('E', 'F', 10)
        )));
        mapGraph.put('F', new LinkedList<>(Arrays.asList(
                new WeightedDirectedEdge<>('F', 'D', 2)
        )));
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        bfs = new BFS(mapGraph, nodes);
    }

    @Test
    public void testAdjacencyBFSOnALeaf() {
        initAdjacencyGraph();
        List<Character> visit = bfs.visit('C');
        Assert.assertEquals(Arrays.asList('C'), visit);
    }

    @Test
    public void testAdjacencyBFSOnAMiddleNode() {
        initAdjacencyGraph();
        List<Character> visit = bfs.visit('E');
        Assert.assertEquals(Arrays.asList('E', 'D', 'F'), visit);
    }

    @Test
    public void testAdjacencyBFSOnFullGraph() {
        initAdjacencyGraph();
        List<Character> visit = bfs.visit('A');
        Assert.assertEquals(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'), visit);
    }
}
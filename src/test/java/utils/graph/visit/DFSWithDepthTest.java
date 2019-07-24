package utils.graph.visit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.*;

public class DFSWithDepthTest {

    private DFSWithDepth<Character> dfs;

    @Before
    public void setUp() {
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
        dfs = new DFSWithDepth(mapGraph, nodes);
    }

    @Test
    public void testDFSOnALeaf() {
        Assert.assertEquals(0, dfs.visit('C'));
    }

    @Test
    public void testDFSOnAMiddleNode() {
        Assert.assertEquals(2, dfs.visit('E'));
    }

    @Test
    public void testDFSOnFullGraph() {
        Assert.assertEquals(4, dfs.visit('A'));
    }

}
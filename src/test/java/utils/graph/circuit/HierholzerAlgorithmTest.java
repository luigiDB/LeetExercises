package utils.graph.circuit;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class HierholzerAlgorithmTest {

    @Test
    public void triangularTest() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(new UnweightedDirectedEdge<>(0, 1));
        edgeList.add(new UnweightedDirectedEdge<>(1, 2));
        edgeList.add(new UnweightedDirectedEdge<>(2, 0));
        Integer[] nodes = new Integer[]{0, 1, 2};
        HierholzerAlgorithm circuitDiscover = new HierholzerAlgorithm(edgeList, nodes);

        List<Integer> circuits = circuitDiscover.discoverCircuit(0);

        Assert.assertEquals(circuits, Arrays.asList(0, 1, 2));
    }

    @Test
    public void bowGraphTest() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(new UnweightedDirectedEdge<>(0, 1));
        edgeList.add(new UnweightedDirectedEdge<>(1, 2));
        edgeList.add(new UnweightedDirectedEdge<>(1, 3));
        edgeList.add(new UnweightedDirectedEdge<>(2, 0));
        edgeList.add(new UnweightedDirectedEdge<>(3, 4));
        edgeList.add(new UnweightedDirectedEdge<>(4, 1));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4};
        HierholzerAlgorithm circuitDiscover = new HierholzerAlgorithm(edgeList, nodes);

        List<Integer> circuits = circuitDiscover.discoverCircuit(0);

        Assert.assertEquals(circuits, Arrays.asList(0, 1, 3, 4, 1, 2));
    }

    @Test
    public void complexGraphTest() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(new UnweightedDirectedEdge<>(0, 1));
        edgeList.add(new UnweightedDirectedEdge<>(0, 6));
        edgeList.add(new UnweightedDirectedEdge<>(1, 2));
        edgeList.add(new UnweightedDirectedEdge<>(2, 0));
        edgeList.add(new UnweightedDirectedEdge<>(2, 3));
        edgeList.add(new UnweightedDirectedEdge<>(3, 4));
        edgeList.add(new UnweightedDirectedEdge<>(4, 2));
        edgeList.add(new UnweightedDirectedEdge<>(4, 5));
        edgeList.add(new UnweightedDirectedEdge<>(5, 0));
        edgeList.add(new UnweightedDirectedEdge<>(6, 4));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4, 5, 6};
        HierholzerAlgorithm circuitDiscover = new HierholzerAlgorithm(edgeList, nodes);

        List<Integer> circuits = circuitDiscover.discoverCircuit(0);

        Assert.assertEquals(circuits, Arrays.asList(0, 1, 2, 0, 6, 4, 2, 3, 4, 5));

    }

}
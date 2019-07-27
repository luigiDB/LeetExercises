package utils.graph.topologicalSort;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.LinkedList;
import java.util.List;

public class KhanAlgorithmTest {

    @Test
    public void testThatATopologicalOrderingIsFound() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 2));
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 1));
        edgeList.add(UnweightedDirectedEdge.createEdge(2, 3));
        edgeList.add(UnweightedDirectedEdge.createEdge(3, 1));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4, 5};

        KhanAlgorithm graphSorter = new KhanAlgorithm(edgeList, nodes);
        List<Integer> possibleSort = graphSorter.sort();

        Assert.assertTrue(possibleSort.indexOf(5)<possibleSort.indexOf(2));
        Assert.assertTrue(possibleSort.indexOf(5)<possibleSort.indexOf(0));
        Assert.assertTrue(possibleSort.indexOf(4)<possibleSort.indexOf(0));
        Assert.assertTrue(possibleSort.indexOf(4)<possibleSort.indexOf(1));
        Assert.assertTrue(possibleSort.indexOf(2)<possibleSort.indexOf(3));
        Assert.assertTrue(possibleSort.indexOf(3)<possibleSort.indexOf(1));

    }
}
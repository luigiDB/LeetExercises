package utils.graph.shortestPath;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathInDAGTest {

    @Test
    public void validateShortestPathDiscovery() {
        List<IWeightedEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(new WeightedDirectedEdge<>(0, 1, 5));
        edgeList.add(new WeightedDirectedEdge<>(0, 2, 3));
        edgeList.add(new WeightedDirectedEdge<>(1, 3, 6));
        edgeList.add(new WeightedDirectedEdge<>(1, 2, 2));
        edgeList.add(new WeightedDirectedEdge<>(2, 4, 4));
        edgeList.add(new WeightedDirectedEdge<>(2, 5, 2));
        edgeList.add(new WeightedDirectedEdge<>(2, 3, 7));
        edgeList.add(new WeightedDirectedEdge<>(3, 4, -1));
        edgeList.add(new WeightedDirectedEdge<>(4, 5, -2));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4, 5};
        ShortestPathInDAG shortestPathInDAG = new ShortestPathInDAG(edgeList, nodes);

        shortestPathInDAG.minDistanceFrom(1);

        Assert.assertEquals(Arrays.asList(Integer.MAX_VALUE, 0, 2, 6, 5, 3), shortestPathInDAG.getDistances());
        Assert.assertEquals(Arrays.asList(0, 0, 1, 1, 3, 4), shortestPathInDAG.getPreviouses());
    }

}
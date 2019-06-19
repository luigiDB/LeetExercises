package utils.graph.shortestPath;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BellmanFordTest {

    @Test
    public void validateBellmanFord() {
        List<IWeightedEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(new WeightedDirectedEdge<>(0, 1, -1));
        edgeList.add(new WeightedDirectedEdge<>(0, 2, 4));
        edgeList.add(new WeightedDirectedEdge<>(1, 2, 3));
        edgeList.add(new WeightedDirectedEdge<>(1, 3, 2));
        edgeList.add(new WeightedDirectedEdge<>(1, 4, 2));
        edgeList.add(new WeightedDirectedEdge<>(3, 2, 5));
        edgeList.add(new WeightedDirectedEdge<>(3, 1, 1));
        edgeList.add(new WeightedDirectedEdge<>(4, 3, -3));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4};
        BellmanFord bellmanFord = new BellmanFord(edgeList, nodes);

        bellmanFord.bellmanFordDistance(0);

        Assert.assertEquals(Arrays.asList(0, -1, 2, -2, 1), bellmanFord.getDistances());
        Assert.assertEquals(Arrays.asList(0, 0, 1, 4, 1), bellmanFord.getPreviouses());
    }

}
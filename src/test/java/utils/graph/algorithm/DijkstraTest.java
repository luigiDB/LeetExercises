package utils.graph.algorithm;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class DijkstraTest {

    @Test
    public void validateDjikstra() {
        List<IWeightedEdge<Character>> edgeList = new LinkedList<>();
        edgeList.add(new WeightedDirectedEdge<>('A', 'B', 10));
        edgeList.add(new WeightedDirectedEdge<>('A', 'C', 10));
        edgeList.add(new WeightedDirectedEdge<>('B', 'D', 20));
        edgeList.add(new WeightedDirectedEdge<>('B', 'E', 5));
        edgeList.add(new WeightedDirectedEdge<>('E', 'D', 7));
        edgeList.add(new WeightedDirectedEdge<>('E', 'F', 10));
        edgeList.add(new WeightedDirectedEdge<>('F', 'D', 2));
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        Dijkstra dijkstra = new Dijkstra(edgeList, nodes);

        dijkstra.dijkstraDistance('A');

        Assert.assertEquals(Arrays.asList(0,    10, 10, 22, 15, 25), dijkstra.getDistances());
        Assert.assertEquals(Arrays.asList(0,    0,  0,  4,  1,  4), dijkstra.getPreviouses());
    }
}
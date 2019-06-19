package utils.graph.algorithm;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TopologicalSortTest {

    @Test
    public void validateTopologicalSort() {
        List<IWeightedEdge<Character>> edgeList = new LinkedList<>();
        edgeList.add(new WeightedDirectedEdge<>('A', 'B', 10));
        edgeList.add(new WeightedDirectedEdge<>('A', 'C', 10));
        edgeList.add(new WeightedDirectedEdge<>('B', 'D', 20));
        edgeList.add(new WeightedDirectedEdge<>('B', 'E', 5));
        edgeList.add(new WeightedDirectedEdge<>('E', 'D', 7));
        edgeList.add(new WeightedDirectedEdge<>('E', 'F', 10));
        edgeList.add(new WeightedDirectedEdge<>('F', 'D', 2));
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};

        TopologicalSort topologicalSort = new TopologicalSort(edgeList, nodes);
        Stack sort = topologicalSort.sort();
        Collections.reverse(sort);

        Assert.assertTrue(sort.indexOf('A')<sort.indexOf('B'));
        Assert.assertTrue(sort.indexOf('A')<sort.indexOf('C'));
        Assert.assertTrue(sort.indexOf('B')<sort.indexOf('D'));
        Assert.assertTrue(sort.indexOf('B')<sort.indexOf('E'));
        Assert.assertTrue(sort.indexOf('E')<sort.indexOf('D'));
        Assert.assertTrue(sort.indexOf('E')<sort.indexOf('F'));
        Assert.assertTrue(sort.indexOf('F')<sort.indexOf('D'));
    }

}
package utils.graph.spanningTree;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.undirected.WeightedUndirectedEdge;

import java.util.LinkedList;
import java.util.List;

public class PrimTest {
    @Test
    public void validatePrim() {
        List<IWeightedEdge<Character>> edgeList = new LinkedList<>();
        List<IWeightedEdge<Character>> expectedList = new LinkedList<>();

        WeightedUndirectedEdge<Character> tmp = new WeightedUndirectedEdge<>('A', 'B', 11);
        edgeList.add(tmp);
        expectedList.add(tmp);
        tmp = new WeightedUndirectedEdge<>('A', 'C', 12);
        edgeList.add(tmp);
        expectedList.add(tmp);
        edgeList.add(new WeightedUndirectedEdge<>('B', 'D', 20));
        tmp = new WeightedUndirectedEdge<>('B', 'E', 5);
        edgeList.add(tmp);
        expectedList.add(tmp);
        tmp = new WeightedUndirectedEdge<>('E', 'D', 7);
        edgeList.add(tmp);
        expectedList.add(tmp);
        edgeList.add(new WeightedUndirectedEdge<>('E', 'F', 10));
        tmp = new WeightedUndirectedEdge<>('F', 'D', 2);
        edgeList.add(tmp);
        expectedList.add(tmp);
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        Prim<Character> prim = new Prim(edgeList, nodes);

        List<WeightedUndirectedEdge<Character>> mst = prim.mst();
        Assert.assertEquals(expectedList.size(), mst.size());
        for (WeightedUndirectedEdge<Character> edge : mst) {
            Assert.assertTrue(expectedList.contains(edge));
        }
    }
}
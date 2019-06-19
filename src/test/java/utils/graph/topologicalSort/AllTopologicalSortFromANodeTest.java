package utils.graph.topologicalSort;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class AllTopologicalSortFromANodeTest {

    @Test
    public void testThatAllTopologicalOrderingAreFound() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 2));
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 1));
        edgeList.add(UnweightedDirectedEdge.createEdge(2, 3));
        edgeList.add(UnweightedDirectedEdge.createEdge(3, 1));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4, 5};

        AllTopologicalSortFromANode graphSorter = new AllTopologicalSortFromANode(edgeList, nodes);
        List<String> possibleSort = graphSorter.sort(4);
        Assert.assertEquals(4, possibleSort.size());
        Assert.assertThat(possibleSort, containsInAnyOrder("450231", "452031", "452301", "452310"));
    }

}
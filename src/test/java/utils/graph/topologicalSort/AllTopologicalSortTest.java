package utils.graph.topologicalSort;

import org.junit.Assert;
import org.junit.Test;
import utils.graph.egde.IEdge;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class AllTopologicalSortTest {

    @Test
    public void name() {
        List<IEdge<Integer>> edgeList = new LinkedList<>();
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 2));
        edgeList.add(UnweightedDirectedEdge.createEdge(5, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 0));
        edgeList.add(UnweightedDirectedEdge.createEdge(4, 1));
        edgeList.add(UnweightedDirectedEdge.createEdge(2, 3));
        edgeList.add(UnweightedDirectedEdge.createEdge(3, 1));
        Integer[] nodes = new Integer[]{0, 1, 2, 3, 4, 5};

        AllTopologicalSort graphSorter = new AllTopologicalSort(edgeList, nodes);
        List<String> possibleSort = graphSorter.sort();
        Assert.assertThat(possibleSort, containsInAnyOrder("450231",
                "452031",
                "452301",
                "452310",
                "523401",
                "523410",
                "524031",
                "524301",
                "524310",
                "540231",
                "542031",
                "542301",
                "542310") );
    }

}
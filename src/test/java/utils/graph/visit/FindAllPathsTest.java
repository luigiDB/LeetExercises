package utils.graph.visit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.graph.egde.IWeightedEdge;
import utils.graph.egde.directed.WeightedDirectedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class FindAllPathsTest {

    private FindAllPaths<Character> findAllPaths;

    @Before
    public void setUp() throws Exception {
        List<IWeightedEdge<Character>> edgeList = new LinkedList<>();
        edgeList.add(new WeightedDirectedEdge<>('A', 'B', 1));
        edgeList.add(new WeightedDirectedEdge<>('A', 'C', 2));
        edgeList.add(new WeightedDirectedEdge<>('B', 'D', 3));
        edgeList.add(new WeightedDirectedEdge<>('B', 'C', 4));
        edgeList.add(new WeightedDirectedEdge<>('C', 'E', 5));
        edgeList.add(new WeightedDirectedEdge<>('E', 'F', 6));
        Character[] nodes = new Character[]{'A', 'B', 'C', 'D', 'E', 'F'};
        findAllPaths = new FindAllPaths(edgeList, nodes);
    }

    @Test
    public void findRecursivelyAnyPath() {
        List<Character> anyPath = findAllPaths.findRecursivelyAnyPath('A', 'F');
        Assert.assertThat(anyPath, contains('A', 'B', 'C', 'E', 'F'));
    }

    @Test
    public void findRecursivelyAllPaths() {
        Set<List<Character>> anyPath = findAllPaths.findRecursivelyAllPaths('A', 'F');
        Assert.assertThat(anyPath, containsInAnyOrder(
                Arrays.asList('A', 'B', 'C', 'E', 'F'),
                Arrays.asList('A', 'C', 'E', 'F')
        ));
    }

    @Test
    public void findIterativelyAnyPath() {
        List<Character> anyPath = findAllPaths.findIterativelyAnyPath('A', 'F');
        Assert.assertThat(anyPath, contains('A', 'C', 'E', 'F'));
    }
}
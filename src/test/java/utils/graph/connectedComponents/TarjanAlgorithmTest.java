package utils.graph.connectedComponents;

import junit.framework.TestCase;
import org.junit.Assert;
import utils.graph.egde.directed.UnweightedDirectedEdge;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class TarjanAlgorithmTest extends TestCase {

    public void testSimpleCase() {
        Map<Integer, List<UnweightedDirectedEdge<Integer>>> graph = Map.of(
                0, List.of(
                        UnweightedDirectedEdge.createEdge(0, 1)
                ),
                1, List.of(
                        UnweightedDirectedEdge.createEdge(1, 2),
                        UnweightedDirectedEdge.createEdge(1, 3),
                        UnweightedDirectedEdge.createEdge(1, 5)
                ),
                2, List.of(
                        UnweightedDirectedEdge.createEdge(2, 0),
                        UnweightedDirectedEdge.createEdge(2, 3)
                ),
                3, List.of(
                        UnweightedDirectedEdge.createEdge(3, 4)
                ),
                4, List.of(
                        UnweightedDirectedEdge.createEdge(4, 3)
                ),
                5, List.of(
                        UnweightedDirectedEdge.createEdge(5, 4)
                )
        );

        TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(graph);
        List<Set<Integer>> stronglyConnectedComponents = tarjanAlgorithm.findStronglyConnectedComponents();
        Assert.assertThat(stronglyConnectedComponents, containsInAnyOrder(
                Set.of(0, 1, 2),
                Set.of(3, 4),
                Set.of(5)
        ));

        List<UnweightedDirectedEdge<Integer>> criticalConnection = tarjanAlgorithm.findCriticalConnection();
        Assert.assertThat(criticalConnection, containsInAnyOrder(
                UnweightedDirectedEdge.createEdge(1, 3),
                UnweightedDirectedEdge.createEdge(1, 5),
                UnweightedDirectedEdge.createEdge(2, 3),
                UnweightedDirectedEdge.createEdge(5, 4)
        ));
    }

    public void testNaiveCase() {
        Map<Integer, List<UnweightedDirectedEdge<Integer>>> graph = Map.of(
                0, List.of(
                        UnweightedDirectedEdge.createEdge(0, 1)
                ),
                1, List.of(
                        UnweightedDirectedEdge.createEdge(1, 2),
                        UnweightedDirectedEdge.createEdge(1, 3)
                ),
                2, List.of(
                        UnweightedDirectedEdge.createEdge(2, 0)
                ),
                3, List.of()
        );

        TarjanAlgorithm tarjanAlgorithm = new TarjanAlgorithm(graph);
        List<Set<Integer>> stronglyConnectedComponents = tarjanAlgorithm.findStronglyConnectedComponents();
        Assert.assertThat(stronglyConnectedComponents, containsInAnyOrder(
                Set.of(0, 1, 2),
                Set.of(3)
        ));

        List<UnweightedDirectedEdge<Integer>> criticalConnection = tarjanAlgorithm.findCriticalConnection();
        Assert.assertThat(criticalConnection, containsInAnyOrder(
                UnweightedDirectedEdge.createEdge(1, 3)
        ));
    }
}
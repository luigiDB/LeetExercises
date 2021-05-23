package utils.tree.visit;

import junit.framework.TestCase;
import utils.tree.Node;

import java.util.Arrays;
import java.util.List;

public class ThreeVisitAlgorithmsTest extends TestCase {

    private Node<Integer> root;

    @Override
    public void setUp() throws Exception {
        root = Node.of(1,
                Node.of(2,
                        Node.of(4),
                        Node.of(5)),
                Node.of(3));
    }

    public void testDFS() {
        List<Integer> expected = Arrays.asList(1, 2, 4, 5, 3);
        assertEquals(expected, ThreeVisitAlgorithms.recursiveDfs(root));
        assertEquals(expected, ThreeVisitAlgorithms.recursiveDfs2(root));
        assertEquals(expected, ThreeVisitAlgorithms.iterativeDfs(root));
    }

    public void testBFS() {
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, ThreeVisitAlgorithms.iterativeBfs(root));
    }

    public void testClassicalVisitVariations() {
        assertEquals(Arrays.asList(4,2,5,1,3), ThreeVisitAlgorithms.inOrderWithStack(root));
    }
}
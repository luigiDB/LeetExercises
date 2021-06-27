package leetfree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Given a root of an N-ary tree, you need to compute the length of the diameter of the tree.
 * The diameter of an N-ary tree is the length of the longest path between any two nodes in the tree. This path may or
 * may not pass through the root.
 * (Nary-Tree input serialization is represented in their level order traversal, each group of children is separated
 * by the null value.)
 */
public class _1522_DiameterOfN_AryTree {

    @Test
    public void a() {
        Node root = new Node(
                1,
                List.of(
                        new Node(3,
                                List.of(
                                        new Node(5),
                                        new Node(6)
                                )
                        ),
                        new Node(2),
                        new Node(4)
                )
        );
        assertEquals(3, diameter(root));
    }

    @Test
    public void b() {
        Node root = new Node(
                1,
                List.of(
                        new Node(2,
                                List.of(
                                        new Node(3,
                                                List.of(new Node(5))
                                        ),
                                        new Node(4,
                                                List.of(new Node(6))
                                        )
                                )
                        )
                )
        );
        assertEquals(4, diameter(root));
    }

    int diameter = 0;

    public int diameter(Node root) {
        diameter = 0;
        recursiveDiameter(root);
        return diameter;
    }

    private int recursiveDiameter(Node root) {
        List<Integer> sons = new ArrayList<>();
        for (Node next : root.children) {
            sons.add(recursiveDiameter(next));
        }
        Collections.sort(sons);

        if (sons.size() == 0)
            return 1;

        if (sons.size() > 1)
            diameter = Math.max(
                    diameter,
                    sons.get(sons.size() - 1) + sons.get(sons.size() - 2)
            );

        return sons.get(sons.size() - 1) + 1;
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }
}

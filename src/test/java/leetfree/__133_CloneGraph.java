package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * Given a reference of a node in a connected undirected graph, return a deep copy (clone) of the graph. Each node in
 * the graph contains a val (int) and a list (List[Node]) of its neighbors.
 * <p>
 * <p>
 * <p>
 * Example:
 * <p>
 * <p>
 * <p>
 * Input:
 * {"$id":"1","neighbors":[{"$id":"2","neighbors":[{"$ref":"1"},{"$id":"3","neighbors":[{"$ref":"2"},{"$id":"4","neighbors":[{"$ref":"3"},{"$ref":"1"}],"val":4}],"val":3}],"val":2},{"$ref":"4"}],"val":1}
 * <p>
 * Explanation:
 * Node 1's value is 1, and it has two neighbors: Node 2 and 4.
 * Node 2's value is 2, and it has two neighbors: Node 1 and 3.
 * Node 3's value is 3, and it has two neighbors: Node 2 and 4.
 * Node 4's value is 4, and it has two neighbors: Node 1 and 3.
 * <p>
 * <p>
 * Note:
 * <p>
 * The number of nodes will be between 1 and 100.
 * The undirected graph is a simple graph, which means no repeated edges and no self-loops in the graph.
 * Since the graph is undirected, if node p has node q as neighbor, then node q must have node p as neighbor too.
 * You must return the copy of the given node as a reference to the cloned graph.
 */
public class __133_CloneGraph {

    @Test
    public void treeGraph() {
        Node a = new Node(1);
        Node b = new Node(2);
        Node c = new Node(3);
        a.neigbours.addAll(Arrays.asList(b, c));
        b.neigbours.addAll(Arrays.asList(a));
        c.neigbours.addAll(Arrays.asList(a));

        Node clone = cloneGraph(a);

        Assert.assertEquals(1, clone.id);
        Assert.assertEquals(2, clone.neigbours.size());

        Node firstNeighbor = clone.neigbours.get(0);
        Node secondNeighbor = clone.neigbours.get(1);
        Assert.assertTrue(firstNeighbor.id == 2 || firstNeighbor.id == 3);
        Assert.assertTrue(secondNeighbor.id == 2 || secondNeighbor.id == 3);

        Assert.assertTrue(firstNeighbor.id == 2 || firstNeighbor.id == 3);
        Assert.assertEquals(1, firstNeighbor.neigbours.size());
        Assert.assertTrue(firstNeighbor.neigbours.get(0).id == 1);

        Assert.assertTrue(secondNeighbor.id == 2 || secondNeighbor.id == 3);
        Assert.assertEquals(1, secondNeighbor.neigbours.size());
        Assert.assertTrue(secondNeighbor.neigbours.get(0).id == 1);
    }

    private Node cloneGraph(Node root) {

        Map<Integer, Node> nodeMap = new HashMap<>();
        Node clonedRoot = new Node(root);
        nodeMap.put(root.id, clonedRoot);

        //BFS
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node poll = queue.poll();
            if (!visited.contains(poll)) {
                visited.add(poll);
                nodeMap.putIfAbsent(poll.id, new Node(poll));

                for (Node n : poll.neigbours) {
                    Node linkNode = getNode(nodeMap, n);
                    if (!nodeMap.get(poll.id).neigbours.contains(linkNode))
                        nodeMap.get(poll.id).neigbours.add(linkNode);
                    if (!linkNode.neigbours.contains(nodeMap.get(poll.id)))
                        linkNode.neigbours.add(nodeMap.get(poll.id));
                }

                queue.addAll(poll.neigbours);
            }
        }

        return clonedRoot;
    }

    private Node getNode(Map<Integer, Node> nodeMap, Node n) {
        Node linkNode = null;
        if (nodeMap.containsKey(n.id)) {
            linkNode = nodeMap.get(n.id);
        } else {
            linkNode = new Node(n);
            nodeMap.put(n.id, linkNode);
        }
        return linkNode;
    }

    @Test
    public void cycleGraph() {

    }

    class Node {
        int id;
        List<Node> neigbours = new LinkedList<>();

        public Node(int id) {
            this.id = id;
        }

        public Node(Node n) {
            this.id = n.id;
        }
    }
}

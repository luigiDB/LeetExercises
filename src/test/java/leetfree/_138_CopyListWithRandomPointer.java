package leetfree;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * A linked list of length n is given such that each node contains an additional random pointer, which could point to
 * any node in the list, or null.
 * Construct a deep copy of the list. The deep copy should consist of exactly n brand new nodes, where each new node
 * has its value set to the value of its corresponding original node. Both the next and random pointer of the new nodes
 * should point to new nodes in the copied list such that the pointers in the original list and copied list represent
 * the same list state. None of the pointers in the new list should point to nodes in the original list.
 * For example, if there are two nodes X and Y in the original list, where X.random --> Y, then for the corresponding
 * two nodes x and y in the copied list, x.random --> y.
 * Return the head of the copied linked list.
 * The linked list is represented in the input/output as a list of n nodes. Each node is represented as a pair
 * of [val, random_index] where:
 * val: an integer representing Node.val
 * random_index: the index of the node (range from 0 to n-1) that the random pointer points to, or null if it does not
 * point to any node.
 * Your code will only be given the head of the original linked list.
 */
public class _138_CopyListWithRandomPointer {

    @Test
    public void a() {
        Node _7 = new Node(7);
        Node _13 = new Node(13);
        Node _11 = new Node(11);
        Node _10 = new Node(10);
        Node _1 = new Node(1);
        _7.set(_13, null);
        _13.set(_11, _7);
        _11.set(_10, _1);
        _10.set(_1, _11);
        _1.set(null, _7);
        Node copy = copyRandomList(_7);
    }

    public Node copyRandomList(Node head) {
        Map<Node, Node> copyMap = new HashMap<>();

        Node current = head;
        while (current != null) {
            Node copyCurrent = getCopy(copyMap, current);

            copyCurrent.next = getCopy(copyMap, current.next);
            copyCurrent.random = getCopy(copyMap, current.random);

            current = current.next;
        }

        return copyMap.get(head);
    }

    private Node getCopy(Map<Node, Node> copyMap, Node current) {
        if(current == null)
            return null;

        Node copyCurrent = copyMap.get(current);
        if(copyCurrent == null) {
            copyCurrent = new Node(current.val);
            copyMap.put(current, copyCurrent);
        }
        return copyCurrent;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }

        public void set(Node next, Node random) {
            this.next = next;
            this.random = random;
        }
    }
}

package leetfree;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class _426_ConvertBinarySearchTreeToSortedDoublyLinkedList {

    @Test
    public void a() {
        Node root = new Node(4,
                new Node(2,
                        new Node(1),
                        new Node(3)
                ),
                new Node(5)
        );

        Node listTree = treeToDoublyList(root);
        assertEquals("1,2,3,4,5,1", printList(listTree));
    }

    @Test
    public void b() {
        Node root = new Node(2,
                new Node(1),
                new Node(3)
        );

        Node listTree = treeToDoublyList(root);
        assertEquals("1,2,3,1", printList(listTree));
    }

    @Test
    public void c() {
        Node root = null;

        Node listTree = treeToDoublyList(root);
        assertEquals("", printList(listTree));
    }

    @Test
    public void d() {
        Node root = new Node(1);

        Node listTree = treeToDoublyList(root);
        assertEquals("1,1", printList(listTree));
    }

    private String printList(Node listTree) {
        if (listTree == null)
            return "";
        StringBuilder sb = new StringBuilder();
        int times = 0;
        for (Node current = listTree; times < 2; current = current.right) {
            sb.append(current.val);
            sb.append(",");
            if (current == listTree) times++;
        }
        if (sb.length() > 0)
            sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    public Node treeToDoublyList(Node root) {
        if (root == null)
            return null;

        Node head = null, tail = null;
        Stack<Node> visit = new Stack<>();
        Node current = root;
        while (current != null || !visit.isEmpty()) {
            while (current != null) {
                visit.push(current);
                current = current.left;
            }
            current = visit.pop();

            if (head == null) {
                head = current;
                tail = current;
            } else {
                tail.right = current;
                current.left = tail;
                tail = tail.right;
            }

            current = current.right;
        }

        tail.right = head;
        head.left = tail;

        return head;
    }

    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }

    ;
}

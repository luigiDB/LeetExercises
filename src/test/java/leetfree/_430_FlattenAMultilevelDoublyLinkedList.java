package leetfree;

import org.junit.Assert;
import org.junit.Test;

import javax.swing.plaf.ColorUIResource;
import java.util.*;

/*
You are given a doubly linked list which in addition to the next and previous pointers, it could have a child pointer,
which may or may not point to a separate doubly linked list. These child lists may have one or more children of their
own, and so on, to produce a multilevel data structure, as shown in the example below.
Flatten the list so that all the nodes appear in a single-level, doubly linked list. You are given the head of the
first level of the list.

Example 1:
Input: head = [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]
Output: [1,2,3,7,8,11,12,9,10,4,5,6]

Example 2:

Input: head = [1,2,null,3]
Output: [1,3,2]
Explanation:
The input multilevel linked list is as follows:

  1---2---NULL
  |
  3---NULL

Example 3:
Input: head = []
Output: []


How multilevel linked list is represented in test case:
We use the multilevel linked list from Example 1 above:
 1---2---3---4---5---6--NULL
         |
         7---8---9---10--NULL
             |
             11--12--NULL
The serialization of each level is as follows:
[1,2,3,4,5,6,null]
[7,8,9,10,null]
[11,12,null]
To serialize all levels together we will add nulls in each level to signify no node connects to the upper node of the
previous level. The serialization becomes:
[1,2,3,4,5,6,null]
[null,null,7,8,9,10,null]
[null,11,12,null]
Merging the serialization of each level and removing trailing nulls we obtain:

[1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12]

Constraints:
Number of Nodes will not exceed 1000.
1 <= Node.val <= 10^5
 */
public class _430_FlattenAMultilevelDoublyLinkedList {

    @Test
    public void givenTest1() {
        Node head = buildNodeList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        addSonList(new ArrayList<>(Arrays.asList(7, 8, 9, 10)), Arrays.asList(2), head);
        addSonList(new ArrayList<>(Arrays.asList(11, 12)), Arrays.asList(2, 1), head);
        Node flatten = flatten(head);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6), nextFollowingList(flatten));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 7, 8, 11, 12, 9, 10, 4, 5, 6), precFollowingList(flatten));
    }

    @Test
    public void testEmptyInput() {
        Node head = null;
        Assert.assertTrue(nextFollowingList(flatten(head)).isEmpty());
    }

    @Test
    public void testSubsequentChild() {
        Node head = buildNodeList(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
        addSonList(new ArrayList<>(Arrays.asList(7, 8, 9, 10)), Arrays.asList(2), head);
        addSonList(new ArrayList<>(Arrays.asList(11, 12)), Arrays.asList(3), head);
        Node flatten = flatten(head);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 7, 8, 9, 10, 4, 11, 12, 5, 6), nextFollowingList(flatten));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 7, 8, 9, 10, 4, 11, 12, 5, 6), precFollowingList(flatten));
    }

    @Test
    public void testChildRecursion() {
        Node head = buildNodeList(new ArrayList<>(Arrays.asList(1)));
        addSonList(new ArrayList<>(Arrays.asList(2)), Arrays.asList(0), head);
        addSonList(new ArrayList<>(Arrays.asList(3)), Arrays.asList(0, 0), head);
        addSonList(new ArrayList<>(Arrays.asList(4)), Arrays.asList(0, 0, 0), head);
        Node flatten = flatten(head);
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), nextFollowingList(flatten));
        Assert.assertEquals(Arrays.asList(1, 2, 3, 4), precFollowingList(flatten));
    }

    public Node flatten(Node head) {
        if (head == null)
            return null;
        Stack<Node> pendingTails = new Stack<>();
        Node cursor = head;
        while (cursor.next != null || cursor.child != null || pendingTails.size() != 0) {
            if (cursor.child != null) {
                if (cursor.next != null)
                    pendingTails.push(cursor.next);
                cursor.next = cursor.child;
                cursor.child = null;
                cursor.next.prev = cursor;
            } else if (cursor.next != null) {
                cursor = cursor.next;
            } else {
                cursor.next = pendingTails.pop();
                cursor.next.prev = cursor;
            }
        }
        return head;
    }

    private Node buildNodeList(List<Integer> sequence) {
        if (sequence.size() == 0)
            return null;
        Node n = new Node();
        n.val = sequence.remove(0);
        n.next = buildNodeList(sequence);
        if (n.next != null)
            n.next.prev = n;
        return n;
    }

    private void addSonList(List<Integer> sequence, List<Integer> position, Node head) {
        Node destination = head;
        for (int index = 0; index < position.size(); index++) {
            for (int i = 0; i < position.get(index); i++) {
                destination = destination.next;
            }
            if (index != position.size() - 1)
                destination = destination.child;
        }
        destination.child = buildNodeList(sequence);
    }

    private List<Integer> nextFollowingList(Node head) {
        List<Integer> valueList = new ArrayList<>();
        for (Node current = head; current != null; current = current.next) {
            valueList.add(current.val);
        }
        return valueList;
    }

    private List<Integer> precFollowingList(Node head) {
        LinkedList<Integer> valueList = new LinkedList<>();
        Node last = head;
        for (Node current = head; current != null; current = current.next) {
            last = current;
        }
        for (Node current = last; current != null; current = current.prev) {
            valueList.addFirst(current.val);
        }
        return valueList;
    }

    private class Node {

        public int val;
        public Node prev;
        public Node next;
        public Node child;
    }
}

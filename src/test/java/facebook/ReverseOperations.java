package facebook;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertTrue;

/**
 * You are given a singly-linked list that contains N integers. A subpart of the list is a contiguous set of even
 * elements, bordered either by either end of the list or an odd element. For example, if the list is
 * [1, 2, 8, 9, 12, 16], the subparts of the list are [2, 8] and [12, 16].
 * Then, for each subpart, the order of the elements is reversed. In the example, this would result in the new list,
 * [1, 8, 2, 9, 16, 12].
 * The goal of this question is: given a resulting list, determine the original order of the elements.
 */
public class ReverseOperations {

    @Test
    public void a() {
        int[] arr_1 = {1, 2, 8, 9, 12, 16};
        int[] expected1 = {1, 8, 2, 9, 16, 12};
        Node head_1 = createLinkedList(arr_1);
        Node expected_1 = createLinkedList(expected1);
        Node output_1 = reverse(head_1);
        assertTrue(check(expected_1, output_1));
    }

    @Test
    public void b() {
        int[] arr_2 = {2, 18, 24, 3, 5, 7, 9, 6, 12};
        int[] expected2 = {24, 18, 2, 3, 5, 7, 9, 12, 6};
        Node head_2 = createLinkedList(arr_2);
        Node expected_2 = createLinkedList(expected2);
        Node output_2 = reverse(head_2);
        assertTrue(check(expected_2, output_2));
    }

    class Node {
        int data;
        Node next;
        Node(int x) {
            data = x;
            next = null;
        }
    }

    Node reverse(Node head) {
        // Write your code here
        Node current = head;

        Stack<Node> stack = new Stack<>();
        Node last = null;
        while(current != null) {
            if(current.data %2 != 0) {
                if(!stack.isEmpty()) {
                    while(!stack.isEmpty()) {
                        if(last != null) {
                            last.next = stack.pop();
                            last = last.next;
                        } else {
                            last = stack.pop();
                            head = last;
                        }
                    }
                    last.next = current;
                }
                last = current;
            } else {
                stack.push(current);
            }
            current = current.next;
        }

        while(!stack.isEmpty()) {
            last.next = stack.pop();
            last = last.next;
        }
        last.next = null;

        return head;
    }


    boolean check(Node expectedHead, Node outputHead) {
        boolean result = true;
        while (expectedHead != null && outputHead != null) {
            result &= (expectedHead.data == outputHead.data);
            expectedHead = expectedHead.next;
            outputHead = outputHead.next;
        }
        if (!(expectedHead == null && outputHead == null)) result = false;
        return result;
    }
    Node createLinkedList(int[] arr) {
        Node head = null;
        Node tempHead = head;
        for (int v : arr) {
            if (head == null) {
                head = new Node(v);
                tempHead = head;
            } else {
                head.next = new Node(v);
                head = head.next;
            }
        }
        return tempHead;
    }
}

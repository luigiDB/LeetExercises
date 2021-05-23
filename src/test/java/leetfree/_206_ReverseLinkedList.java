package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
Reverse a singly linked list.
Example:
Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:
A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class _206_ReverseLinkedList {

    @Test
    public void test() {
        ListNode head = new ListNode(0);
        int N = 4;
        ListNode current = head;
        for (int i = 1; i < N; i++) {
            current.next = new ListNode(i);
            current = current.next;
        }

        assertEquals("321", toString(reverseList(head)));
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }



    public String toString(ListNode node) {
        StringBuilder sb = new StringBuilder();
        ListNode tmp = node;
        while(tmp.next != null){
            sb.append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString();
    }

    public class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

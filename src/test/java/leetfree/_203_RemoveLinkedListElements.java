package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Given the head of a linked list and an integer val, remove all the nodes of the linked list that has Node.val == val,
 * and return the new head.
 */
public class _203_RemoveLinkedListElements {

    @Test
    public void a() {
        ListNode root = new ListNode(6,
                new ListNode(2,
                        new ListNode(6,
                                new ListNode(3,
                                        new ListNode(4,
                                                new ListNode(5,
                                                        new ListNode(6)
                                                )
                                        )
                                )
                        )
                )
        );
        ListNode res = removeElements(root, 6);
        assertNotNull(res);
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode returningHead = head;
        while (returningHead.val == val) {
            returningHead = returningHead.next;
        }

        ListNode current = returningHead;
        while (current != null) {
            if (current.next != null && current.next.val == val) {
                current.next = current.next.next;
            }
            current = current.next;
        }

        return returningHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

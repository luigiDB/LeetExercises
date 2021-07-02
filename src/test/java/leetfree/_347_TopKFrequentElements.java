package leetfree;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;

/**
 * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in
 * any order.
 */
public class _347_TopKFrequentElements {

    private Solution solution;

    @Before
    public void setUp() throws Exception {
        solution = new Solution();
    }

    @Test
    public void a() {
        assertArrayEquals(
                new int[]{1, 2},
                solution.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)
        );
    }

    @Test
    public void b() {
        assertArrayEquals(
                new int[]{1},
                solution.topKFrequent(new int[]{1}, 1)
        );
    }

    @Test
    public void c() {
        assertArrayEquals(
                new int[]{1, 2, 3},
                solution.topKFrequent(new int[]{1, 1, 2, 2, 2, 1, 1, 3, 2, 4, 3}, 3)
        );
    }

    class EasierSolution {
        //With a heap of size k
        public int[] topKFrequent(int[] nums, int k) {
            if (k == nums.length) {
                return nums;
            }

            Map<Integer, Integer> count = new HashMap();
            for (int n : nums) {
                count.put(n, count.getOrDefault(n, 0) + 1);
            }

            Queue<Integer> heap = new PriorityQueue<>(
                    (n1, n2) -> count.get(n1) - count.get(n2));

            for (int n : count.keySet()) {
                heap.add(n);
                if (heap.size() > k) heap.poll();
            }

            int[] top = new int[k];
            for (int i = k - 1; i >= 0; --i) {
                top[i] = heap.poll();
            }
            return top;
        }
    }

    class Solution {
        Map<Integer, Node> map = new HashMap<>();
        Node head = null;
        Node tail = null;

        //Complex solution using a double linked list with O(N) complexity
        public int[] topKFrequent(int[] nums, int k) {

            for (int i : nums) {
                Node node = map.getOrDefault(i, new Node(i));
                node.frequency++;

                if (node.frequency == 1) {
                    //new number
                    map.put(i, node);

                    //Insert at tail
                    if (head == null) {
                        //empty list
                        head = node;
                    } else {
                        tail.next = node;
                        node.prev = tail;
                    }
                    tail = node;
                } else {
                    //Check if we can swap the node with the preceding if the frequency is higher
                    Node preceding = node.prev;
                    if (node == head)
                        continue;

                    if (preceding.frequency < node.frequency) {
                        if (preceding == head)
                            // update head pointer
                            head = node;

                        //update all pointer of node and preceding to swap the nodes
                        preceding.next = node.next;
                        if (node.next != null)
                            node.next.prev = preceding;
                        node.prev = preceding.prev;
                        node.next = preceding;
                        preceding.prev = node;
                    }
                }
            }

            int[] result = new int[k];
            Node current = head;
            for (int i = 0; i < k; i++) {
                result[i] = current.value;
                current = current.next;
            }
            return result;
        }

        private class Node {
            int value;
            int frequency;
            Node prev = null;
            Node next = null;

            public Node(int value) {
                this.value = value;
            }
        }
    }
}

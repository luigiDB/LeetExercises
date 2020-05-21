package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
Implementing the class MajorityChecker, which has the following API:
MajorityChecker(int[] arr) constructs an instance of MajorityChecker with the given array arr;
int query(int left, int right, int threshold) has arguments such that:
0 <= left <= right < arr.length representing a subarray of arr;
2 * threshold > right - left + 1, ie. the threshold is always a strict majority of the length of the subarray
Each query(...) returns the element in arr[left], arr[left+1], ..., arr[right] that occurs at least threshold times,
or -1 if no such element exists.

Constraints:
1 <= arr.length <= 20000
1 <= arr[i] <= 20000
For each query, 0 <= left <= right < len(arr)
For each query, 2 * threshold > right - left + 1
The number of queries is at most 10000
 */
public class _1157_OnlineMajorityElementInSubarray {

    @Test
    public void given() {
        MajorityChecker majorityChecker = new MajorityChecker(new int[]{1, 1, 2, 2, 1, 1});
        Assert.assertEquals(1, majorityChecker.query(0, 5, 4));
        Assert.assertEquals(-1, majorityChecker.query(0, 3, 3));
        Assert.assertEquals(2, majorityChecker.query(2, 3, 2));
    }

    private class MajorityChecker {

        private int[] input;
        private final Node root;
        private final int size;

        public MajorityChecker(int[] input) {
            this.input = input;
            size = input.length - 1;
            root = new Node();
            build(root, 0, size);
        }

        private void build(Node node, int l, int r) {
            if (l == r)
                node.init(input[l]);
            else {
                int mid = (l + r) / 2;
                node.left = new Node();
                node.right = new Node();
                build(node.left, l, mid);
                build(node.right, mid + 1, r);
                node.evaluateMode();
            }
        }

        public int query(int left, int right, int threshold) {
            Optional<Map.Entry<Integer, Integer>> any = query(root, 0, size, left, right)
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() >= threshold)
                    .findAny();
            if (any.isPresent())
                return any.get().getKey();
            else
                return -1;
        }

        private Map<Integer, Integer> query(Node node, int l, int r, int leftLimit, int rightLimit) {
            if (leftLimit <= l && r <= rightLimit)
                return node.map;
            Node accumulator = new Node();
            int mid = (l + r) / 2;
            if (leftLimit <= mid)
                accumulator.merge(query(node.left, l, mid, leftLimit, rightLimit));
            if (rightLimit > mid)
                accumulator.merge(query(node.right, mid + 1, r, leftLimit, rightLimit));
            return accumulator.map;
        }

        private class Node {
            Node left;
            Node right;
            Map<Integer, Integer> map = new HashMap<>();

            public void init(int singleElem) {
                map.put(singleElem, 1);
            }

            public void evaluateMode() {
                map = new HashMap<>(left.map);
                right.map.forEach(
                        (key, value) -> map.merge(key, value, Integer::sum)
                );
            }

            public void merge(Map<Integer, Integer> otherMap) {
                otherMap.forEach(
                        (key, value) -> map.merge(key, value, Integer::sum)
                );
            }
        }
    }
}

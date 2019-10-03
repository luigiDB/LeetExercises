package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and put.
 * <p>
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 * <p>
 * The cache is initialized with a positive capacity.
 * <p>
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * <p>
 * Example:
 * <p>
 * LRUCache cache = new LRUCache( 2 );
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // returns 1
 * cache.put(3,3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4,4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 */
public class __146_LRUCache {
    // To have get in O(1) data must be stored in a map. To have put in O(1) we keep a queue of accessed value and on
    // size boundary we delete the queue popped element.

    @Test
    public void givenTest() {
        LRU cache = new LRU(2);

        cache.put(1, 1);
        cache.put(2, 2);
        Assert.assertEquals(1, cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        Assert.assertEquals(-1, cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        Assert.assertEquals(-1, cache.get(1));       // returns -1 (not found)
        Assert.assertEquals(3, cache.get(3));       // returns 3
        Assert.assertEquals(4, cache.get(4));       // returns 4
    }

    class LRU {
        private int size;
        private Node head = null;
        private Node tail = null;
        Map<Integer, Node> memory;

        public LRU(int size) {
            this.size = size;
            memory = new HashMap<>();
        }

        public int get(int key) {
            Node elem = memory.get(key);
            if (elem == null)
                return -1;

            remove(elem);
            offer(elem);

            return elem.value;
        }

        public void put(int key, int value) {
            if (memory.containsKey(key)) {
                Node node = memory.get(key);
                node.value = value;
                remove(node);
                offer(node);
            } else {
                Node node = new Node(key, value);
                offer(node);
                memory.put(key, node);
                if (memory.keySet().size() > size) {
                    //remove head since is the oldest reference
                    memory.remove(head.key);
                    remove(head);
                }
            }
        }

        //remove it's O(1) since we have the node reference
        private void remove(Node n) {
            if (n.left != null) {
                n.left.right = n.right;
            } else {
                head = n.right;
            }

            if (n.right != null) {
                n.right.left = n.left;
            } else {
                tail = n.left;
            }
        }

        //add to tail easier
        private void offer(Node tmp) {
            if (tail == null) {
                tail = tmp;
                head = tmp;
            } else {
                tail.right = tmp;
                tmp.left = tail;
                tail = tmp;
            }
        }

        private class Node {
            int key;
            int value;
            Node left;
            Node right;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }
    }
}

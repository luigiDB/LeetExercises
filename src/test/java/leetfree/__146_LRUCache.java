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
        private final Map<Integer, Integer> memory;
        private final Queue<Integer> queue;

        public LRU(int size) {
            this.size = size;
            memory = new HashMap<>();
            queue = new LinkedList<>();
            LinkedList<Integer> pippo = new LinkedList<>();
            PriorityQueue<Integer> pq = new PriorityQueue<>();
        }

        public int get(int key) {
            Integer elem = memory.getOrDefault(key, -1);
            queue.removeAll(Collections.singletonList(key));
            queue.offer(key);
            return elem;
        }

        public void put(int key, int value) {
            memory.compute(key, (k, v) -> value);
            queue.offer(key);
            if (memory.size() > size) {
                memory.remove(queue.poll());
            }
        }
    }
}

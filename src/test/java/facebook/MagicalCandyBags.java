package facebook;

import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

/**
 * You have N bags of candy. The ith bag contains arr[i] pieces of candy, and each of the bags is magical!
 * It takes you 1 minute to eat all of the pieces of candy in a bag (irrespective of how many pieces of candy are
 * inside), and as soon as you finish, the bag mysteriously refills. If there were x pieces of candy in the bag at the
 * beginning of the minute, then after you've finished you'll find that floor(x/2) pieces are now inside.
 * You have k minutes to eat as much candy as possible. How many pieces of candy can you eat?
 */
public class MagicalCandyBags {

    @Test
    public void a() {
        assertEquals(14, maxCandies(new int[]{2, 1, 7, 4, 2}, 3));
    }

    int maxCandies(int[] arr, int k) {
        // Write your code here
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for (int i : arr) {
            pq.offer(i);
        }

        int candies = 0;
        for (int i = 0; i < k; i++) {
            int poll = pq.poll();
            candies += poll;
            pq.offer(poll / 2);
        }
        return candies;
    }

}

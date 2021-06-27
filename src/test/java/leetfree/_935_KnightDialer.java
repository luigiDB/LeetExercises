package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two
 * squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of
 * chess knight are shown in this diagaram:
 * We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
 * Given an integer n, return how many distinct phone numbers of length n we can dial.
 * You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a
 * number of length n. All jumps should be valid knight jumps.
 * As the answer may be very large, return the answer modulo 1^9 + 7.
 */
public class _935_KnightDialer {

    @Before
    public void setUp() throws Exception {
        memory.clear();
    }

    @Test
    public void a() {
        assertEquals(10, knightDialer(1));
    }

    @Test
    public void b() {
        assertEquals(20, knightDialer(2));
    }

    @Test
    public void c() {
        assertEquals(46, knightDialer(3));
    }

    @Test
    public void d() {
        assertEquals(104, knightDialer(4));
    }

    @Test
    public void e() {
        assertEquals(136006598, knightDialer(3131));
    }

    public int knightDialer(int n) {
        int numbers = 0;
        for (int i = 0; i < 10; i++) {
            numbers = (numbers + findNumbersFrom(i, 1, n)) % max;
        }
        return numbers;
    }

    private int findNumbersFrom(int number, int len, int size) {

        Pair<Integer, Integer> indexPair = Pair.of(number, len);
        if (memory.containsKey(indexPair))
            return memory.get(indexPair);

        if (len == size)
            return 1;

        int sum = 0;
        for (int next : reachableStatuses(number)) {
            sum = (sum + findNumbersFrom(next, len + 1, size)) % max;
        }

        memory.put(indexPair, sum);

        return sum;
    }

    private List<Integer> reachableStatuses(int number) {
        return reach.get(number);
    }

    public static final int max = 1000000007;
    private final Map<Pair<Integer, Integer>, Integer> memory = new HashMap<>();
    private final Map<Integer, List<Integer>> reach = Map.of(
            0, List.of(4, 6),
            1, List.of(6, 8),
            2, List.of(7, 9),
            3, List.of(4, 8),
            4, List.of(0, 3, 9),
            5, List.of(),
            6, List.of(0, 1, 7),
            7, List.of(2, 6),
            8, List.of(1, 3),
            9, List.of(2, 4)
    );
}

package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

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
public class _935_KnightDialer_With_Matrix {

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
        numbers = (numbers + findNumbersFrom(Pair.of(3, 1), 1, n)) % max;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                numbers = (numbers + findNumbersFrom(Pair.of(i, j), 1, n)) % max;
            }
        }
        return numbers;
    }

    private int findNumbersFrom(Pair<Integer, Integer> number, int len, int size) {

        Pair<Pair<Integer, Integer>, Integer> indexPair = Pair.of(number, len);
        if (memory.containsKey(indexPair))
            return memory.get(indexPair);

        if (len == size)
            return 1;

        int sum = 0;
        for (Pair<Integer, Integer> next : reachableStatuses(number)) {
            sum = (sum + findNumbersFrom(next, len + 1, size)) % max;
        }

        memory.put(indexPair, sum);

        return sum;
    }

    private List<Pair<Integer, Integer>> reachableStatuses(Pair<Integer, Integer> number) {

        List<Pair<Integer, Integer>> results = new ArrayList<>();
        int[] directions = {-2, -1, 1, 2};
        for (int i : directions)
            for (int j : directions)
                if (Math.abs(i) + Math.abs(j) == 3) {
                    Pair<Integer, Integer> possibleNext = Pair.of(number.getLeft() + i, number.getRight() + j);
                    if (banned.contains(possibleNext)
                            || possibleNext.getLeft() < 0 || possibleNext.getLeft() > 3
                            || possibleNext.getRight() < 0 || possibleNext.getRight() > 2)
                        continue;
                    results.add(possibleNext);
                }

        return results;
    }

    public static final int max = 1000000007;
    private final Map<Pair<Pair<Integer, Integer>, Integer>, Integer> memory = new HashMap<>();
    private final Set<Pair<Integer, Integer>> banned = Set.of(
            Pair.of(3, 0),
            Pair.of(3, 2)
    );
}

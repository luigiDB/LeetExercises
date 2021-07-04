package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Given a string s, return the first non-repeating character in it and return its index. If it does not exist,
 * return -1.
 */
public class _387_FirstUniqueCharacterInAString {

    @Test
    public void a() {
        assertEquals(0, firstUniqChar("leetcode"));
        assertEquals(2, firstUniqChar("loveleetcode"));
        assertEquals(-1, firstUniqChar("aabb"));
    }

    public int firstUniqChar(String s) {
        Map<Character, Pair<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c))
                map.put(c, Pair.of(i, 1));
            else {
                Pair<Integer, Integer> cCount = map.get(c);
                map.put(c, Pair.of(cCount.getLeft(), cCount.getRight() + 1));
            }
        }

        return map.values()
                .stream()
                .filter(x -> x.getRight() == 1)
                .min(Comparator.comparingInt(Pair::getLeft))
                .orElse(Pair.of(-1, -1))
                .getLeft();
    }
}

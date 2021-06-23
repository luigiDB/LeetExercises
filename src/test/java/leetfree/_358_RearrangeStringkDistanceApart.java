package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.Matchers.contains;

/**
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least
 * distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty
 * string.
*/
public class _358_RearrangeStringkDistanceApart {

    @Test
    public void given() {
        List<String> possibleResults = List.of("abcabc", "acbacb", "bcabca", "bacbac", "cbacba", "cabcab");
        Assert.assertTrue(possibleResults.contains(rearrangeString("aabbcc", 3)));
    }

    @Test
    public void given2() {
        List<String> possibleResults = List.of("abacabcd", "abcabcda", "abcabcad");
        Assert.assertTrue(possibleResults.contains(rearrangeString("aaadbbcc", 2)));
    }

    /*maintain an ordered ist of Pair<char, n_occurencies> ordered by freq.
        Everytime choice at roundrobin from if one cannot be chosen go to the next one.
        Keep char with a sigle occurrence apart and use only when there aren't other possibilities*/

    /**
     * Complexity is actually O(n) because in O(n*log(26)) the log is negligible
     */
    public String rearrangeString(String str, int k) {

        StringBuilder rearranged = new StringBuilder();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray())
            map.compute(c, (key, value) -> (value == null) ? 1 : value + 1);

        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        maxHeap.addAll(map.entrySet());

        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);

            if (waitQueue.size() < k) {
                continue;
            }
            Map.Entry<Character, Integer> front = waitQueue.poll();
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }

        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }
}

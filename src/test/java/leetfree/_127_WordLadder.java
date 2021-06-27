package leetfree;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.Phaser;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * Only one letter can be changed at a time.
 * Each transformed word must exist in the word list.
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 */
public class _127_WordLadder {
    @Test
    public void test1() {
        Assert.assertEquals(5, ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    @Test
    public void test2() {
        Assert.assertEquals(0, ladderLength("hit", "cog", List.of("hot", "dot", "dog", "lot", "log")));
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        ArrayList<String> fullList = new ArrayList<>(wordList);
        fullList.add(beginWord);
        Map<String, Set<String>> graph = initGraph(fullList);

        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(Pair.of(beginWord, 1));

        // since we are traversing BFS if something has already been visited it has been visited with less steps
        // so we can cut branches that we are already visiting
        Set<String> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Pair<String, Integer> poll = queue.poll();
            visited.add(poll.getLeft());

            for (String nextWord : graph.get(poll.getLeft())) {
                if (nextWord.equals(endWord))
                    return poll.getRight() + 1;
                if (!visited.contains(nextWord))
                    queue.add(Pair.of(nextWord, poll.getRight() + 1));
            }
        }

        return 0;
    }

    private Map<String, Set<String>> initGraph(ArrayList<String> fullList) {
        Map<String, Set<String>> graph = new HashMap<>();
        for (String w1 : fullList) {
            for (String w2 : fullList) {
                if (w1.equals(w2))
                    continue;
                if (wordDistance(w1, w2) == 1) {
                    graph.computeIfAbsent(w1, k -> new HashSet<>());
                    graph.get(w1).add(w2);

                    graph.computeIfAbsent(w2, k -> new HashSet<>());
                    graph.get(w2).add(w1);
                }

            }
        }
        return graph;
    }

    private int wordDistance(String w1, String w2) {
        int distanceCounter = 0;
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i))
                distanceCounter++;
        }
        return distanceCounter;
    }
}

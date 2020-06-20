package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Check whether the original sequence org can be uniquely reconstructed from the sequences in seqs. The org
sequence is a permutation of the integers from 1 to n, with 1 ≤ n ≤ 104. Reconstruction means building a
shortest common supersequence of the sequences in seqs (i.e., a shortest sequence so that all sequences in
seqs are subsequences of it). Determine whether there is only one sequence that can be reconstructed from seqs
and it is the org sequence.
Example 1:
Input:
org: [1,2,3], seqs: [[1,2],[1,3]]
Output:
false
Explanation:
[1,2,3] is not the only one sequence that can be reconstructed, because [1,3,2] is also a valid sequence that can
be reconstructed.
Example 2:
Input:
org: [1,2,3], seqs: [[1,2]]
Output:
false
Explanation:
The reconstructed sequence can only be [1,2].
Example 3:
Input:
org: [1,2,3], seqs: [[1,2],[1,3],[2,3]]
Output:
true
Explanation:
The sequences [1,2], [1,3], and [2,3] can uniquely reconstruct the original sequence [1,2,3].
Example 4:
Input:
org: [4,1,5,2,6,3], seqs: [[5,2,6,3],[4,1,5,2]]
Output:
true
UPDATE (2017/1/8):
The seqs parameter had been changed to a list of list of strings (instead of a 2d array of strings). Please reload the
code definition to get the latest changes.
*/
public class _444_SequenceReconstruction {

    @Test
    public void given1() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(List.of(1, 2));
        input.add(List.of(1, 3));
        input.add(List.of(2, 3));
        Assert.assertTrue(sequenceReconstruction(List.of(1, 2, 3), input));
    }

    @Test
    public void given2() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(List.of(1, 2));
        input.add(List.of(1, 3));
        Assert.assertFalse(sequenceReconstruction(List.of(1, 2, 3), input));
    }

    @Test
    public void given3() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(List.of(1, 2));
        Assert.assertFalse(sequenceReconstruction(List.of(1, 2, 3), input));
    }

    @Test
    public void given4() {
        List<List<Integer>> input = new ArrayList<>();
        input.add(List.of(5, 2, 6, 3));
        input.add(List.of(4, 1, 5, 2));
        Assert.assertTrue(sequenceReconstruction(List.of(4, 1, 5, 2, 6, 3), input));
    }

    /**
     * Can be resolved comparating the org string with all the possible absolute topological sort reachable using Khan
     * algorithm
     */
    public boolean sequenceReconstruction(List<Integer> org, List<List<Integer>> seqs) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        TreeMap<Integer, Integer> fanIn = new TreeMap<>();
        List<List<Integer>> possibleSort = new ArrayList<>();

        for (List<Integer> sequence : seqs) {
            for (int i = 0; i < sequence.size() - 1; i++) {
                int finalI = i;
                graph.compute(sequence.get(i), (k, v) -> {
                    if (v == null)
                        v = new ArrayList<>();
                    v.add(sequence.get(finalI + 1));
                    return v;
                });
                graph.computeIfAbsent(sequence.get(i + 1), v -> new ArrayList<>());

                fanIn.compute(sequence.get(i + 1), (k, v) -> {
                    if (v == null)
                        return 1;
                    return v + 1;
                });
                fanIn.putIfAbsent(sequence.get(i), 0);
            }
        }

        topologicalSort(graph, fanIn, new HashSet<Integer>(), new LinkedList<>(), possibleSort);
        if (possibleSort.size() > 1)
            return false;
        ;
        return possibleSort.get(0).equals(org);
    }

    private void topologicalSort(Map<Integer, List<Integer>> graph,
                                 TreeMap<Integer, Integer> fanIn,
                                 HashSet<Integer> visited,
                                 LinkedList<Integer> currentSeq,
                                 List<List<Integer>> possibleSort) {
        List<Integer> nexts = findPossibleNextSteps(graph, fanIn, visited);
        if (nexts.size() == 0) {
            possibleSort.add(new ArrayList<>(currentSeq));
            return;
        } else {
            for (Integer next : nexts) {
                //backtrack do
                currentSeq.add(next);
                for (Integer i : graph.get(next)) {
                    fanIn.compute(i, (k, v) -> v - 1);
                }
                visited.add(next);

                topologicalSort(graph, fanIn, visited, currentSeq, possibleSort);

                //backtrack undo
                visited.remove(next);
                for (Integer i : graph.get(next)) {
                    fanIn.compute(i, (k, v) -> v + 1);
                }
                currentSeq.removeLast();
            }
        }
    }

    private List<Integer> findPossibleNextSteps(Map<Integer, List<Integer>> graph, TreeMap<Integer, Integer> fanIn, Set<Integer> visited) {
        if (!fanIn.containsValue(0))
            return List.of();

        //fanIn 0
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : fanIn.entrySet()) {
            if (entry.getValue() == 0)
                res.add(entry.getKey());
        }

        //remove visited
        res.removeAll(visited);
        return res;
    }

}

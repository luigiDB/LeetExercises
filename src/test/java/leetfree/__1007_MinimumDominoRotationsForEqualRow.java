package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/*
In a row of dominoes, A[i] and B[i] represent the top and bottom halves of the i-th domino.  (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)
We may rotate the i-th domino, so that A[i] and B[i] swap values.
Return the minimum number of rotations so that all the values in A are the same, or all the values in B are the same.
If it cannot be done, return -1.
Note:
1 <= A[i], B[i] <= 6
2 <= A.length == B.length <= 20000
 */
public class __1007_MinimumDominoRotationsForEqualRow {


    @Test
    public void given1() {
        Assert.assertEquals(2, minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,2,6,2,3,2}));
    }

    @Test
    public void given2() {
        Assert.assertEquals(-1, minDominoRotations(new int[]{2,1,2,4,2,2}, new int[]{5,4,6,2,3,2}));
    }

    @Test
    public void stressTestFirstSolution() {
        int[] A = new int[1000000];
        Arrays.fill(A, 2);
        int[] B = new int[1000000];
        Arrays.fill(B, 5);
        A[A.length-1] = 7;
        Assert.assertEquals(0, minDominoRotations(A, B));
    }

    @Test
    public void stressTestSecondSolution() {
        int[] A = new int[1000000];
        Arrays.fill(A, 2);
        int[] B = new int[1000000];
        Arrays.fill(B, 5);
        A[A.length-1] = 7;
        Assert.assertEquals(0, minDominoRotationsSolution2(A, B));
    }

    // This solution completes in 67ms and 41.8MB
    public int minDominoRotations(int[] A, int[] B) {
        HashMap<Integer, Integer> occurrenceMap = new HashMap<>();
        HashMap<Integer, Integer> doubleDominoes = new HashMap<>();
        int maxOccurences = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            if (A[i] == B[i]) {
                maxOccurences = Math.max(maxOccurences, addOccurrence(A[i], occurrenceMap));
                addOccurrence(A[i], doubleDominoes);
            } else {
                maxOccurences = Math.max(maxOccurences, addOccurrence(A[i], occurrenceMap));
                maxOccurences = Math.max(maxOccurences, addOccurrence(B[i], occurrenceMap));
            }
            if(maxOccurences != i+1) {
                return -1;
            }
        }

        int candidate = occurrenceMap.entrySet()
                .stream()
                .filter(e -> e.getValue() == A.length)
                .findFirst()
                .get()
                .getKey();
        int occurenciesInA = -doubleDominoes.getOrDefault(candidate, 0);
        for (int i = 0; i < A.length; i++) {
            if (A[i] == candidate)
                occurenciesInA++;
        }
        return Math.min(A.length-doubleDominoes.getOrDefault(candidate, 0)-occurenciesInA, occurenciesInA);
    }

    private int addOccurrence(int key, HashMap<Integer, Integer> map) {
        map.compute(key, (k, v) -> {
            if (v == null)
                return 1;
            else
                return v + 1;
        });
        return map.get(key);
    }

    /*
    GREEDY SOLUTION
    We can immediately determine if we need to flip the next domino if there is a value equals to one on the
    current domino
    This solution completes in 7ms and 114.8 MB
    */
    public int minDominoRotationsSolution2(int[] A, int[] B) {
        int rotations = tryFit(A[0], A, B);
        if (rotations != -1 || A[0] == B[0])
            return rotations;
        return tryFit(B[0], A, B);

    }

    private int tryFit(int recurrentNumber, int[] A, int[] B) {
        int flipCount = 0;
        int doublesDomino = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] != recurrentNumber && B[i] != recurrentNumber)
                return -1;
            if (A[i] == recurrentNumber && B[i] == recurrentNumber)
                doublesDomino++;
            else if (B[i] == recurrentNumber) {
                flipCount++;
            }
        }
        return Math.min(flipCount, A.length - doublesDomino - flipCount);
    }

}

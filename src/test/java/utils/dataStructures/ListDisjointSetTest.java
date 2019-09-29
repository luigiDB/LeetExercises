package utils.dataStructures;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

public class ListDisjointSetTest {


    private ListDisjointSet<Integer> testSet;

    @Before
    public void setUp() throws Exception {
        testSet = new ListDisjointSet<>();

    }

    @Test
    public void testAdd() {
        testSet.addSet(1);
        Set<Set<Integer>> sets = testSet.returnActualSets();
        Assert.assertEquals(1, sets.size());
        for (Set<Integer> set : sets) {
            Assert.assertTrue(set.contains(1));
        }
    }

    @Test
    public void testAddWithMultipleSet() {
        testSet.addSet(1);
        testSet.addSet(2);
        Set<Set<Integer>> sets = testSet.returnActualSets();
        Assert.assertEquals(2, sets.size());
        for (Set<Integer> set : sets) {
            Assert.assertTrue(set.contains(1) || set.contains(2));
        }
    }

    @Test
    public void testMerge() {
        testSet.addSet(1);
        testSet.addSet(2);
        testSet.merge(1, 2);
        Set<Set<Integer>> sets = testSet.returnActualSets();
        Assert.assertEquals(1, sets.size());
        for (Set<Integer> set : sets) {
            Assert.assertTrue(set.contains(1) && set.contains(2));
        }
    }

    @Test
    public void testMergeComplex() {
        testSet.addSet(1);
        testSet.addSet(2);
        testSet.addSet(3);
        testSet.addSet(4);
        testSet.addSet(5);
        testSet.merge(1, 2);
        testSet.merge(3, 4);
        testSet.merge(1, 3);
        Set<Set<Integer>> sets = testSet.returnActualSets();
        Assert.assertEquals(2, sets.size());
        for (Set<Integer> set : sets) {
            Assert.assertTrue(set.containsAll(Arrays.asList(1, 2, 3, 4)) || set.contains(5));
        }
    }
}
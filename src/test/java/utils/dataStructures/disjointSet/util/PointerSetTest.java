package utils.dataStructures.disjointSet.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import utils.dataStructures.disjointSet.util.Elem;
import utils.dataStructures.disjointSet.util.PointerSet;

public class PointerSetTest {

    private PointerSet<Integer> testSet;

    @Before
    public void setUp() throws Exception {
        testSet = new PointerSet<>();
        testSet.add(1);
        testSet.add(2);
    }

    @Test
    public void add() {
        testSet.add(3);
        Assert.assertTrue(testSet.contains(1));
        Assert.assertTrue(testSet.contains(2));
        Assert.assertTrue(testSet.contains(3));
        Assert.assertFalse(testSet.contains(4));
    }

    @Test
    public void contains() {
        Assert.assertTrue(testSet.contains(1));
        Assert.assertTrue(testSet.contains(2));
        Assert.assertFalse(testSet.contains(3));
    }

    @Test
    public void testPointer() {
        for(Elem<Integer> elem: testSet.getSet()) {
            Assert.assertTrue(elem.getHead().equals(testSet));
        }
    }

    @Test
    public void merge() {
        PointerSet<Integer> secondary = new PointerSet<>();
        secondary.add(3);
        secondary.add(4);
        for(Elem<Integer> elem: secondary.getSet()) {
            Assert.assertTrue(elem.getHead().equals(secondary));
        }

        testSet.merge(secondary);
        for(Elem<Integer> elem: testSet.getSet()) {
            Assert.assertTrue(elem.getHead().equals(testSet));
        }
    }
}
package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _249_GroupShiftedStrings {
    /*Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

    "abc" -> "bcd" -> ... -> "xyz"
    Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

    For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
    A solution is:

    [
      ["abc","bcd","xyz"],
      ["az","ba"],
      ["acef"],
      ["a","z"]
    ]*/


    @Test
    public void testThatTwoEqualStringsGoesTogheter() {
        List<String> list = new LinkedList<>(Arrays.asList("abc", "abc"));
        Map<ArrayList<Integer>, Set<String>> arrayListListMap = groupShiftedStrings(list);

        HashSet<ArrayList<Integer>> expected = new HashSet<>();
        ArrayList<Integer> expectedKeyset = new ArrayList<>(Arrays.asList(1,1));
        expected.add(expectedKeyset);
        Assert.assertEquals(expected, arrayListListMap.keySet());
        Assert.assertEquals(arrayListListMap.get(expectedKeyset), new HashSet<>(Arrays.asList("abc")));
    }

    @Test
    public void testThatTwoStringGoInTheSameGroup() {
        List<String> list = new LinkedList<>(Arrays.asList("abc", "bcd"));
        Map<ArrayList<Integer>, Set<String>> arrayListListMap = groupShiftedStrings(list);

        HashSet<ArrayList<Integer>> expected = new HashSet<>();
        ArrayList<Integer> expectedKeyset = new ArrayList<>(Arrays.asList(1,1));
        expected.add(expectedKeyset);
        Assert.assertEquals(expected, arrayListListMap.keySet());
        Assert.assertEquals(arrayListListMap.get(expectedKeyset), new HashSet<>(Arrays.asList("abc", "bcd")));
    }

    @Test
    public void testThatTwoStringsGoToDifferentGroup() {
        List<String> list = new LinkedList<>(Arrays.asList("abc", "abd"));
        Map<ArrayList<Integer>, Set<String>> arrayListListMap = groupShiftedStrings(list);

        HashSet<ArrayList<Integer>> expected = new HashSet<>();
        ArrayList<Integer> expectedKeyset = new ArrayList<>(Arrays.asList(1,1));
        expected.add(expectedKeyset);
        ArrayList<Integer> expectedKeyset2 = new ArrayList<>(Arrays.asList(1,2));
        expected.add(expectedKeyset2);
        Assert.assertEquals(expected, arrayListListMap.keySet());
        Assert.assertEquals(arrayListListMap.get(expectedKeyset), new HashSet<>(Arrays.asList("abc")));
        Assert.assertEquals(arrayListListMap.get(expectedKeyset2), new HashSet<>(Arrays.asList("abd")));
    }

    private Map<ArrayList<Integer>, Set<String>> groupShiftedStrings(List<String> strings) {
        Map<ArrayList<Integer>, Set<String>> result = new HashMap<>();

        for (String s: strings) {
            ArrayList stringIdentifier = generateIdentifier(s);
            if(result.containsKey(stringIdentifier)) {
                Set<String> groupList = result.get(stringIdentifier);
                groupList.add(s);
            } else {
                Set<String> newSet = new HashSet<>();
                newSet.add(s);
                result.put(stringIdentifier, newSet);
            }
        }

        return result;
    }

    private ArrayList generateIdentifier(String s) {
        ArrayList<Integer> distanceVector = new ArrayList<>();
        for (int i = 1; i < s.length(); i++) {
            distanceVector.add(s.charAt(i)-s.charAt(i-1));
        }
        return distanceVector;
    }
}

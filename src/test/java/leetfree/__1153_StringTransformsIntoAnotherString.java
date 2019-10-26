package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Given two strings str1 and str2 of the same length, determine whether you can transform str1 into str2 by doing zero or more conversions.
In one conversion you can convert all occurrences of one character in str1 to any other lowercase English character.
Return true if and only if you can transform str1 into str2.
Example 1:
Input: str1 = "aabcc", str2 = "ccdee"
Output: true
Explanation: Convert 'c' to 'e' then 'b' to 'd' then 'a' to 'c'. Note that the order of conversions matter.
Example 2:
Input: str1 = "leetcode", str2 = "codeleet"
Output: false
Explanation: There is no way to transform str1 to str2.
Note:
1 <= str1.length == str2.length <= 10^4
Both str1 and str2 contain only lowercase English letters.
 */
public class __1153_StringTransformsIntoAnotherString {

    /*
    MUCH EASIER SOLUTION
     public boolean canConvert(String s1, String s2) {
        if (s1.equals(s2)) return true;
        Map<Character, Character> dp = new HashMap<>();
        for (int i = 0; i < s1.length(); ++i) {
            if (dp.getOrDefault(s1.charAt(i), s2.charAt(i)) != s2.charAt(i))
                return false;
            dp.put(s1.charAt(i), s2.charAt(i));
        }
        return new HashSet<Character>(dp.values()).size() < 26;
    }
     */
    @Test
    public void impossibleTest() {
        Assert.assertFalse(canConvert("leetcode", "codeleet"));
    }

    @Test
    public void possibleWithoutMappingsTest() {
        Assert.assertTrue(canConvert("aabcc", "ccbaa"));
    }

    @Test
    public void possibleWithMappingsTest() {
        Assert.assertTrue(canConvert("aabcc", "zzbzz"));
    }

    @Test
    public void impossibleMappingsTest() {
        Assert.assertFalse(canConvert("aabaa", "ccbaa"));
    }

    @Test
    public void impossibleForCharacterExhaustion() {
        Assert.assertFalse(canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyza"));
    }

    @Test
    public void possibleNearCharacterExhaustion() {
        Assert.assertTrue(canConvert("abcdefghijklmnopqrstuvwxyz", "bcdefghijklmnopqrstuvwxyzq"));
    }

    @Test
    public void validExhaustion() {
        Assert.assertTrue(canConvert("abcdefghijklmnopqrstuvwxyz", "abcdefghijklmnopqrstuvwxyz"));
    }

    public boolean canConvert(String str1, String str2) {
        if (str1.equals(str2))
            return true;                // this is dump but resolve a lot of problems
        List<Integer> s1 = toIntegerList(str1);
        List<Integer> s2 = toIntegerList(str2);
        Map<Integer, Integer> mappingScheme = new HashMap<>();
        Set<Integer> codomain = new HashSet<>();

        for (int i = 0; i < s1.size(); i++) {
            if (s1.get(i).equals(s2.get(i))) {
                codomain.add(s2.get(i));
            } else {
                if (mappingScheme.get(s1.get(i)) == null || !mappingScheme.get(s1.get(i)).equals(s2.get(i))) {
                    if (codomain.contains(s1.get(i)))
                        return false;
                    mappingScheme.put(s1.get(i), s2.get(i));
                    codomain.add(s2.get(i));
                }
            }
        }
        return (codomain.size() < 26); // mapping can never be valid if we need visit all chars
    }

    private List<Integer> toIntegerList(String s) {
        LinkedList<Integer> list = new LinkedList<>();
        int[] mapArray = new int['z' - 'a' + 1];
        int counter = 1;

        for (int i = 0; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            Integer number = (mapArray[index] == 0) ? counter++ : mapArray[index];
            mapArray[index] = number;
            list.addLast(number);
        }
        return list;
    }
}

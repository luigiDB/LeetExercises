package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class _159_LongestSubstringWithAtMostTwoDistinctCharacters {
    /*Given a string, find the length of the longest substring T that contains at most 2 distinct characters.
    For example, Given s = “eceba”,
    T is "ece" which its length is 3.*/

    @Test
    public void testCase1() {
        String test = "eceba";
        Assert.assertEquals(3, longest2CharStringSize(test));
    }

    @Test
    public void testCase2() {
        String test = "abcddefee";
        Assert.assertEquals(4, longest2CharStringSize(test));
    }

    private int longest2CharStringSize(String input) {
        int startPOinter = 0;
        int endPOinter = 0;
        Map<Character, Integer> currentSubstringContent = new HashMap<>();
        int maxSize = 0;

        currentSubstringContent.put(input.charAt(endPOinter), 1);
        while (endPOinter < input.length()-1) {
            endPOinter++;
            char end = input.charAt(endPOinter);
            if(currentSubstringContent.containsKey(end)) {
                currentSubstringContent.put(end, currentSubstringContent.get(end)+1);
            } else {
                currentSubstringContent.put(end, 1);
                while(currentSubstringContent.size() > 2) {
                    char start = input.charAt(startPOinter);
                    currentSubstringContent.put(start, currentSubstringContent.get(start)-1);
                    if(currentSubstringContent.get(start) == 0 ) {
                        currentSubstringContent.remove(start);
                    }
                    startPOinter++;
                }
            }
            maxSize = Math.max(maxSize, currentSubstringContent.values().stream().mapToInt(i->i).sum());
        }

        return maxSize;
    }


}

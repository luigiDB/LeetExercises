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
        int startPointer = 0;
        int endPointer = 0;
        Map<Character, Integer> currentSubstringContent = new HashMap<>();
        int maxSize = 0;

        currentSubstringContent.put(input.charAt(endPointer), 1);
        while (endPointer < input.length()-1) {
            endPointer++;
            char end = input.charAt(endPointer);
            if(currentSubstringContent.containsKey(end)) {
                currentSubstringContent.put(end, currentSubstringContent.get(end)+1);
            } else {
                currentSubstringContent.put(end, 1);
                while(currentSubstringContent.size() > 2) {
                    char start = input.charAt(startPointer);
                    currentSubstringContent.put(start, currentSubstringContent.get(start)-1);
                    if(currentSubstringContent.get(start) == 0 ) {
                        currentSubstringContent.remove(start);
                    }
                    startPointer++;
                }
            }
            maxSize = Math.max(maxSize, currentSubstringContent.values().stream().mapToInt(i->i).sum());
        }

        return maxSize;
    }


}

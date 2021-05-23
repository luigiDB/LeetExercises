package leetfree;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class _320_GeneralizedAbbreviation {
    /*Write a function to generate the generalized abbreviations of a word.

    Example:
    Given word = "word", return the following list (order does not matter):
    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]*/

    @Test
    public void basicExample() {
        Collection<String> abbreviations = evaluateAbbreviations("a");
        List<String> expected = Arrays.asList("a", "1");
        assertTrue(CollectionUtils.isEqualCollection(expected, abbreviations));
    }

    @Test
    public void twoCharExample() {
        Set<String> abbreviations = evaluateAbbreviations("ab");
        Set<String> expected = Set.of("ab", "1b", "a1", "2");
        assertTrue(CollectionUtils.isEqualCollection(expected, abbreviations));
    }

    @Test
    public void giveExample() {
        Collection<String> abbreviations = evaluateAbbreviations("word");
        Set<String> expected = Set.of("word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4");
        assertTrue(CollectionUtils.isEqualCollection(expected, abbreviations));
    }

    private Set<String> evaluateAbbreviations(String input) {
        Set<String> abbreviations = new HashSet<>();

        recursiveAbbreviation(abbreviations, input, new StringBuilder());

        return abbreviations;
    }

    private void recursiveAbbreviation(Set<String> abbreviations, String input, StringBuilder soFar) {

        if (input.length() == 0) {
            abbreviations.add(soFar.toString());
            return;
        }

        int previousLength = soFar.length();

        for (int i = 0; i < input.length(); i++) {
            soFar.append(i + 1);
            if (i + 1 != input.length()) {
                soFar.append(input.charAt(i + 1));
                recursiveAbbreviation(abbreviations, input.substring(i + 2), soFar);
            } else
                recursiveAbbreviation(abbreviations, "", soFar);
            soFar.setLength(previousLength);
        }

        soFar.append(input.charAt(0));
        recursiveAbbreviation(abbreviations, input.substring(1), soFar);

        soFar.setLength(previousLength);

    }


/*
OFFICIAL SOLUTION
public class Solution {
    public List<String> generateAbbreviations(String word){
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), word, 0, 0);
        return ans;
    }

    // i is the current position
    // k is the count of consecutive abbreviated characters
    private void backtrack(List<String> ans, StringBuilder builder, String word, int i, int k){
        int len = builder.length(); // keep the length of builder
        if(i == word.length()){
            if (k != 0) builder.append(k); // append the last k if non zero
            ans.add(builder.toString());
        } else {
            // the branch that word.charAt(i) is abbreviated
            backtrack(ans, builder, word, i + 1, k + 1);

            // the branch that word.charAt(i) is kept
            if (k != 0) builder.append(k);
            builder.append(word.charAt(i));
            backtrack(ans, builder, word, i + 1, 0);
        }
        builder.setLength(len); // reset builder to the original state
    }
}
 */
}

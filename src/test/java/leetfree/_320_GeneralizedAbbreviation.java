package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class _320_GeneralizedAbbreviation {
    /*Write a function to generate the generalized abbreviations of a word.

    Example:
    Given word = "word", return the following list (order does not matter):
    ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]*/

    @Test
    public void basicExample() {
        Collection<String> abbreviations = evaluateAbbreviations("a");
        Assert.assertEquals(Arrays.asList("a", "1"), abbreviations);
    }

    @Test
    public void twoCharExample() {
        Collection<String> abbreviations = evaluateAbbreviations("ab");
        Assert.assertEquals(Arrays.asList("ab", "1b", "b1", "2"), abbreviations);
    }

    @Test
    public void giveExample() {
        Collection<String> abbreviations = evaluateAbbreviations("word");
        Assert.assertEquals(Arrays.asList("word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"), abbreviations);
    }

    private Collection<String> evaluateAbbreviations(String input) {
        Set<String> abbreviations = new HashSet<>();

        recursiveAbbreviation(abbreviations, input);

        return abbreviations;
    }

    private void recursiveAbbreviation(Set<String> abbreviations, String input) {

    }

}

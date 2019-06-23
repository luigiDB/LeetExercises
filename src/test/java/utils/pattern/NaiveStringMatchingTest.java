package utils.pattern;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.contains;

public class NaiveStringMatchingTest {

    @Test
    public void singleMatch() {
        String text = "THIS IS A TEST TEXT";
        String pattern = "TEST";
        List<Integer> matches = NaiveStringMatching.search(text, pattern);

        Assert.assertEquals(1, matches.size());
        Assert.assertEquals(new Integer(10), matches.get(0));
    }

    @Test
    public void multiMatch() {
        String text = "AABAACAADAABAABA";
        String pattern = "AABA";
        List<Integer> matches = NaiveStringMatching.search(text, pattern);

        Assert.assertEquals(3, matches.size());
        Assert.assertThat(matches, contains(0, 9, 12));
    }

    @Test
    public void recursiveMatch() {
        String text = "ABAABAABA";
        String pattern = "ABAABA";
        List<Integer> matches = NaiveStringMatching.search(text, pattern);

        Assert.assertEquals(2, matches.size());
        Assert.assertThat(matches, contains(0, 3));
    }
}
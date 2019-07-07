package utils.pattern;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.hamcrest.Matchers.containsInAnyOrder;

public class TriePatternSearchTest {

    private TriePatternSearch searcher;

    @Before
    public void setUp() throws Exception {
        String text = "geeksforgeeks.org";
        searcher = new TriePatternSearch(text);
    }

    @Test
    public void searchPattern1() {
        Collection<Integer> matches = searcher.search("ee");
        Assert.assertThat(matches, containsInAnyOrder(1, 9));
    }

    @Test
    public void searchPattern2() {
        Collection<Integer> matches = searcher.search("geek");
        Assert.assertThat(matches, containsInAnyOrder(0, 8));
    }

    @Test
    public void searchPattern3() {
        Collection<Integer> matches = searcher.search("quiz");
        Assert.assertNull(matches);
    }

    @Test
    public void searchPattern4() {
        Collection<Integer> matches = searcher.search("forgeeks");
        Assert.assertThat(matches, containsInAnyOrder(5));
    }
}
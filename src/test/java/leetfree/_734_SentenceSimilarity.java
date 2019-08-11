package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
 * determine if two sentences are similar.
 * <p>
 * For example, "great acting skills" and "fine drama talent" are similar, if the similar word pairs are
 * pairs = [["great", "fine"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is not transitive. For example, if "great" and "fine" are similar, and "fine" and
 * "good" are similar, "great" and "good" are not necessarily similar.
 * <p>
 * However, similarity is symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great"
 * being similar.
 * <p>
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"],
 * pairs = [] are similar, even though there are no specified similar word pairs.
 * <p>
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"]
 * can never be similar to words2 = ["doubleplus","good"].
 * <p>
 * Note:
 * <p>
 * The length of words1 and words2 will not exceed 1000.
 * The length of pairs will not exceed 2000.
 * The length of each pairs[i] will be 2.
 * The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 */
public class _734_SentenceSimilarity {

    @Test
    public void trueCase() {
        String[] word1 = new String[]{"great", "acting", "skills"};
        String[] word2 = new String[]{"fine", "drama", "talent"};
        String[][] pairs = new String[][]{{"great", "fine"}, {"acting", "drama"}, {"skills", "talent"}};
        Assert.assertTrue(areSmilar(word1, word2, pairs));
    }

    @Test
    public void falseCase() {
        String[] word1 = new String[]{"great", "acting", "skills"};
        String[] word2 = new String[]{"fine", "drama", "talent"};
        String[][] pairs = new String[][]{{"great", "fine"}, {"acting", "PIPPO"}, {"skills", "talent"}};
        Assert.assertFalse(areSmilar(word1, word2, pairs));
    }

    @Test
    public void trueWithMissingPairs() {
        String[] word1 = new String[]{"acting"};
        String[] word2 = new String[]{"acting"};
        String[][] pairs = new String[][]{};
        Assert.assertTrue(areSmilar(word1, word2, pairs));
    }

    private boolean areSmilar(String[] word1, String[] word2, String[][] pairs) {
        if (word1.length != word2.length)
            return false;

        Map<String, String> pairMap = new HashMap<>();
        for (int i = 0; i < pairs.length; i++) {
            pairMap.put(pairs[i][0], pairs[i][1]);
            pairMap.put(pairs[i][1], pairs[i][0]);
        }

        for (int i = 0; i < word1.length; i++) {
            if (!word1[i].equals(word2[i])) {
                String match = pairMap.get(word1[i]);
                if (!match.equals(word2[i]))
                    return false;
            }
        }

        return true;
    }
}

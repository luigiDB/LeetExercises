package leetfree;

import org.junit.Assert;
import org.junit.Test;

import javax.naming.ConfigurationException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs,
 * determine if two sentences are similar.
 * <p>
 * For example, words1 = ["great", "acting", "skills"] and words2 = ["fine", "drama", "talent"] are similar, if the
 * similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 * <p>
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and
 * "good" are similar, then "great" and "fine" are similar.
 * <p>
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being
 * similar.
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
public class _737_SentenceSimilarityII {
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

    @Test
    public void trueWithTransitivity() {
        String[] word1 = new String[]{"great", "acting", "skills"};
        String[] word2 = new String[]{"good", "drama", "talent"};
        String[][] pairs = new String[][]{{"great", "fine"}, {"fine", "good"}, {"acting", "drama"}, {"skills", "talent"}};
        Assert.assertTrue(areSmilar(word1, word2, pairs));
    }

    @Test
    public void trueWithTransitivity2() throws ConfigurationException {
        String[] word1 = new String[]{"great"};
        String[] word2 = new String[]{"good"};
        String[][] pairs = new String[][]{{"great", "fine"}, {"best", "good"}, {"fine", "best"},};
        Assert.assertTrue(areSmilar(word1, word2, pairs));
    }


    private boolean areSmilar(String[] word1, String[] word2, String[][] pairs) {
        Map<String, Set<String>> mapping = new HashMap<>();

        for (int i = 0; i < pairs.length; i++) {
            //I'll assume that input are always correct
            Set<String> leftSet = mapping.get(pairs[i][0]);
            Set<String> rigthSet = mapping.get(pairs[i][1]);


            if (leftSet == null && rigthSet == null) {
                Set<String> temp = new HashSet<>();
                temp.add(pairs[i][0]);
                temp.add(pairs[i][1]);
                mapping.put(pairs[i][0], temp);
                mapping.put(pairs[i][1], temp);
            } else {
                if (leftSet == null) {
                    rigthSet.add(pairs[i][0]);
                    mapping.put(pairs[i][0], rigthSet);
                }
                if (rigthSet == null) {
                    leftSet.add(pairs[i][1]);
                    mapping.put(pairs[i][1], leftSet);
                }
                if (leftSet != null && rigthSet != null) {
                    /**
                     * This because I haven't noticed before that this can happen.
                     * The best way to handle this is to use disjoint set
                     */
                    leftSet.addAll(rigthSet);
                    for (String s : rigthSet) {
                        mapping.put(s, leftSet);
                    }
                }
            }
        }

        for (int i = 0; i < word1.length; i++) {
            if (!word1[i].equals(word2[i])) {
                Set<String> leftSet = mapping.get(word1[i]);
                Set<String> rigthSet = mapping.get(word2[i]);
                if (leftSet != rigthSet)
                    return false;
            }
        }
        return true;
    }
}

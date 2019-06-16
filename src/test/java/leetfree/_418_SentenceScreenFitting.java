package leetfree;

import org.junit.Assert;
import org.junit.Test;

public class _418_SentenceScreenFitting {
    /**
     * Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the
     * given sentence can be fitted on the screen.
     * Note:
     * A word cannot be split into two lines.
     * The order of words in the sentence must remain unchanged.
     * Two consecutive words in a line must be separated by a single space.
     * Total words in the sentence won't exceed 100.
     * Length of each word is greater than 0 and won't exceed 10.
     * 1 ≤ rows, cols ≤ 20,000.
     * Example 1:
     * Input:
     * rows = 2, cols = 8, sentence = ["hello", "world"]
     * Output:
     * 1
     * Explanation:
     * hello---
     * world---
     * The character '-' signifies an empty space on the screen.
     * Example 2:
     * Input:
     * rows = 3, cols = 6, sentence = ["a", "bcd", "e"]
     * Output:
     * 2
     * Explanation:
     * a-bcd-
     * e-a---
     * bcd-e-
     * The character '-' signifies an empty space on the screen.
     * Example 3:
     * Input:
     * rows = 4, cols = 5, sentence = ["I", "had", "apple", "pie"]
     * Output:
     * 1
     * Explanation:
     * I-had
     * apple
     * pie-I
     * had--
     * The character '-' signifies an empty space on the screen.
     */

    @Test
    public void validation() {
        Assert.assertEquals(1, new FitSentence(2, 8, new String[]{"hello", "world"}).fit());
        Assert.assertEquals(2, new FitSentence(3, 6, new String[]{"a", "bcd", "e"}).fit());
        Assert.assertEquals(1, new FitSentence(4, 5, new String[]{"I", "had", "apple", "pie"}).fit());
    }

    private class FitSentence {
        private int rows;
        private int columns;
        private String[] sentence;
        private int rowCounter;
        private int rowEnd;
        private int sentenceIterationCounter;
        private int index;

        public FitSentence(int rows, int columns, String[] sentence) {
            this.rows = rows;
            this.columns = columns;
            this.sentence = sentence;
            rowCounter = 0;
            rowEnd = rowCounter * columns + columns - 1;
            sentenceIterationCounter = 0;
            index = 0;
        }

        private void advanceLine() {
            rowCounter++;
            rowEnd = rowCounter * columns + columns - 1;
            index = rowCounter * columns;
        }

        private void addSpace() {
            if (index == rowEnd) {
                advanceLine();
            } else {
                index += 2;
                if (index >= rowEnd) {
                    advanceLine();
                }
            }
        }

        public int fit() {
            while (true) {
                for (int i = 0; i < sentence.length; i++) {;
                    if (rowCounter == rows) {
                        return sentenceIterationCounter;
                    }
                    int wLen = sentence[i].length();
                    if (wLen > columns) { return -1; }

                    if (index + wLen - 1 <= rowEnd) {
                        index += (wLen - 1);
                        addSpace();
                    } else {
                        advanceLine();
                        index += (wLen - 1);
                        addSpace();
                    }
                }
                sentenceIterationCounter++;
            }
        }
    }
}

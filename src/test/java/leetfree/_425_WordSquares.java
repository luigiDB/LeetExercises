package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/*
Given a set of words (without duplicates), find all word squares you can build from them.
A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).
For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.
b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
Example 1:
Input:
["area","lead","wall","lady","ball"]
Output:
"wall",
"area",
"lead",
"lady"

"ball",
"area",
"lead",
"lady"
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).
Example 2:
Input:
["abat","baba","atan","atal"]
Output:
"baba",
"abat",
"baba",
"atan"

"baba",
"abat",
"baba",
"atal"
Explanation:
The output consists of two word squares. The order of output does not matter (just the order of words in each word square
*/
public class _425_WordSquares {

    @Test
    public void validation1() {
        Set<String[]> possibleSquares = evaluateSpecularSquares("area","lead","wall","lady","ball");

        Assert.assertEquals(2, possibleSquares.size());
        Assert.assertTrue(possibleSquares.contains(new String[]{"wall","area","lead","lady"}));
        Assert.assertTrue(possibleSquares.contains(new String[]{"ball","area","lead","lady"}));
    }

    @Test
    public void validation2() {
        Set<String[]> possibleSquares = evaluateSpecularSquares("abat","baba","atan","atal");

        Assert.assertEquals(2, possibleSquares.size());
        Assert.assertTrue(possibleSquares.contains(new String[]{"baba","abat","baba","atan"}));
        Assert.assertTrue(possibleSquares.contains(new String[]{"baba","abat","baba","atal"}));
    }

    private Set<String[]> evaluateSpecularSquares(String... inputs) {
        /*
        PSEUDO CODE of a backtracking solution

        for each W in ALL_WORDS:
            square[0] = W
            foo(1, ALL_WORDS - W, square, results)

        void foo(i, available, square, results):
            if(i == square.length):
                add square to solution
                return
            for each W in available:
                if(wordIsPlausible(W, i, square)):
                    matrix[i] = W
                    available.remove(W)
                    foo(i+1, available, square, results)
                    available.add(W)

        boolean wordIsPlausible(W, i, square)):
            return true if for each 0 <= k < i : W[k] == square[k][i]
            AKA the prefix of w must be equals to the characters immediately over the ith character of W
         */
        return null;
    }
}

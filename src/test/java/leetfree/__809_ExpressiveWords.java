package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi" -> "hiiii".  In these
strings like "heeellooo", we have groups of adjacent letters that are all the same:  "h", "eee", "ll", "ooo".
For some given string S, a query word is stretchy if it can be made to be equal to S by any number of applications of
the following extension operation:
1_ choose a group consisting of characters c,
2_ add some number of characters c to the group so that the size of the group is 3 or more.
For example, starting with "hello", we could do an extension on the group "o" to get "hellooo", but we cannot get
"helloo" since the group "oo" has size less than 3.  Also, we could do another extension like "ll" -> "lllll" to get
"helllllooo".  If S = "helllllooo", then the query word "hello" would be stretchy because of these two extension
operations: query = "hello" -> "hellooo" -> "helllllooo" = S.
Given a list of query words, return the number of words that are stretchy.
Example:
Input:
S = "heeellooo"
words = ["hello", "hi", "helo"]
Output: 1
Explanation:
We can extend "e" and "o" in the word "hello" to get "heeellooo".
We can't extend "helo" to get "heeellooo" because the group "ll" is not size 3 or more.


Notes:

0 <= len(S) <= 100.
0 <= len(words) <= 100.
0 <= len(words[i]) <= 100.
S and all words in words consist only of lowercase letters
 */
public class __809_ExpressiveWords {
    @Test
    public void testGiven() {
        Assert.assertEquals(1, expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
    }

    @Test
    public void testShorterInputs() {
        Assert.assertEquals(0, expressiveWords("abcd", new String[]{"abc"}));
    }

    @Test
    public void testThatWhenASequenceIsShorterInTheGivenWordZeroIsReturned() {
        Assert.assertEquals(0, expressiveWords("aaa", new String[]{"aaaa"}));
    }

    /**
     * We can iterate at the same time over S and counting for each char encountered the sequential occurrences:
     * Once we know for each char the occurrences on S (N) and on the word (M) we can determine the results as:
     * if (N<M) imopssible the char not exist in S
     * if (N==M) same number of chars
     * if (N >M %% N>3 ) ok
     * else not possible
     */


    public int expressiveWords(String S, String[] words) {
        List<Deconstruct> mainWord = divide(S);

        return Arrays.stream(words)
                .map( s -> divide(s))
                .map( d -> {
                    if(mainWord.size() != d.size())
                        return 0;
                    for (int i = 0; i < mainWord.size(); i++) {
                        if(mainWord.get(i).getChar() != d.get(i).getChar() )
                            return 0;
                        if(mainWord.get(i).getOccurrences() < 3 && d.get(i).getOccurrences() != mainWord.get(i).getOccurrences() )
                            return 0;
                        if(mainWord.get(i).getOccurrences() >= 3 && d.get(i).getOccurrences() > mainWord.get(i).getOccurrences() )
                            return 0;
                    }
                    return 1;
                })
                .reduce(Integer::sum)
                .get();
    }

    private List<Deconstruct> divide(String s) {
        List<Deconstruct> tempList = new LinkedList<>();

        char current = s.charAt(0);
        int counter = 0;
        for (char c: s.toCharArray()) {
            if(c == current)
                counter++;
            else {
                tempList.add(new Deconstruct(current, counter));
                current = c;
                counter = 1;
            }
        }
        tempList.add(new Deconstruct(current, counter));

        return tempList;
    }

    private class Deconstruct {

        private final char c;
        private final int occurrences;

        public Deconstruct(char c, int occurrences) {
            this.c = c;
            this.occurrences = occurrences;
        }

        public char getChar() {
            return c;
        }

        public int getOccurrences() {
            return occurrences;
        }
    }
}

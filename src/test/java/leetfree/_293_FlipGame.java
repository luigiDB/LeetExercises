package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class _293_FlipGame {
    /*You are playing the following Flip Game with your friend: Given a string that contains only these two
    characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a
    person can no longer make a move and therefore the other person will be the winner.
    Write a function to compute all possible states of the string after one valid move.
    For example, given s = "++++", after one move, it may become one of the following states:
    [
      "--++",
      "+--+",
      "++--"
    ]
    If there is no valid move, return an empty list [].*/

    @Test
    public void testBaseExample() {
        String input = "++";
        Assert.assertEquals(Arrays.asList("--"), possibleFlips(input));
    }

    @Test
    public void testImpossible() {
        String input = "";
        Assert.assertEquals(Collections.EMPTY_LIST, possibleFlips(input));

        input = "-+";
        Assert.assertEquals(Collections.EMPTY_LIST, possibleFlips(input));

        input = "--";
        Assert.assertEquals(Collections.EMPTY_LIST, possibleFlips(input));
    }

    @Test
    public void testPositiveExample() {
        String input = "++++";
        Assert.assertEquals(Arrays.asList("--++", "+--+", "++--"), possibleFlips(input));
    }

    public List<String> possibleFlips(String input) {
        List<String> result = new LinkedList<>();

        char[] inputArray = input.toCharArray();
        for (int i = 0; i < inputArray.length-1; i++) {
            if (inputArray[i] == '+' && inputArray[i] == inputArray[i+1]) {
                char[] flip = inputArray.clone();
                flip[i] = '-';
                flip[i+1] = '-';
                result.add(String.valueOf(flip));
            }
        }
        return result;
    }
}

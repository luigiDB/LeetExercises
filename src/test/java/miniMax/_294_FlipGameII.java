package miniMax;

import leetfree._293_FlipGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Stack;

public class _294_FlipGameII {
    /*You are playing the following Flip Game with your friend: Given a string that contains only these two characters:
    + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no
    longer make a move and therefore the other person will be the winner.
    Write a function to determine if the starting player can guarantee a win.
    For example, given s = "++++", return true. The starting player can guarantee a win by flipping the
    middle "++" to become "+--+".
    Follow up:
    Derive your algorithm's runtime complexity.
    * */

    private _293_FlipGame flipGame;

    @Before
    public void setUp() throws Exception {
        flipGame = new _293_FlipGame();
    }

    @Test
    public void winFirst() {
        Assert.assertTrue(sureWin("++++"));
        Assert.assertTrue(sureWin("++"));
    }

    @Test
    public void winSecond() {
        Assert.assertFalse(sureWin("++-++"));
        /*
            0   ++-++
        1   1   ---++   ++---
        2   2   -----   -----
        3   1   lose
        * */
    }

    private boolean sureWin(String s) {
        Stack<Turn> nextTurn = new Stack<>();

        nextTurn.push(new Turn(s, 1));

        while (!nextTurn.isEmpty()) {
            Turn pop = nextTurn.pop();

            List<String> possibleMoves = flipGame.possibleFlips(pop.game);

            if (possibleMoves.isEmpty() && pop.turn % 2 == 0)
                return true;
            possibleMoves.stream()
                    .map(game -> new Turn(game, pop.turn + 1))
                    .forEach(turn -> nextTurn.push(turn));

        }

        return false;
    }

    private class Turn {
        String game;
        int turn;

        public Turn(String game, int turn) {
            this.game = game;
            this.turn = turn;
        }
    }
}

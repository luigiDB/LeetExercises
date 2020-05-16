package dynamicProgramming;

import java.util.*;

/*
 * Alice and Bob take turns playing a game, with Alice starting first.
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * Note:
 * <p>
 * 1 <= N <= 1000
 */
public class _1025_DivisorGame {

    Boolean[][] winnerMatrix;

    public boolean firstPlayerWin(int N) {
        winnerMatrix = new Boolean[N+1][2];
        boolean b = canWin(N, 0);
        return b;
    }

    private boolean canWin(int N, int player) {
        if(winnerMatrix[N][player] != null)
            return winnerMatrix[N][player];
        if(N == 1) {
            winnerMatrix[N][player] = false;
            return false;
        }

        List<Integer> divisors = getDivisors(N);

        boolean theOtherPlayerWin = true;
        for (Integer divisor : divisors) {
            theOtherPlayerWin &= canWin(N - divisor, (player + 1) % 2);
        }

        winnerMatrix[N][player] = !theOtherPlayerWin;
        return !theOtherPlayerWin;
    }

    private List<Integer> getDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if (n % i == 0)
                divisors.add(i);
        }
        return divisors;
    }

}

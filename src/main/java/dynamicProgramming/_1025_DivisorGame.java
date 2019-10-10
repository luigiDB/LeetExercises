package dynamicProgramming;

import java.util.*;

/**
 * Alice and Bob take turns playing a game, with Alice starting first.
 * <p>
 * Initially, there is a number N on the chalkboard.  On each player's turn, that player makes a move consisting of:
 * <p>
 * Choosing any x with 0 < x < N and N % x == 0.
 * Replacing the number N on the chalkboard with N - x.
 * Also, if a player cannot make a move, they lose the game.
 * <p>
 * Return True if and only if Alice wins the game, assuming both players play optimally.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: 2
 * Output: true
 * Explanation: Alice chooses 1, and Bob has no more moves.
 * Example 2:
 * <p>
 * Input: 3
 * Output: false
 * Explanation: Alice chooses 1, Bob chooses 1, and Alice has no more moves.
 * <p>
 * <p>
 * Note:
 * <p>
 * 1 <= N <= 1000
 */
public class _1025_DivisorGame {

    private Map<Pair, Boolean> results = new HashMap<>();

    public boolean firstPlayerWin(int N) {
        return nextMove(N, Player.ALICE);
    }

    private boolean nextMove(int N, Player p) {
        List<Integer> divisors = getDivisors(N);
        if(divisors.isEmpty()) {
            return p != Player.ALICE;
        }

        List<Boolean> results = new LinkedList<>();
        for (Integer i : divisors) {
            results.add(nextMove(N-i, (p == Player.ALICE)?Player.BOB:Player.ALICE));
        }
        return results.contains(true);
    }

    private List<Integer> getDivisors(int num) {
        List<Integer> divisors = new LinkedList<>();

        for (int i = num - 1; i > 0; i--) {
            if (num % i == 0)
                divisors.add(i);
        }

        return divisors;
    }

    private class Pair {
        int N;
        Player player;

        public Pair(int n, Player player) {
            N = n;
            this.player = player;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return N == pair.N &&
                    player == pair.player;
        }

        @Override
        public int hashCode() {
            return Objects.hash(N, player);
        }
    }

    enum Player {
        ALICE, BOB
    }
}

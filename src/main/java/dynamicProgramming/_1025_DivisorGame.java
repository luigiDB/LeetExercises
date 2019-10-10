package dynamicProgramming;

import java.util.*;

/**
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

    private Map<Pair, Boolean> resultMap = new HashMap<>();

    public boolean firstPlayerWin(int N) {
        return nextMove(N, Player.ALICE);
    }

    private boolean nextMove(int N, Player p) {
        List<Integer> divisors = getDivisors(N);
        if (divisors.isEmpty()) {
            return p != Player.ALICE;
        }

        List<Boolean> results = new LinkedList<>();
        for (Integer i : divisors) {
            int newN = N - i;
            Player newPlayer = (p == Player.ALICE) ? Player.BOB : Player.ALICE;
            Pair pairKey = new Pair(newN, newPlayer);
            if (resultMap.containsKey(pairKey))
                results.add(resultMap.get(pairKey));
            else {
                boolean nextMoveResult = nextMove(newN, newPlayer);
                resultMap.put(pairKey, nextMoveResult);
                results.add(nextMoveResult);
            }
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

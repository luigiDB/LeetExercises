package miniMax;

/*
Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has
a positive integer number of stones piles[i].
The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either
the beginning or the end of the row.  This continues until there are no more piles left, at which point the person
with the most stones wins.
Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
Example 1:
Input: [5,3,4,5]
Output: true
Explanation:
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.
Note:
2 <= piles.length <= 500
piles.length is even.
1 <= piles[i] <= 500
sum(piles) is odd.
 */
public class _877_StoneGame {

    public boolean stoneGame(int[] piles) {
        int minimax = minimax(piles, 0, piles.length - 1, true);
        return minimax > 0;
    }

    private int minimax(int[] piles, int startIndex, int endIndex, boolean player) {
        if (startIndex > endIndex)
            return 0;

        if (player) {
            int minimax1 = minimax(piles, startIndex + 1, endIndex, false) + piles[startIndex];
            int minimax2 = minimax(piles, startIndex, endIndex - 1, false) + piles[endIndex];
            return Math.max(minimax1,
                    minimax2);
        } else {
            int minimax1 = minimax(piles, startIndex + 1, endIndex, true) - piles[startIndex];
            int minimax2 = minimax(piles, startIndex, endIndex - 1, true) - piles[endIndex];
            return Math.min(minimax1,
                    minimax2);
        }
    }

    /*
    public boolean stoneGameWinLossTree(int[] piles) {
        Map<Status, WL> stateMap = new HashMap<>();

        return foo(piles, 0, 0, piles.length - 1, true).equals(WL.WIN);
    }

    private WL foo(int[] piles, int points, int startIndex, int endIndex, boolean player) {
        if(startIndex>endIndex) {

        }


        foo(piles, points + piles[startIndex], startIndex + 1, endIndex, !player);
        foo(piles, points + piles[endIndex], startIndex, endIndex - 1, !player);

    }

    private class Status {
        int startIndex;
        int endIndex;
        int playerPoints;
    }

    private enum WL {
        WIN, LOSS;
    }
     */
}

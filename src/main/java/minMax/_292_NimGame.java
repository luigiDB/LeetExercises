package minMax;

/*
You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you
take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first
turn to remove the stones.
Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can
win the game given the number of stones in the heap.
Example:
Input: 4
Output: false
Explanation: If there are 4 stones in the heap, then you will never win the game;
             No matter 1, 2, or 3 stones you remove, the last stone will always be
             removed by your friend.
 */
public class _292_NimGame {
    public boolean canWin(int n) {
        // these three booleans represent the states of last three numbers, as that's the only information we require
        // to calculate the value of next state.
        boolean p1 = true, p2 = true, p3 = true;

        for (int i = 4; i <= n; i++) {
            if (p1 & p2 & p3) { // check if any of the pi's is a losing state
                p1 = p2;
                p2 = p3;
                p3 = false; // if yes, the current state becomes winning state
            } else {
                p1 = p2;
                p2 = p3;
                p3 = true; // otherwise it's a losing state.
            }
        }
        return p3;
    }
}

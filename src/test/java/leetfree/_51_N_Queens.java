package leetfree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.containsInAnyOrder;

/**
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each
 * other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a
 * queen and an empty space, respectively.
 */
public class _51_N_Queens {

    private Solution solutionClass;

    @Before
    public void setUp() throws Exception {
        solutionClass = new Solution();
    }

    @Test
    public void a() {
        List<List<String>> solution = solutionClass.solveNQueens(1);
        Assert.assertThat(solution, containsInAnyOrder(
                List.of("Q")
        ));
    }

    @Test
    public void b() {
        List<List<String>> solution = solutionClass.solveNQueens(4);
        Assert.assertThat(solution, containsInAnyOrder(
                List.of(".Q..", "...Q", "Q...", "..Q."),
                List.of("..Q.", "Q...", "...Q", ".Q..")
        ));
    }

    class Solution {

        private int size;
        List<List<String>> result;

        public List<List<String>> solveNQueens(int n) {
            size = n;
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }

            result = new ArrayList<>();
            backtrack(0, new HashSet<>(), new HashSet<>(), new HashSet<>(), board);
            return result;
        }

        private void backtrack(int row, HashSet<Integer> diagonals, HashSet<Integer> antiDiagonals, HashSet<Integer> cols, char[][] state) {
            if (row == size) {
                result.add(createBoard(state));
                return;
            }

            for (int col = 0; col < size; col++) {
                int currDiag = row - col;
                int currAntiDiag = row + col;
                if (diagonals.contains(currDiag)
                        || antiDiagonals.contains(currAntiDiag)
                        || cols.contains(col))
                    continue;

                diagonals.add(currDiag);
                antiDiagonals.add(currAntiDiag);
                cols.add(col);
                state[row][col] = 'Q';

                backtrack(row + 1, diagonals, antiDiagonals, cols, state);

                state[row][col] = '.';
                cols.remove(col);
                antiDiagonals.remove(currAntiDiag);
                diagonals.remove(currDiag);

            }
        }

        private List<String> createBoard(char[][] state) {
            List<String> board = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < size; j++) {
                    sb.append(state[i][j]);
                }
                board.add(sb.toString());
            }
            return board;
        }
    }
}

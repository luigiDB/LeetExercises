package leetfree;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships
 * on board.
 * Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the
 * shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or
 * vertical cell separates between two battleships (i.e., there are no adjacent battleships).
 */
public class _419_BattleshipsInABoard {

    @Test
    public void a() {
        char[][] field = {
                {'X', '.', '.', 'X'},
                {'.', '.', '.', 'X'},
                {'.', '.', '.', 'X'}
        };
        assertEquals(2, countBattleships(field));
    }

    public int countBattleships(char[][] board) {
        int counter = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'X') {
                    counter++;
                    color(board, i, j);
                }
            }
        }

        return counter;
    }

    private void color(char[][] field, int row, int column) {
        field[row][column] = '\u0000';

        int currentColumn = column;
        while (currentColumn + 1 < field[row].length && field[row][currentColumn + 1] == 'X') {
            field[row][currentColumn + 1] = '\u0000';
            currentColumn++;
        }

        int currentRow = row;
        while (currentRow + 1 < field.length && field[currentRow + 1][column] == 'X') {
            field[currentRow + 1][column] = '\u0000';
            currentRow++;
        }
    }
}

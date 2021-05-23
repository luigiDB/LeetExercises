package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down,
 * left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next
 * direction.
 * Given the ball's start position, the destination and the maze, determine whether the ball could stop at the
 * destination.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that
 * the borders of the maze are all walls. The start and destination coordinates are represented by row and column
 * indexes.
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: false
 * Explanation: There is no way for the ball to stop at the destination.
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
 * border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class _490_TheMaze {

    private int[][] board = new int[][]{{0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}};

    @Test
    public void tests() {
        Assert.assertTrue(canWin(board, 0, 4, 4, 4));
        Assert.assertFalse(canWin(board, 0, 4, 3, 2));
    }

    public boolean canWin(int[][] board, int startX, int startY, int endX, int endY) {
        Set<Pair> explored = new HashSet<>();
        Queue<Point> toExplore = new LinkedList<>();

        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);

        toExplore.add(start);

        while (!toExplore.isEmpty()) {
            Point poll = toExplore.poll();
            List<Pair> possibilities = possibleDirections(board, poll);
            for (Pair pair : possibilities) {
                if (explored.contains(pair))
                    continue;
                Point next = move(board, pair);
                if (next.equals(end))
                    return true;
                explored.add(pair);
                toExplore.offer(next);
            }
        }
        return false;
    }

    private Point move(int[][] board, Pair direction) {
        Point current = direction.getP();

        while (board[current.getX()][current.getY()] == 0) {
            switch (direction.getDir()) {
                case RIGTH:
                    if (current.getY() != (board[0].length - 1) && board[current.getX()][current.getY() + 1] == 0)
                        current = new Point(current.getX(), current.getY() + 1);
                    else
                        return current;
                    break;
                case UP:
                    if (current.getX() != 0 && board[current.getX() - 1][current.getY()] == 0)
                        current = new Point(current.getX() - 1, current.getY());
                    else
                        return current;
                    break;
                case LEFT:
                    if (current.getY() != 0 && board[current.getX()][current.getY() - 1] == 0)
                        current = new Point(current.getX(), current.getY() - 1);
                    else
                        return current;
                    break;
                case DOWN:
                    if (current.getX() != (board.length - 1) && board[current.getX() + 1][current.getY()] == 0)
                        current = new Point(current.getX() + 1, current.getY());
                    else
                        return current;
                    break;
            }
        }

        return current;
    }

    private List<Pair> possibleDirections(int[][] board, Point p) {
        List<Pair> possibilities = new LinkedList<>();

        if (p.getY() != (board[0].length - 1) && board[p.getX()][p.getY() + 1] != 1)
            possibilities.add(new Pair(p, Direction.RIGTH));

        if (p.getY() != 0 && board[p.getX()][p.getY() - 1] != 1)
            possibilities.add(new Pair(p, Direction.LEFT));

        if (p.getX() != 0 && board[p.getX() - 1][p.getY()] != 1)
            possibilities.add(new Pair(p, Direction.UP));

        if (p.getX() != (board.length - 1) && board[p.getX() + 1][p.getY()] != 1)
            possibilities.add(new Pair(p, Direction.DOWN));

        return possibilities;
    }

    class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x &&
                    y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    enum Direction {
        RIGTH, UP, LEFT, DOWN;
    }

    class Pair {
        private Point p;
        private Direction dir;

        public Pair(Point p, Direction dir) {
            this.p = p;
            this.dir = dir;
        }

        public Point getP() {
            return p;
        }

        public Direction getDir() {
            return dir;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return p.equals(pair.p) &&
                    dir == pair.dir;
        }

        @Override
        public int hashCode() {
            return Objects.hash(p, dir);
        }
    }
}

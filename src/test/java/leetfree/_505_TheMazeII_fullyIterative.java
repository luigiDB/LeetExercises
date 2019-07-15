package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left
 * or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * <p>
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the
 * destination. The distance is defined by the number of empty spaces traveled by the ball from the start position
 * (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 * <p>
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * <p>
 * Example 1
 * <p>
 * Input 1: a maze represented by a 2D array
 * <p>
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * <p>
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * <p>
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 * The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * <p>
 * Example 2
 * <p>
 * Input 1: a maze represented by a 2D array
 * <p>
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * <p>
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * <p>
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 * <p>
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
 * border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class _505_TheMazeII_fullyIterative {

    private int[][] board = new int[][]{{0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 1},
            {0, 0, 0, 0, 0}};

    @Test
    public void tests() {
        Assert.assertEquals(12, canWin(board, 0, 4, 4, 4));
        Assert.assertEquals(-1, canWin(board, 0, 4, 3, 2));
    }

    public int canWin(int[][] board, int startX, int startY, int endX, int endY) {
        LinkedList<Pair> explored = new LinkedList<>();

        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);

        PriorityQueue<Integer> possibleDistances = new PriorityQueue<>();
        recursiveSearch(board, explored, start, end, 0, possibleDistances);

        return (possibleDistances.size()==0)?-1:possibleDistances.poll();
    }

    private void recursiveSearch(int[][] board, LinkedList<Pair> explored, Point start, Point end, int pathLength, PriorityQueue<Integer> lenghts) {

        if(start.equals(end)) {
            lenghts.add(pathLength);
        }

        List<Pair> possible = possibleDirections(board, start);
        for (Pair p : possible) {
            if(explored.contains(p))
                continue;

            PairDistance dist = move(board, p);
            explored.add(p);

            recursiveSearch(board, explored, dist.getP(), end, pathLength+dist.getDistance(), lenghts);

            explored.remove(p);
        }

    }


    private PairDistance move(int[][] board, Pair direction) {
        Point current = direction.getP();
        int steps = 0;

        while (board[current.getX()][current.getY()] == 0) {
            switch (direction.getDir()) {
                case RIGTH:
                    if (current.getY() != (board[0].length - 1) && board[current.getX()][current.getY() + 1] == 0) {
                        current = new Point(current.getX(), current.getY() + 1);
                        steps++;
                    } else
                        return new PairDistance(current, steps);
                    break;
                case UP:
                    if (current.getX() != 0 && board[current.getX() - 1][current.getY()] == 0) {
                        current = new Point(current.getX() - 1, current.getY());
                        steps++;
                    } else
                        return new PairDistance(current, steps);
                    break;
                case LEFT:
                    if (current.getY() != 0 && board[current.getX()][current.getY() - 1] == 0) {
                        current = new Point(current.getX(), current.getY() - 1);
                        steps++;
                    } else
                        return new PairDistance(current, steps);
                    break;
                case DOWN:
                    if (current.getX() != (board.length - 1) && board[current.getX() + 1][current.getY()] == 0) {
                        current = new Point(current.getX() + 1, current.getY());
                        steps++;
                    } else
                        return new PairDistance(current, steps);
                    break;
            }
        }

        return null;
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

    private class PairDistance {

        private Point p;

        public Point getP() {
            return p;
        }

        public int getDistance() {
            return distance;
        }

        private int distance;

        public PairDistance(Point p, int distance) {
            this.p = p;
            this.distance = distance;
        }
    }
}

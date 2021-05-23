package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left
 * or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
 * Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the
 * destination. The distance is defined by the number of empty spaces traveled by the ball from the start position
 * (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.
 * The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the
 * borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.
 * Example 1
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (4, 4)
 * Output: 12
 * Explanation: One shortest way is : left -> down -> left -> down -> right -> down -> right.
 * The total distance is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 * Example 2
 * Input 1: a maze represented by a 2D array
 * 0 0 1 0 0
 * 0 0 0 0 0
 * 0 0 0 1 0
 * 1 1 0 1 1
 * 0 0 0 0 0
 * Input 2: start coordinate (rowStart, colStart) = (0, 4)
 * Input 3: destination coordinate (rowDest, colDest) = (3, 2)
 * Output: -1
 * Explanation: There is no way for the ball to stop at the destination.
 * Note:
 * There is only one ball and one destination in the maze.
 * Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
 * The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the
 * border of the maze are all walls.
 * The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
 */
public class _505_TheMazeII {


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

    /**
     * It actually uses the Dijkstra to travel the maze
     */
    public int canWin(int[][] board, int startX, int startY, int endX, int endY) {
        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);

        int[][] distanceBoard = new int[board.length][board[0].length];
        for (int[] ints : distanceBoard) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        distanceBoard[startX][startY] = 0;

        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.comparingInt(Pair::getDistance));

        queue.add(new Pair(start, 0));

        while (!queue.isEmpty()) {
            Pair poll = queue.poll();
            List<Pair> nexts = possibleDirections(board, poll.getPoint());
            for (Pair p : nexts) {
                if (distanceBoard[p.getPoint().getX()][p.getPoint().getY()] > distanceBoard[poll.getPoint().getX()][poll.getPoint().getY()] + p.getDistance()) {
                    distanceBoard[p.getPoint().getX()][p.getPoint().getY()] = distanceBoard[poll.getPoint().getX()][poll.getPoint().getY()] + p.getDistance();
                    queue.offer(p);
                }

            }
        }

        return (distanceBoard[endX][endY]==Integer.MAX_VALUE)
                ? -1
                : distanceBoard[endX][endY];
    }

    private Pair move(int[][] board, Point start, Direction direction) {
        Point current = start;
        int dist = 0;

        while (board[current.getX()][current.getY()] == 0) {
            switch (direction) {
                case RIGTH:
                    if (current.getY() != (board[0].length - 1) && board[current.getX()][current.getY() + 1] == 0) {
                        current = new Point(current.getX(), current.getY() + 1);
                        dist++;
                    } else
                        return new Pair(current, dist);
                    break;
                case UP:
                    if (current.getX() != 0 && board[current.getX() - 1][current.getY()] == 0) {
                        current = new Point(current.getX() - 1, current.getY());
                        dist++;
                    } else
                        return new Pair(current, dist);
                    break;
                case LEFT:
                    if (current.getY() != 0 && board[current.getX()][current.getY() - 1] == 0) {
                        current = new Point(current.getX(), current.getY() - 1);
                        dist++;
                    } else
                        return new Pair(current, dist);
                    break;
                case DOWN:
                    if (current.getX() != (board.length - 1) && board[current.getX() + 1][current.getY()] == 0) {
                        current = new Point(current.getX() + 1, current.getY());
                        dist++;
                    } else
                        return new Pair(current, dist);
                    break;
            }
        }
        return null;
    }

    private List<Pair> possibleDirections(int[][] board, Point p) {
        List<Pair> possibilities = new LinkedList<>();

        if (p.getY() != (board[0].length - 1) && board[p.getX()][p.getY() + 1] != 1)
            possibilities.add(move(board, p, Direction.RIGTH));

        if (p.getY() != 0 && board[p.getX()][p.getY() - 1] != 1)
            possibilities.add(move(board, p, Direction.LEFT));

        if (p.getX() != 0 && board[p.getX() - 1][p.getY()] != 1)
            possibilities.add(move(board, p, Direction.UP));

        if (p.getX() != (board.length - 1) && board[p.getX() + 1][p.getY()] != 1)
            possibilities.add(move(board, p, Direction.DOWN));

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
    }

    enum Direction {
        RIGTH, UP, LEFT, DOWN;
    }

    class Pair {
        private Point p;
        private int distance;

        public Pair(Point p, int dir) {
            this.p = p;
            this.distance = dir;
        }

        public Point getPoint() {
            return p;
        }

        public int getDistance() {
            return distance;
        }
    }
}

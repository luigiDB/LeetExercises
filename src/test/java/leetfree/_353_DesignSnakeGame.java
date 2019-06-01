package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class _353_DesignSnakeGame {
    /*Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.
    The snake is initially positioned at the top left corner (0,0) with length = 1 unit.
    You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.
    Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.
    When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.*/

    @Test
    public void simpleTest() {
        LinkedList<Point> food = new LinkedList<>();
        food.add(new Point(1, 2));
        food.add(new Point(0, 1));
        food.add(new Point(1, 2));
        Snake snake = new Snake(3, 2, food);
        Assert.assertEquals(0, snake.move('R')); //Returns 0
        Assert.assertEquals(0, snake.move('D')); //Returns 0
        Assert.assertEquals(1, snake.move('R')); //Returns 1 (Snake eats the first food and right after that, the second food appears at (0, 1) )
        Assert.assertEquals(1, snake.move('U')); //Returns 1
        Assert.assertEquals(2, snake.move('L')); //Returns 2 (Snake eats the second food)
        Assert.assertEquals(-1, snake.move('U')); //Returns - 1 (Game over because snake collides with border)
    }

    @Test
    public void check() {
        LinkedList<Point> food = new LinkedList<>();
        food.add(new Point(1, 2));
        food.add(new Point(0, 1));
        Snake snake = new Snake(3, 2, food);
        snake.printGameBoard();
    }

    private class Snake {
        private final int width;
        private final int height;
        private final List<Point> food;
        private int currentFood;
        private int snakeLen;
        private final List<Point> snake;
        private final Point[][] playingMat;

        public Snake(int width, int height, List<Point> food) {
            this.width = width;
            this.height = height;
            this.food = food;
            currentFood = 0;

            snakeLen = 1;
            snake = new LinkedList<>();
            playingMat = new Point[height][width];
            for (int i = 0; i < playingMat.length; i++) {
                for (int j = 0; j < playingMat[i].length; j++) {
                    playingMat[i][j] = new Point(i, j);
                }
            }

            playingMat[0][0].setFill("S");
            snake.add(playingMat[0][0]);
            playingMat[food.get(currentFood).getX()][food.get(currentFood).getY()].setFill("F");
        }

        public int move(char r) {
            Point next = nextHeadPosition(snake.get(0), r);

            //if next is null endgame
            if (next == null)
                return -1;

            //if the snake is not elongated this turn we need to remove the tail
            if(snakeLen == snake.size()) {
                Point snakeTail = snake.get(snake.size()-1);
                playingMat[snakeTail.getX()][snakeTail.getY()].setFill("-");
                snake.remove(snakeTail);
            }

            //move head of snake
            snake.add(0, next);
            playingMat[next.getX()][next.getY()].setFill("S");

            //if reached food put the new food in the board and enlarge snake
            if (snake.get(0).equals(playingMat[food.get(currentFood).getX()][food.get(currentFood).getY()])) {
                snakeLen++;
                playingMat[food.get(currentFood).getX()][food.get(currentFood).getY()].setFill("S");
                currentFood++;
                playingMat[food.get(currentFood).getX()][food.get(currentFood).getY()].setFill("F");
            }


            printGameBoard();
            return currentFood;
        }

        public void printGameBoard() {
            System.out.println("------------------------------");
            for (int i = 0; i < playingMat.length; i++) {
                for (int j = 0; j < playingMat[i].length; j++) {
                    System.out.print(playingMat[i][j].getFill() + '\t');
                }
                System.out.println();
            }
        }

        private Point nextHeadPosition(Point current, char move) {
            try {
                switch (move) {
                    case 'U':
                        return playingMat[current.getX() - 1][current.getY()];
                    case 'D':
                        return playingMat[current.getX() + 1][current.getY()];
                    case 'L':
                        return playingMat[current.getX()][current.getY() - 1];
                    case 'R':
                        return playingMat[current.getX()][current.getY() + 1];
                }
            } catch (ArrayIndexOutOfBoundsException exception) {
                return null;
            }
            return null;
        }

    }

    private class Point {

        private final int x;
        private final int y;
        private String fill;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
            this.fill = "-";
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

        public String getFill() {
            return fill;
        }

        public void setFill(String fill) {
            this.fill = fill;
        }
    }
}

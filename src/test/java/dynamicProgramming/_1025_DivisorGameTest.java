package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class _1025_DivisorGameTest {

    private _1025_DivisorGame divisorGame;

    @Before
    public void setUp() {
        divisorGame = new _1025_DivisorGame();
    }

    @Test
    public void test2() {
        Assert.assertTrue(divisorGame.firstPlayerWin(2));
    }

    @Test
    public void test3() {
        Assert.assertFalse(divisorGame.firstPlayerWin(3));
    }

    @Test
    public void test4() {
        Assert.assertTrue(divisorGame.firstPlayerWin(4));
    }

    @Test
    public void test5() {
        Assert.assertFalse(divisorGame.firstPlayerWin(5));
    }
}
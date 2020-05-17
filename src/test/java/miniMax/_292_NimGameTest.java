package miniMax;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _292_NimGameTest {

    private _292_NimGame nimGame;

    @Before
    public void setUp() throws Exception {
        nimGame = new _292_NimGame();
    }

    @Test
    public void basicTests() {
        Assert.assertTrue(nimGame.canWin(1));
        Assert.assertTrue(nimGame.canWin(2));
        Assert.assertTrue(nimGame.canWin(3));
        Assert.assertFalse(nimGame.canWin(4));
        Assert.assertTrue(nimGame.canWin(5));
        Assert.assertTrue(nimGame.canWin(6));
        Assert.assertTrue(nimGame.canWin(7));
        Assert.assertFalse(nimGame.canWin(8));
    }
}
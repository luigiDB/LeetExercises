package minMax;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _877_StoneGameTest {

    private _877_StoneGame stoneGame;

    @Before
    public void setUp() throws Exception {
        stoneGame = new _877_StoneGame();
    }

    @Test
    public void given() {
        Assert.assertTrue(stoneGame.stoneGame(new int[]{5, 3, 4, 5}));
    }
}
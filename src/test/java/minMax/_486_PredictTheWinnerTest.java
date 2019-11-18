package minMax;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _486_PredictTheWinnerTest {

    private _486_PredictTheWinner predictTheWinner;

    @Before
    public void setUp() throws Exception {
        predictTheWinner = new _486_PredictTheWinner();
    }

    @Test
    public void falseTest() {
        Assert.assertFalse(predictTheWinner.PredictTheWinner(new int[]{1, 5, 2}));
    }

    @Test
    public void trueTest() {
        Assert.assertTrue(predictTheWinner.PredictTheWinner(new int[]{1, 5, 233, 7}));
    }
}
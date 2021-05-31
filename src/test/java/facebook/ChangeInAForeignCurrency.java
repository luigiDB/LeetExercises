package facebook;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * You likely know that different currencies have coins and bills of different denominations. In some currencies, it's
 * actually impossible to receive change for a given amount of money. For example, Canada has given up the 1-cent penny.
 * If you're owed 94 cents in Canada, a shopkeeper will graciously supply you with 95 cents instead since there exists
 * a 5-cent coin.
 * Given a list of the available denominations, determine if it's possible to receive exact change for an amount of
 * money targetMoney. Both the denominations and target amount will be given in generic units of that currency.
 */
public class ChangeInAForeignCurrency {

    @Test
    public void a() {
        int target_1 = 94;
        int[] arr_1 = {5, 10, 25, 100, 200};
        assertFalse(canGetExactChange(target_1, arr_1));
    }

    @Test
    public void b() {
        int target_2 = 75;
        int[] arr_2 = {4, 17, 29};
        assertTrue(canGetExactChange(target_2, arr_2));
    }

    // Add any helper functions you may need here
    boolean useOneCoin(int amount, int[] values) {

        if (amount == 0)
            return true;
        if (amount < 0)
            return false;

        boolean oneIsValid = false;
        for (int coin : values) {
            oneIsValid |= useOneCoin(amount - coin, values);
        }
        return oneIsValid;
    }

    boolean canGetExactChange(int targetMoney, int[] denominations) {
        // Write your code here
        return useOneCoin(targetMoney, denominations);
    }
}

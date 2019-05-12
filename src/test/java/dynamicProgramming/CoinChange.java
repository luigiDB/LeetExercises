package dynamicProgramming;

import org.junit.Test;

/**
 * https://www.geeksforgeeks.org/coin-change-dp-7/
 */
public class CoinChange {


    @Test
    public void CoinChange() {
        int N = 4;
        int s[] = new int[]{1, 2, 3};

        System.out.println(possibleChange(N, s));
    }

    @Test
    public void CoinChange2() {
        int N = 10;
        int s[] = new int[]{2, 3, 5, 6};

        System.out.println(possibleChange(N, s));
    }

    private int possibleChange(int N, int s[]) {
        int support[] = new int[N + 1];
        support[0] = 1;

        for (int i = 0; i < s.length; i++) {
            for (int j = s[i]; j <= N; j++) {
                support[j] += support[j - s[i]];
            }
        }
        return support[N];
    }

}

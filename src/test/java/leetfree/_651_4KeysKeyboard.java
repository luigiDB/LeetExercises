package leetfree;

/**
 * Imagine you have a special keyboard with the following keys:
 * Key 1: (A): Print one 'A' on screen.
 * Key 2: (Ctrl-A): Select the whole screen.
 * Key 3: (Ctrl-C): Copy selection to buffer.
 * Key 4: (Ctrl-V): Print buffer on screen appending it after what has already been printed.
 * Now, you can only press the keyboard for N times (with the above four keys), find out the maximum numbers of 'A' you can print on screen.
 *
 * Example 1:
 * Input: N = 3
 * Output: 3
 * Explanation:
 * We can at most get 3 A's on screen by pressing following key sequence:
 * A, A, A
 * Example 2:
 * Input: N = 7
 * Output: 9
 * Explanation:
 * We can at most get 9 A's on screen by pressing following key sequence:
 * A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 * Note:
 * 1 <= N <= 50
 * Answers will be in the range of 32-bit signed integer.
 */
public class _651_4KeysKeyboard {

    /**
     * The best solution can be greedy calculated considering thatç
     * 1_ Multiply two times is never useful ca+cc+cv+cv is always better than ca+cc+cv+ca+cc+cv 2 less step
     * 2_ Considering the point 1 the best solution is always beginning with some As and terminate with some cv this
     * means that in linear time it's possible to evaluate all possible solutions given N
     *
     * Evaluating the est results
     * N    1   2   3   4   5   6   7   8   9
     * Best 1   2   3   4   5   6   9   12  16
     *
     * We can notice that for N > 7 the Best combination is (N-5)*4
     * eg. Best(8) = Best(8-5) * 4 = Best(3) * 4 = 3 * 4 = 12
     */
}

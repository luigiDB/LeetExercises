package dynamicProgramming;

/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.
Note:
n and k are non-negative integers.
Example:
Input: n = 3, k = 2
Output: 6
Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
            post1  post2  post3
 -----      -----  -----  -----
   1         c1     c1     c2
   2         c1     c2     c1
   3         c1     c2     c2
   4         c2     c1     c1
   5         c2     c1     c2
   6         c2     c2     c1
 */
public class _276_PaintFence {
    /*
    One idea could be to be iteratively calculate all the possible outcomes multiplying the previous results by K
    deleting all non vaid outcomes. Extremely space inefficient
    Or you can notice that
    n=1, k=3 = 3
    n=2, k=3 = 9
    n=3, k=3 = 24
    noticing that at each step N if N-1 == N-2 I have (k-1) possibilities and k in the other case
    f(N) = K * (k-1) * K + K * 1 * (K-1)
    if we write down some results
    f(1) = K
    f(2) = K^2
    f(3) = K * (k-1) * K + K * 1 * (K-1) = (K-1) * (K^2 + K) = (K-1) * (f(2)+f(1))
    ...
    f(N) = (K-1) * (f(N-1)+f(N-2))
     */
}

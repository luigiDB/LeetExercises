package leetfree;
/*
Your car starts at position 0 and speed +1 on an infinite number line.  (Your car can go into negative positions.)
Your car drives automatically according to a sequence of instructions A (accelerate) and R (reverse).
When you get an instruction "A", your car does the following: position += speed, speed *= 2.
When you get an instruction "R", your car does the following: if your speed is positive then speed = -1 , otherwise speed = 1.  (Your position stays the same.)
For example, after commands "AAR", your car goes to positions 0->1->3->3, and your speed goes to 1->2->4->-1.
Now for some target position, say the length of the shortest sequence of instructions to get there.
Example 1:
Input:
target = 3
Output: 2
Explanation:
The shortest instruction sequence is "AA".
Your position goes from 0->1->3.
Example 2:
Input:
target = 6
Output: 5
Explanation:
The shortest instruction sequence is "AAARA".
Your position goes from 0->1->3->7->7->6.
Note:
1 <= target <= 10000.
 */
public class __818_RaceCar {
    /**
     * Naive approach: iteratively try every possible combination; at each step try add an A and an R
     * Realistic approach:
     * 1_   decide a direction so start with a R in case target is negative
     * 2_   add A until P(i) < target < P(i+1)
     *      at that point there are two possible routes
     *      a_  from P(i) add RR and restart until point 2
     *      b_  from P(i+1) add RR and restart from step 2 but with opposite direction (aka until P(i+1) < target < P(i))
     * eg to demonstrate the approach with target 8
     */
     /*     0   A   A   A   A
            0   1   3   7   15
            1   2   4   8   16
                                R   A   A   A           start with an R from 15 4 steps
                                15  14  12  8
                                -1  -2  -4  -8
                            R   R   A                   start with RR from 7 3 steps
                            7   7   8
                            -1  1   2
            So we have AAAARAAA if we came back from 15 or AAARRA if we reset the progression at 7
     */
}

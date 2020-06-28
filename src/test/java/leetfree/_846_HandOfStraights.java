package leetfree;

/*
Alice has a hand of cards, given as an array of integers.
Now she wants to rearrange the cards into groups so that each group is size W, and consists of W consecutive cards.
Return true if and only if she can.
Example 1:
Input: hand = [1,2,3,6,2,3,4,7,8], W = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8].
Example 2:
Input: hand = [1,2,3,4,5], W = 4
Output: false
Explanation: Alice's hand can't be rearranged into groups of 4.
Constraints:
1 <= hand.length <= 10000
0 <= hand[i] <= 10^9
1 <= W <= hand.length
Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */

/**
 * Same as es 1296
 * Similar to {@link __659_SplitArrayIntoConsecutiveSubsequences}
 */
public class _846_HandOfStraights {


    /*python solution using heap
    I was asked during an interview last week, I got a followup asking me to print the "straight array" as well, here is my approach.

    Min Heap(priority queue)
    + put the numbers into a min heap
    + pop the heap and put the item into a "straight" array if the pop == straight[-1] + 1, else put the poped item into a dump array
    + if the straight array is of size W, put the dumps back to the heap
    Time O(N * logN * W)
    Space O(N)

    class Solution(object):
        def straightHands(self, hand, W):
            pq = []
            for num in hand:
                heapq.heappush(pq, num)
            straights = []
            while len(pq) > 0:
                straight = []
                dumps = []
                while len(pq) > 0 and len(straight) < W:
                    pop = heapq.heappop(pq)
                    if len(straight) == 0 or pop == straight[-1] + 1:
                        straight.append(pop)
                    else:
                        dumps.append(pop)
                straights.append(straight)
                if len(straight) < W:
                    return []
                else:
                    for dump in dumps:
                        heapq.heappush(pq, dump)
            return straights
     */

}

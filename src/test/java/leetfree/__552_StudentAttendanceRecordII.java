package leetfree;

/*
Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
A student attendance record is a string that only contains the following three characters:
'A' : Absent.
'L' : Late.
'P' : Present.
A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
Example 1:
Input: n = 2
Output: 8
Explanation:
There are 8 records with length 2 will be regarded as rewardable:
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
Only "AA" won't be regarded as rewardable owing to more than one absent times.
Note: The value of n won't exceed 100,000.
 */
public class __552_StudentAttendanceRecordII {

    public class BruteForceSolution {
        int count, M = 1000000007;

        public int checkRecord(int n) {
            count = 0;
            gen("", n);
            return count;
        }

        public void gen(String s, int n) {
            if (n == 0 && checkRecord(s))
                count = (count + 1) % M;
            else if (n > 0) {
                gen(s + "A", n - 1);
                gen(s + "P", n - 1);
                gen(s + "L", n - 1);
            }
        }

        public boolean checkRecord(String s) {
            int count = 0;
            for (int i = 0; i < s.length() && count < 2; i++)
                if (s.charAt(i) == 'A')
                    count++;
            return s.length() > 0 && count < 2 && s.indexOf("LLL") < 0;
        }
    }

    /**
     * The given problem can be solved easily if we can develop a recurring relation for it.
     * Firstly, assume the problem to be considering only the characters LL and PP in the strings. i.e. The strings can contain only LL and PP. The effect of AA will be taken into account later on.
     * In order to develop the relation, let's assume that f[n]f[n] represents the number of possible rewardable strings(with LL and PP as the only characters) of length nn. Then, we can easily determine the value of f[n]f[n] if we know the values of the counts for smaller values of nn. To see how it works, let's examine the figure below:
     * Recurrence
     * The above figure depicts the division of the rewardable string of length nn into two strings of length n-1n−1 and ending with LL or PP. The string ending with PP of length nn is always rewardable provided the string of length n-1n−1 is rewardable. Thus, this string accounts for a factor of f[n-1]f[n−1] to f[n]f[n].
     * For the first string ending with LL, the rewardability is dependent on the further strings of length n-3n−3. Thus, we consider all the rewardable strings of length n-3n−3 now. Out of the four combinations possible at the end, the fourth combination, ending with a LLLL at the end leads to an unawardable string. But, since we've considered only rewardable strings of length n-3n−3, for the last string to be rewardable at length n-3n−3 and unawardable at length n-1n−1, it must be preceded by a PP before the LLLL.
     * Thus, accounting for the first string again, all the rewardable strings of length n-1n−1, except the strings of length n-4n−4 followed by PLLPLL, can contribute to a rewardable string of length nn. Thus, this string accounts for a factor of f[n-1] - f[n-4]f[n−1]−f[n−4] to f[n]f[n].
     * Thus, the recurring relation becomes:
     * f[n] = 2f[n-1] - f[n-4]f[n]=2f[n−1]−f[n−4]
     * We store all the f[i]f[i] values in an array. In order to compute f[i]f[i], we make use of a recursive function func(n) which makes use of the above recurrence relation.
     * Now, we need to put the factor of character AA being present in the given string. We know, atmost one AA is allowed to be presnet in a rewardable string. Now, consider the two cases.
     * No AA is present: In this case, the number of rewardable strings is the same as f[n]f[n].
     * A single AA is present: Now, the single AA can be present at any of the nn positions. If the AA is present at the
     * i^{th}i*th position in the given string, in the form: "<(i-1) characters>, A, <(n-i) characters>", the total number of rewardable strings is given by: f[i-1] * f[n-i]f[i−1]∗f[n−i]. Thus, the total number of such substrings is given by: \sum_{i=1}^{n} (f[i-1] * f[n-i])∑
     * i=1
     * n
     * ​
     *  (f[i−1]∗f[n−i]).
     */
    public class DynamicProgramming {
        int M = 1000000007;

        public int checkRecord(int n) {
            int[] f = new int[n + 1];
            f[0] = 1;
            for (int i = 1; i <= n; i++)
                f[i] = func(i);
            int sum = func(n);
            for (int i = 1; i <= n; i++) {
                sum += (f[i - 1] * f[n - i]) % M;
            }
            return sum % M;
        }

        public int func(int n) {
            if (n == 0)
                return 1;
            if (n == 1)
                return 2;
            if (n == 2)
                return 4;
            if (n == 3)
                return 7;
            return (2 * func(n - 1) - func(n - 4)) % M;
        }
    }
}

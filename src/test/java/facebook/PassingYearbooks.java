package facebook;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * There are n students, numbered from 1 to n, each with their own yearbook. They would like to pass their yearbooks
 * around and get them signed by other students.
 * You're given a list of n integers arr[1..n], which is guaranteed to be a permutation of 1..n (in other words, it
 * includes the integers from 1 to n exactly once each, in some order). The meaning of this list is described below.
 * Initially, each student is holding their own yearbook. The students will then repeat the following two steps each
 * minute: Each student i will first sign the yearbook that they're currently holding (which may either belong to
 * themselves or to another student), and then they'll pass it to student arr[i-1]. It's possible that arr[i-1] = i
 * for any given i, in which case student i will pass their yearbook back to themselves. Once a student has received
 * their own yearbook back, they will hold on to it and no longer participate in the passing process.
 * It's guaranteed that, for any possible valid input, each student will eventually receive their own yearbook back
 * and will never end up holding more than one yearbook at a time.
 * You must compute a list of n integers output, whose element at i-1 is equal to the number of signatures that will
 * be present in student i's yearbook once they receive it back.
 */
public class PassingYearbooks {

    @Test
    public void a() {
        Assert.assertArrayEquals(new int[]{2,2}, findSignatureCounts(new int[]{2,1}));
        Assert.assertArrayEquals(new int[]{1,1}, findSignatureCounts(new int[]{1,2}));
    }

    int[] findSignatureCounts(int[] arr) {
        // Write your code here
        int[] res = new int[arr.length];
        int[] copy = Arrays.copyOf(arr, arr.length);

        boolean run = true;
        while(run) {
            int[] support = new int[arr.length] ;
            run = false;
            for(int i = 0; i< arr.length; i++ ) {
                if(copy[i] != i+1)
                    run = true;
                res[copy[i]-1]++;
                support[copy[i]-1] = copy[i];
            }
            copy = support;
        }
        return res;
    }
}

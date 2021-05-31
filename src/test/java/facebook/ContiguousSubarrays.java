package facebook;

/**
 * You are given an array arr of N integers. For each index i, you are required to determine the number of contiguous
 * subarrays that fulfill the following conditions:
 * The value at index i must be the maximum element in the contiguous subarrays, and
 * These contiguous subarrays must either start from or end on index i.
 */
public class ContiguousSubarrays {


    int[] countSubarrays(int[] arr) {
        // Write your code here
        int[] res = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            res[i] = 1;
            for(int j = i-1; j >= 0; j--)
                if(arr[j]<arr[i])
                    res[i]++;
                else
                    break;
            for(int j = i+1; j < arr.length; j++)
                if(arr[j]<arr[i])
                    res[i]++;
                else
                    break;
        }
        return res;
    }

}

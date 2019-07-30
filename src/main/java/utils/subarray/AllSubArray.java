package utils.subarray;

import java.util.LinkedList;
import java.util.List;

public class AllSubArray {

    private int[] input;

    public AllSubArray(int[] input) {
        this.input = input;
    }

    public List<int[]> generate() {
        List<int[]> result = new LinkedList<>();

        int length = input.length;
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                int[] newArray = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    newArray[k - i] = input[k];
                }
                result.add(newArray);
            }
        }

        return result;
    }
}

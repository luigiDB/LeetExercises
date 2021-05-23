package utils.searching;

public class BinarySearch {
    public static int search(int[] arr, int val) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int pivot = (left + right) / 2;
            if (arr[pivot] == val)
                return pivot;
            if (arr[pivot] < val)
                left = pivot + 1;
            else
                right = pivot - 1;
        }
        return -1;
    }

    public static int firstOccurrence(int[] arr, int val) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int pivot = (left + right) / 2;

            if (arr[pivot] >= val)
                right = pivot - 1;
            else {
                left = pivot + 1;
            }
        }
        if(left >=arr.length || arr[left] != val)
            return -1;
        else
            return left;
    }

    public static int lastOccurrence(int[] arr, int val) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int pivot = (left + right) / 2;

            if (arr[pivot] <= val)
                left = pivot + 1;
            else
                right = pivot - 1;
        }

        if(right >=arr.length || arr[right] != val)
            return -1;
        else
            return right;
    }
}

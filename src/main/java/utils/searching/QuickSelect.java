package utils.searching;

import java.util.Random;

public class QuickSelect {

    public static int findKthSmallest(int[] arr, int k) {
        return quickSelect(arr, 0, arr.length - 1, k);
    }

    private static int quickSelect(int[] arr, int left, int right, int k) {
        if (left == right)
            return arr[left];

        int pIndex = rand(left, right);

        pIndex = lomutoPartition(arr, left, right, pIndex);
        if (k == pIndex)
            return arr[k];
        else if (k < pIndex)
            return quickSelect(arr, left, pIndex - 1, k);
        else
            return quickSelect(arr, pIndex + 1, right, k);
    }

    /**
     * Lomuto partition scheme
     * Fails with duplicates ???
     */
    private static int lomutoPartition(int[] arr, int left, int right, int pIndex) {
        int pivot = arr[pIndex];
        swap(arr, pIndex, right);

        pIndex = left;
        for (int i = left; i < right; i++) {
            if (arr[i] <= pivot) {
                swap(arr, i, pIndex);
                pIndex++;
            }
        }
        swap(arr, pIndex, right);
        return pIndex;
    }

    /**
     * Partition using Hoare's Partitioning scheme
     */
    private static int hoarePartition(int[] a, int low, int high) {
        int pivot = a[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do {
                i++;
            } while (a[i] < pivot);

            do {
                j--;
            } while (a[j] > pivot);

            if (i >= j) {
                return j;
            }

            swap(a, i, j);
        }
    }

    private static int rand(int left, int right) {
        int displacement = new Random().nextInt(right - left);
        return left + displacement;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

package utils.tree;

/**
 * This structure permit to obtain logaritmic cost for both update of a value and sum over a range on an array
 * aka. Fenwick tree
 * For matrix example {@link leetfree._308_RangeSumQuery2D_Mutable}
 */
@SuppressWarnings("JavadocReference")
public class BinaryIndexedTree {

    private int size;
    private int[] array;
    private int[] indexArray;

    public BinaryIndexedTree(int[] array) {
        this.array = array;
        size = array.length;
        indexArray = new int[size + 1];

        for (int i = 0; i < size; i++) {
            update(i, array[i]);
        }
    }

    public int getSum(int startIndex, int stopIndex) {
        return getSum(stopIndex) - getSum(startIndex - 1);
    }

    private int getSum(int index) {
        int sum = 0;
        index = index + 1;

        while (index > 0) {//important index must never become zero otherwise (index & (-index)) will loop.
            sum += indexArray[index];
            index -= (index & (-index));
        }

        return sum;
    }

    public void update(int position, int additiveValue) {
        int index = position + 1;
        while (index <= size) {
            indexArray[index] += additiveValue;
            index += (index & (-index));
        }
    }
}

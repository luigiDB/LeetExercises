package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class _369_PlusOneLinkedList {
    private boolean explicitBreak;
    /*Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.
    You may assume the integer do not contain any leading zero, except the number 0 itself.
    The digits are stored such that the most significant digit is at the head of the list.
    Example:
    Input:
    1->2->3
    Output:
    1->2->4*/

    @Test
    public void simpleTest() {
        LinkedList input = new LinkedList(Arrays.asList(1, 2, 3));
        addOne(input);
        Assert.assertEquals(Arrays.asList(1, 2, 4), input);

        input = new LinkedList(Arrays.asList(1, 2, 3));
        addWithIterator(input);
        Assert.assertEquals(Arrays.asList(1, 2, 4), input);
    }

    @Test
    public void testWithCarryOver() {
        LinkedList input = new LinkedList(Arrays.asList(1, 2, 9));
        addOne(input);
        Assert.assertEquals(Arrays.asList(1, 3, 0), input);

        input = new LinkedList(Arrays.asList(1, 2, 9));
        addWithIterator(input);
        Assert.assertEquals(Arrays.asList(1, 3, 0), input);
    }

    @Test
    public void testWithCarryOverToNewCipher() {
        LinkedList input = new LinkedList(Arrays.asList(9));
        addOne(input);
        Assert.assertEquals(Arrays.asList(1, 0), input);

        input = new LinkedList(Arrays.asList(9));
        addWithIterator(input);
        Assert.assertEquals(Arrays.asList(1, 0), input);
    }

    private void addOne(List<Integer> input) {
        int index = input.size() - 1;
        while (true) {
            if (index < 0) {
                input.add(0, 1);
                break;
            }
            Integer currentCipher = input.get(index);
            if (currentCipher.equals(9)) {
                input.set(index, 0);
                index--;
            } else {
                input.set(index, currentCipher + 1);
                break;
            }
        }
    }

    private void addWithIterator(LinkedList<Integer> input) {
        ListIterator<Integer> integerListIterator = input.listIterator(input.size());
        boolean explicitBreak = false;
        while (integerListIterator.hasPrevious()) {
            Integer current = integerListIterator.previous();
            if (current.equals(9)) {
                integerListIterator.set(0);
            } else {
                integerListIterator.set(current + 1);
                explicitBreak = true;
                break;
            }
        }
        if (!explicitBreak) {
            input.addFirst(1);
        }
    }
}

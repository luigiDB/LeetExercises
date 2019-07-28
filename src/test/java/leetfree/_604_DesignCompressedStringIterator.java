package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * Design and implement a data structure for a compressed string iterator. It should support the following operations:
 * next and hasNext.
 * <p>
 * The given compressed string will be in the form of each letter followed by a positive integer representing the number
 * of this letter existing in the original uncompressed string.
 * <p>
 * next() - if the original string still has uncompressed characters, return the next letter; Otherwise return a white
 * space.
 * hasNext() - Judge whether there is any letter needs to be uncompressed.
 * <p>
 * Note:
 * Please remember to RESET your class variables declared in StringIterator, as static/class variables are persisted
 * across multiple test cases. Please see here for more details.
 * <p>
 * Example:
 * <p>
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * <p>
 * iterator.next(); // return 'L'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 'e'
 * iterator.next(); // return 't'
 * iterator.next(); // return 'C'
 * iterator.next(); // return 'o'
 * iterator.next(); // return 'd'
 * iterator.hasNext(); // return true
 * iterator.next(); // return 'e'
 * iterator.hasNext(); // return false
 * iterator.next(); // return ' '
 */
public class _604_DesignCompressedStringIterator {

    @Test
    public void givenTest() {
        StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");

        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('e', iterator.next());
        Assert.assertEquals('e', iterator.next());
        Assert.assertEquals('t', iterator.next());
        Assert.assertEquals('C', iterator.next());
        Assert.assertEquals('o', iterator.next());
        Assert.assertEquals('d', iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals('e', iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(' ', iterator.next());
    }

    @Test
    public void testMultipleCiphersOccurencies() {
        StringIterator iterator = new StringIterator("L11c2");

        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertEquals('L', iterator.next());
        Assert.assertTrue(iterator.hasNext());
        Assert.assertEquals('c', iterator.next());
        Assert.assertEquals('c', iterator.next());
        Assert.assertFalse(iterator.hasNext());
        Assert.assertEquals(' ', iterator.next());
    }

    private class StringIterator {
        private String txt;
        private LinkedList<Occurencies> iteratorList;

        public StringIterator(String txt) {
            this.txt = txt;

            iteratorList = new LinkedList<>();
            for (int i = 0; i < txt.length(); ) {
                int j = i + 1;
                while (j < txt.length() && Character.isDigit(txt.charAt(j)))
                    j++;
                iteratorList.addLast(new Occurencies(txt.charAt(i), Integer.valueOf(txt.substring(i + 1, j))));
                i = j;
            }
        }

        public char next() {
            if (iteratorList.getFirst().repetitions == 0) {
                iteratorList.removeFirst();
            }
            if (iteratorList.isEmpty())
                return ' ';
            iteratorList.getFirst().repetitions--;
            return iteratorList.getFirst().letter;
        }

        public boolean hasNext() {
            if (iteratorList.isEmpty())
                return false;
            if (iteratorList.getFirst().repetitions == 0
                    && iteratorList.size() == 1) {
                return false;
            }
            return true;
        }

        private class Occurencies {
            char letter;
            int repetitions;

            public Occurencies(char letter, int repetitions) {
                this.letter = letter;
                this.repetitions = repetitions;
            }
        }
    }
}

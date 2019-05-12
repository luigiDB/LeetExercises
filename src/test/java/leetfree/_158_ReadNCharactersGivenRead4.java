package leetfree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class _158_ReadNCharactersGivenRead4 {

    /*The API: int read4(char *buf) reads 4 characters at a time from a file.
    The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
    By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
    Note:
    The read function may be called multiple times.
    * */

    //only for read4
    private ArrayList<Integer> fileBuffer;
    private int lastRead;

    //normal fields
    private ArrayList<Integer> readNotSend;
    boolean isLastRead4;

    @Before
    public void setUp() throws Exception {
        fileBuffer = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        lastRead = 0;
        readNotSend = new ArrayList<>();
        isLastRead4 = false;
    }

    @Test
    public void testCanReadMultipleOf4Char() {
        ArrayList<Integer> buf = new ArrayList<>();
        int read = read(buf, 8);
        Assert.assertEquals(8, read);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8)), buf);
    }

    @Test
    public void testCanReadANonMultipleOf4Char() {
        ArrayList<Integer> buf = new ArrayList<>();
        int read = read(buf, 5);
        Assert.assertEquals(5, read);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5)), buf);
    }

    @Test
    public void testCanMultipleReadWithoutLoss() {
        ArrayList<Integer> buf = new ArrayList<>();
        int read = read(buf, 3);
        Assert.assertEquals(3, read);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3)), buf);


        ArrayList<Integer> buf2 = new ArrayList<>();
        read = read(buf2, 3);
        Assert.assertEquals(3, read);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(4,5,6)), buf2);
    }

    @Test
    public void testThatIfTheFileIsShorterReturnAll() {
        ArrayList<Integer> buf = new ArrayList<>();
        int read = read(buf, 10);
        Assert.assertEquals(9, read);
        Assert.assertEquals(new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9)), buf);
    }

    private int read(ArrayList<Integer> buf, int n) {
        int parsedElements = 0;
        while(true) {
            while(!readNotSend.isEmpty() && parsedElements != n) {
                Integer elem0 = readNotSend.get(0);
                readNotSend.remove(0);
                buf.add(elem0);
                parsedElements++;
            }
            if(readNotSend.isEmpty()) {
                if(isLastRead4) {
                    return parsedElements;
                }
                int readSize = read4(readNotSend);
                if(readSize < 4) {
                    isLastRead4 = true;
                }
            }
            if(parsedElements==n) {
                return parsedElements;
            }
        }
    }

    private int read4(ArrayList<Integer> buf) {
        for (int i = 0; i < 4; i++) {
            try {
                buf.add(fileBuffer.get(lastRead));
            } catch (IndexOutOfBoundsException e) {
                return i;
            }
            lastRead++;
        }
        return 4;
    }
}

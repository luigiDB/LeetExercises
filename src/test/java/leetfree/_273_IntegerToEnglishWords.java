package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/*
Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 */
public class _273_IntegerToEnglishWords {
    @Test
    public void given1() {
        Assert.assertEquals("One Hundred Twenty Three", numberToWords(123));
    }

    @Test
    public void given2() {
        Assert.assertEquals("Twelve Thousand Three Hundred Forty Five", numberToWords(12345));
    }

    @Test
    public void given3() {
        Assert.assertEquals("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven", numberToWords(1234567));
    }

    @Test
    public void given4() {
        Assert.assertEquals("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One", numberToWords(1234567891));
    }

    @Test
    public void test0() {
        Assert.assertEquals("Zero", numberToWords(0));
    }

    @Test
    public void testZeroValues() {
        Assert.assertEquals("One Hundred", numberToWords(100));
        Assert.assertEquals("Ten", numberToWords(10));
    }


    public String numberToWords(int num) {
        if(num == 0)
            return "Zero";
        Stack<String> threeCipherSection = new Stack<>();
        Stack<String> multiplier = initMultiplier();

        int current = 0;
        int remainder = num;
        do {
            current = remainder % 1000;
            remainder = remainder / 1000;

            Stack<String> builder = new Stack<>();
            int dec = current % 100;
            int h = current / 100;
            builder.push(multiplier.pop());
            builder.push(decToText(dec));
            if (h != 0) {
                builder.push(singleToText(h) + " Hundred");
            }
            threeCipherSection.push(stackToString(builder));
        } while (remainder != 0);

        return stackToString(threeCipherSection).trim();
    }

    private String stackToString(Stack<String> stack) {
        StringBuilder builder = new StringBuilder();
        while (!stack.isEmpty())
            builder.append(stack.pop()).append(" ");
        return builder.toString().trim();
    }

    private String decToText(int num) {
        if (num < 20)
            if (num < 10)
                return singleToText(num);
            else {
                switch (num) {
                    case 10:
                        return "Ten";
                    case 11:
                        return "Eleven";
                    case 12:
                        return "Twelve";
                    case 13:
                        return "Thirteen";
                    case 14:
                        return "Fourteen";
                    case 15:
                        return "Fifteen";
                    case 16:
                        return "Sixteen";
                    case 17:
                        return "Seventeen";
                    case 18:
                        return "Eighteen";
                    case 19:
                        return "Nineteen";
                    default:
                        throw new UnsupportedOperationException();
                }
            }
        else {
            return decimalText(num / 10) + " " + singleToText(num % 10);
        }
    }

    private Stack<String> initMultiplier() {
        Stack<String> multiplier = new Stack<>();
        multiplier.push("Trillion");
        multiplier.push("Billion");
        multiplier.push("Million");
        multiplier.push("Thousand");
        multiplier.push("");
        return multiplier;
    }

    private String singleToText(int num) {
        switch (num) {
            case 0:
                return "";
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
        }
        throw new UnsupportedOperationException();
    }

    private String decimalText(int num) {
        switch (num) {
            case 2:
                return "Twenty";
            case 3:
                return "Thirty";
            case 4:
                return "Forty";
            case 5:
                return "Fifty";
            case 6:
                return "Sixty";
            case 7:
                return "Seventy";
            case 8:
                return "Eighty";
            case 9:
                return "Ninety";

        }
        throw new UnsupportedOperationException();
    }
}

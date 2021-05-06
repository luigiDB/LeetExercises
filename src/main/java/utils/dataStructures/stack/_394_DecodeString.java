package utils.dataStructures.stack;

import java.util.Stack;
import java.util.TreeMap;

/*
 * Given an encoded string, return its decoded string.
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for those
 * repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * Examples:
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 */
public class _394_DecodeString {

    /*
    The recursive solution is possible but require the recursive function to return both obtained string and the index
    of the ] since the calling instance should continue from that point the parsing
     */
    public String decode(String s) {
        Stack<Pair> stack = new Stack<>();

        Pair currentPair = new Pair(1);
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                stack.push(currentPair);
                currentPair = new Pair(Character.getNumericValue(s.charAt(i)));
            } else {
                if (s.charAt(i) == ']') {
                    int rep = currentPair.number;
                    String closed = currentPair.sb.toString();
                    currentPair = stack.pop();
                    for (int j = 0; j < rep; j++) {
                        currentPair.sb.append(closed);
                    }
                } else {
                    if (s.charAt(i) != '[') {
                        currentPair.sb.append(s.charAt(i));
                    }
                }
            }
        }
        return currentPair.sb.toString();
    }

    class Pair {
        int number;
        StringBuilder sb = new StringBuilder();

        public Pair(int number) {
            this.number = number;
        }
    }
}

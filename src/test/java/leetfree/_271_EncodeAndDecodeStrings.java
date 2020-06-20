package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is
decoded back to the original list of strings.
Machine 1 (sender) has the function:
string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:
vector<string> decode(string s) {
  //... your code
  return strs;
}
So Machine 1 does:
string encoded_string = encode(strs);
and Machine 2 does:
vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.
Implement the encode and decode methods.
Note:
The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized
enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode
 algorithm.
*/
public class _271_EncodeAndDecodeStrings {

    @Test
    public void testBaseCase() {
        Vector<String> toBeSent = new Vector<>();
        toBeSent.add("abba");
        Assert.assertEquals(toBeSent, decode(encode(toBeSent)));
    }

    @Test
    public void testMultipleStrings() {
        Vector<String> toBeSent = new Vector<>();
        toBeSent.add("abba");
        toBeSent.add("cddc");
        String encode = encode(toBeSent);
        Vector<String> decode = decode(encode);
        Assert.assertEquals(toBeSent, decode);
    }

    @Test
    public void testThatEnodeAndDecodeWorksWhenTheSplitCharIsPresent() {
        Vector<String> toBeSent = new Vector<>();
        toBeSent.add("abba");
        toBeSent.add("cd#dc");
        Assert.assertEquals(toBeSent, decode(encode(toBeSent)));
    }

    private String encode(Vector<String> toBeSent) {
        StringBuilder builder = new StringBuilder();
        for (String s : toBeSent) {
            String escapedString = s.replace("#", "##");
            builder.append(escapedString);
            builder.append("#");
        }
        return builder.toString();
    }

    private Vector<String> decode(String toBeDecoded) {
        Vector<String> decoded = new Vector<>();
        String[] split = toBeDecoded.split("#");
        for (int i = 0; i < split.length; ) {
            if ((i+1)<split.length && split[i + 1].equals("")) {
                decoded.add(split[i] + "#" + split[i + 2]);
                i = i + 3;
            } else {
                decoded.add(split[i]);
                i = i + 1;
            }
        }
        return decoded;
    }

}

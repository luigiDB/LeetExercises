package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/*
Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the
substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together by only one pair
of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
Example 1:
Input:
s = "abcxyz123"
dict = ["abc","123"]
Output:
"<b>abc</b>xyz<b>123</b>"
Example 2:
Input:
s = "aaabbcc"
dict = ["aaa","aab","bc"]
Output:
"<b>aaabbc</b>c"
Note:
The given dict won't contain duplicates, and its length won't exceed 100.
All the strings in input have length in range [1, 1000].
 */
public class _616_AddBoldTagInString {

    @Test
    public void test1() {
        String txt = "abcxyz123";
        List<String> dict = Arrays.asList("abc", "123");
        Assert.assertEquals("<b>abc</b>xyz<b>123</b>", boldMarking(txt, dict));
    }

    @Test
    public void test2() {
        String txt = "aaabbcc";
        List<String> dict = Arrays.asList("aaa","aab","bc");
        Assert.assertEquals("<b>aaabbc</b>c", boldMarking(txt, dict));
    }

    public String boldMarking(String txt, List<String> dict) {
        List<Match> matches = searchMatches(txt, dict);
        List<Match> compacted = compactMatches(matches);

        StringBuilder sb = new StringBuilder();
        int currentIndex = 0;
        for (Match m : compacted) {
            if (m.getStart() > currentIndex)
                sb.append(txt.substring(currentIndex, m.getStart()));
            sb.append("<b>");
            sb.append(txt.substring(m.getStart(), m.getEnd() + 1));
            sb.append("</b>");
            currentIndex = m.getEnd() + 1;
        }
        sb.append(txt.substring(currentIndex));

        return sb.toString();
    }

    private List<Match> compactMatches(List<Match> matches) {
        Collections.sort(matches, Comparator.comparingInt(Match::getStart).thenComparingInt(Match::getEnd));
        List<Match> list = new LinkedList<>();
        int low = matches.get(0).getStart();
        int top = matches.get(0).getEnd();

        for (Match m : matches) {
            if (m.getStart() <= top)
                top = Math.max(m.getEnd(), top);
            if (m.getStart() == top + 1)
                top = m.getEnd();
            if (m.getStart() > top + 1) {
                list.add(new Match(low, top));
                low = m.getStart();
                top = m.getEnd();
            }
        }
        list.add(new Match(low, top));

        return list;
    }

    private List<Match> searchMatches(String txt, List<String> dict) {
        List<Match> list = new LinkedList<>();

        for (String key : dict) {
            int lastMatch = 0;
            for (; ; ) {
                int index = txt.indexOf(key, lastMatch);
                if (index != -1) {
                    list.add(new Match(index, index + key.length() - 1));
                    lastMatch = index + 1;
                } else {
                    break;
                }
            }
        }

        return list;
    }

    private class Match {
        int start;
        int end;

        public Match(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }
}

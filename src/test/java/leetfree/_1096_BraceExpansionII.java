package leetfree;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.hamcrest.Matchers.contains;

/*
Under a grammar given below, strings can represent a set of lowercase words.  Let's use R(expr) to denote the set of words the expression represents.

Grammar can best be understood through simple examples:

Single letters represent a singleton set containing that word.
R("a") = {"a"}
R("w") = {"w"}
When we take a comma delimited list of 2 or more expressions, we take the union of possibilities.
R("{a,b,c}") = {"a","b","c"}
R("{{a,b},{b,c}}") = {"a","b","c"} (notice the final set only contains each word at most once)
When we concatenate two expressions, we take the set of possible concatenations between two words where the first word comes from the first expression and the second word comes from the second expression.
R("{a,b}{c,d}") = {"ac","ad","bc","bd"}
R("a{b,c}{d,e}f{g,h}") = {"abdfg", "abdfh", "abefg", "abefh", "acdfg", "acdfh", "acefg", "acefh"}
Formally, the 3 rules for our grammar:

For every lowercase letter x, we have R(x) = {x}
For expressions e_1, e_2, ... , e_k with k >= 2, we have R({e_1,e_2,...}) = R(e_1) ∪ R(e_2) ∪ ...
For expressions e_1 and e_2, we have R(e_1 + e_2) = {a + b for (a, b) in R(e_1) × R(e_2)}, where + denotes concatenation, and × denotes the cartesian product.
Given an expression representing a set of words under the given grammar, return the sorted list of words that the expression represents.

Constraints:

1 <= expression.length <= 60
expression[i] consists of '{', '}', ','or lowercase English letters.
The given expression represents a set of words based on the grammar given in the description.
 */
public class _1096_BraceExpansionII {

    @Test
    public void baseExpansion() {
        Assert.assertThat(R("{a}"), contains("a"));
        Assert.assertThat(R("{ab}"), contains("ab"));
    }

    @Test
    public void baseExpansion2() {
        Assert.assertThat(R("a"), contains("a"));
        Assert.assertThat(R("ab"), contains("ab"));
    }

    @Test
    public void baseUnion() {
        Assert.assertThat(R("{a,b,c}"), contains("a", "b", "c"));
        Assert.assertThat(R("{{a},{b}}"), contains("a", "b"));
    }

    @Test
    public void baseMultiplication() {
        Assert.assertThat(R("{{a}{b}}"), contains("ab"));
    }

    @Test
    public void baseLetterMultiplication() {
        Assert.assertThat(R("a{b,c}"), contains("ab", "ac"));
    }

    @Test
    public void complexTest() {
        Assert.assertThat(R("{a,b}{c,{d,e}}"), contains("ac", "ad", "ae", "bc", "bd", "be"));
    }

    @Test
    public void complexTest2() {
        Assert.assertThat(R("{{a,z},a{b,c},{ab,z}}"), contains("a", "ab", "ac", "z"));
    }

    private List<String> R(String in) {
        List<String> sortedlist = new ArrayList<>();
        sortedlist.addAll(RIn(in));
        sortedlist.sort(Comparator.comparing(String::toString));
        return sortedlist;
    }

    private Set<String> RIn(String in) {
        Set<String> res = new HashSet<>();

        boolean previouComma = false;
        int i = 0;
        while (i < in.length()) {
            switch (in.charAt(i)) {
                case '{':
                    int closingBracketPos = findClosingBracket(in, i);
                    Set<String> innerRes = RIn(in.substring(i + 1, closingBracketPos));
                    if(previouComma) {
                        res.addAll(innerRes);
                        previouComma = false;
                    }
                    else
                        res = zipLists(res, innerRes);
                    i = closingBracketPos + 1;
                    break;
                case ',':
                    previouComma = true;
                    i++;
                    break;
                default:
                    int secondIndex = i;
                    StringBuilder tmp = new StringBuilder();
                    while (Character.isAlphabetic(in.charAt(secondIndex))) {
                        tmp.append(in.charAt(secondIndex));
                        secondIndex++;
                        if (secondIndex == in.length()) {
                            break;
                        }
                    }
                    res.add(tmp.toString());
                    i = secondIndex;
                    break;
            }
        }
        return res;
    }

    private Set<String> zipLists(Set<String> first, Set<String> second) {
        if(first.isEmpty())
            return second;
        Set<String> zip = new HashSet<>();
        for (String f: first) {
            for (String s: second) {
                zip.add(f+s);
            }
        }
        return zip;
    }

    private int findClosingBracket(String in, int startIndex) {
        int start = startIndex;
        int bracketCount = 0;
        while (start < in.length()) {
            if (in.charAt(start) == '{') {
                bracketCount++;
            } else if (in.charAt(start) == '}') {
                bracketCount--;
                if (bracketCount == 0)
                    return start;
            }
            start++;
        }
        return start;
    }
}

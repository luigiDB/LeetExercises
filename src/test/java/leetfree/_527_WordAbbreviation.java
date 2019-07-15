package leetfree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 * <p>
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used
 * instead of only the first character until making the map from word to abbreviation become unique. In other words,
 * a final abbreviation cannot map to more than one original words.
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 * Example:
 * Input: [ "like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * Output: ["l2e",  "god", "internal", "me", "internet", "interval", "inte4n",    "f2e",  "intr4n"]
 * Note:
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 */
public class _527_WordAbbreviation {
    //TODO: can be resolved using a prefix trie and reading the branch inOrder


    @Test
    public void testThatIsAbleToAbbreviate() {
        Assert.assertEquals(new String[]{"l2e", "god", "i6l"},
                abbreviate(new String[]{"like", "god", "internal"}));
    }

    @Test
    public void testThatCanAbbreviateAccordinglyToRule() {
        Assert.assertEquals(new String[]{"inte4n", "intr4n"},
                abbreviate(new String[]{"intension", "intrusion"}));
    }

    @Test
    public void testAllConditionsTogether() {
        Assert.assertEquals(new String[]{"l2e", "god", "internal", "me", "internet", "interval", "inten3n", "f2e", "intr4n"},
                abbreviate(new String[]{"like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"}));
    }

    private String[] abbreviate(String[] strings) {
        String[] abbreviations = new String[strings.length];
        Node root = new Node();

        for (String s : strings) {
            Node tmp = root;
            for (int i = 0; i < s.length(); i++) {
                if (tmp.children[s.charAt(i) - 'a'] == null) {
                    tmp.children[s.charAt(i) - 'a'] = new Node();
                }
                tmp = tmp.children[s.charAt(i) - 'a'];
                tmp.counter++;
            }
        }

        for (int j = 0; j < strings.length; j++) {
            String s = strings[j];
            StringBuilder sb = new StringBuilder();

            Node tmp = root;
            int i = 0;
            for (; i < s.length(); i++) {
                tmp = tmp.children[s.charAt(i) - 'a'];
                if (tmp.counter > 1) {
                    sb.append(s.charAt(i));
                } else {
                    sb.append(s.charAt(i));
                    break;
                }
            }

            if (i == s.length() - 3) {
                sb.append(s.charAt(s.length() - 2));
                sb.append(s.charAt(s.length() - 1));
            } else {
                int foldSize = s.length() - i - 2;
                if (foldSize != 0)
                    sb.append(foldSize);
                sb.append(s.charAt(s.length() - 1));
            }

            abbreviations[j] = sb.toString();
        }

        return abbreviations;
    }


    class Node {
        Node[] children = new Node[26];
        int counter = 0;
    }
}

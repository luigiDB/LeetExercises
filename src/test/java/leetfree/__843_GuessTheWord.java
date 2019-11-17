package leetfree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
This problem is an interactive problem new to the LeetCode platform.
We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.
You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original
list with 6 lowercase letters.
This function returns an integer type, representing the number of exact matches (value and position) of your guess to
the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.
For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or
less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.
Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The
letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in
the given word lists is unique.
Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]
Explanation:
master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 */
public class __843_GuessTheWord {

    interface Master {
        int guess(String word);
    }

    public void findSecretWord(String[] wordlist, Master master) {
        if (wordlist == null || wordlist.length == 0) {
            return;
        }

        int n = wordlist.length;
        int l = wordlist[0].length();

        Set<Integer> possible = new HashSet<>();
        for (int i = 0; i < n; i++) {
            possible.add(i);
        }
        while (!possible.isEmpty()) {
            // pick the first word in the set brainlessly
            Iterator<Integer> iterator = possible.iterator();
            int choice = iterator.next();

            int matches = master.guess(wordlist[choice]);
            if (matches == l) { // we found the match!
                return;
            }
            Set<Integer> possible2 = new HashSet<>();
            for (int i = 0; i < n; i++) {
                // This is the key!
                // Candidates are among the words that have the same number of matches with the guessed word as the returned matches
                // And candidates must be among the list (set) of candidates remained from previous guesses
                if (countMatches(wordlist[choice], wordlist[i]) == matches && possible.contains(i)) {
                    possible2.add(i);
                }
            }
            possible = possible2;
        }
    }

    private int countMatches(String word1, String word2) {
        int l = word1.length();
        int matches = 0;
        for (int k = 0; k < l; k++) {
            if (word1.charAt(k) == word2.charAt(k)) {
                matches++;
            }
        }
        return matches;
    }
}

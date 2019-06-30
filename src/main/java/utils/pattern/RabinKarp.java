package utils.pattern;

import java.util.LinkedList;
import java.util.List;

/**
 * The average and best case running time of the Rabin-Karp algorithm is O(n+m), but its worst-case time is O(nm).
 * Worst case of Rabin-Karp algorithm occurs when all characters of pattern and text are same as the hash values of
 * all the substrings of txt[] match with hash value of pat[]. For example pat[] = “AAA” and txt[] = “AAAAAAA”.
 */
public class RabinKarp {
    /**
     * https://www.geeksforgeeks.org/rabin-karp-algorithm-for-pattern-searching/
     * hash(s+1 .. s+m) =
     *      ( ( d *( hash(s .. s+m-1) - txt[s] * h ) ) + txt[s+m] ) mod q
     * where
     *      d -> alpahbet size
     *      h -> d ^ pattern_size -1
     *      q -> a prime number
     */

    public static List<Integer> search(String text, String pattern) {
        int alphabetSize = 26;
        int prime = 13;
        int hashvalue = 1;

        for (int i = 0; i < pattern.length() - 1; i++)
            hashvalue = (hashvalue * alphabetSize) % prime;

        int tHash = 0;
        int pHash = 0;

        for (int i = 0; i < pattern.length(); i++) {
            tHash = ((alphabetSize * tHash) + text.charAt(i)) % prime;
            pHash = ((alphabetSize * pHash) + pattern.charAt(i)) % prime;
        }

        List<Integer> matches = new LinkedList<>();

        for (int i = 0; i < text.length() - pattern.length() + 1; i++) {
            if (tHash == pHash) {
                int j;
                for (j = 0; j < pattern.length(); j++) {
                    if (text.charAt(i + j) != pattern.charAt(j))
                        break;
                }
                if (j == pattern.length())
                    matches.add(i);
            }
            if (i < text.length() - pattern.length())
                tHash = (alphabetSize * (tHash - (text.charAt(i) * hashvalue)) + text.charAt(i + pattern.length())) % prime;
        }

        return matches;
    }
}

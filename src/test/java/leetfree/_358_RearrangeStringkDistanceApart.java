package leetfree;

public class _358_RearrangeStringkDistanceApart {

    /*Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
    All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
    Example 1:
    s = "aabbcc", k = 3
    Result: "abcabc"
    The same letters are at least distance 3 from each other.
    Example 2:
    s = "aaabc", k = 3
    Answer: ""
    It is not possible to rearrange the string.
    Example 3:
    s = "aaadbbcc", k = 2
    Answer: "abacabcd"
    Another possible answer is: "abcabcda"
    The same letters are at least distance 2 from each other.
    * */

    /*maintain an ordered ist of Pair<char, n_occurencies> ordered by freq.
    Everytime choice at roundrobin from if one cannot be chosen go to the next one.
    Keep char with a sigle occurrence apart and use only when there aren't other possibilities*/
}

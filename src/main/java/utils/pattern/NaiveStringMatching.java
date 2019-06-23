package utils.pattern;

import java.util.LinkedList;
import java.util.List;

public class NaiveStringMatching {

    public static List<Integer> search(String text, String pattern) {
        List<Integer> matches = new LinkedList<>();

        for (int i = 0; i < text.length() - pattern.length() +1; i++) {
            if(text.charAt(i) == pattern.charAt(0)){
                boolean isAMatch = true;
                for (int j = 1; j < pattern.length() && isAMatch; j++) {
                    if(text.charAt(j+i) != pattern.charAt(j)){
                        isAMatch = false;
                    }
                }
                if (isAMatch){
                    matches.add(i);
                }
            }
        }

        return matches;
    }
}

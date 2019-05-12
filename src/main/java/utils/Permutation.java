package utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Permutation<T> {

    public void permute(List list, int left, int right, Collection<Collection<T>> permutations) {
        if(left == right) {
            addPermutation(list, permutations);
        } else {
            for(int i = left ; i<=right ; i++) {
                Collections.swap(list, left, i);
                permute(list, left+1, right, permutations);
                Collections.swap(list, left, i);
            }
        }
    }

    private void addPermutation(List list, Collection<Collection<T>> permutations) {
        ArrayList copy = new ArrayList();
        copy.addAll(list);
        permutations.add(copy);
    }
}

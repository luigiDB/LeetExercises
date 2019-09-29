package utils.dataStructures;

import java.util.HashSet;
import java.util.Set;

public class ListDisjointSet<T> {

    private Set<PointerSet<T>> sets;

    public ListDisjointSet() {
        this.sets = new HashSet<>();
    }

    public void addSet(T elem) {
        PointerSet<T> set = new PointerSet<>();
        set.add(elem);
        sets.add(set);
    }

    public PointerSet<T> findSet(T elem) {
        for (PointerSet<T> set : sets) {
            if (set.contains(elem))
                return set;
        }
        return null;
    }

    public void merge(T first, T second) {
        PointerSet<T> firstSet = findSet(first);
        PointerSet<T> secondSet = findSet(second);
        if(!firstSet.equals(secondSet)) {
            firstSet.merge(secondSet);
            sets.remove(secondSet);
        }
    }

    public Set<Set<T>> returnActualSets() {
        Set<Set<T>> returnSet= new HashSet<>();
        for (PointerSet<T> pointerSet: sets) {
            Set<T> partialSet= new HashSet<>();
            for (Elem<T> elem: pointerSet.getSet()){
                partialSet.add(elem.getData());
            }
            returnSet.add(partialSet);
        }

        return returnSet;
    }
}

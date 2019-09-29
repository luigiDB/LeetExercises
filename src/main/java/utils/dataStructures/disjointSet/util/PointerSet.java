package utils.dataStructures.disjointSet.util;

import java.util.HashSet;
import java.util.Set;

public class PointerSet<T> {

    private final Set<Elem<T>> set;

    public Set<Elem<T>> getSet() {
        return set;
    }

    public PointerSet() {
        set = new HashSet<>();
    }

    public boolean add(T e) {
        Elem<T> elem = new Elem<>(e, this);
        return set.add(elem);
    }

    public boolean contains(T e) {
        for (Elem<T> iter : set) {
            if (iter.getData().equals(e))
                return true;
        }
        return false;
    }

    public void merge(PointerSet<T> set2) {
        for (Elem<T> iter : set2.getSet()) {
            iter.setHead(this);
            set.add(iter);
        }
    }

}

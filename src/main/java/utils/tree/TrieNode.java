package utils.tree;

import java.util.*;

public class TrieNode<T> {

    T info;
    Set<T> sons;

    public TrieNode(T info) {
        this.info = info;
        sons = new HashSet<>();
    }

    public void addSon(T son) {
        sons.add(son);
    }
}

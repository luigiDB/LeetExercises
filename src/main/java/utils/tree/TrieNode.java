package utils.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TrieNode<T> {

    T info;
    List<T> sons;

    public TrieNode(T info) {
        this.info = info;
        sons = null;
    }

    public void addSon(T son) {
        if(sons!=null)
            sons.add(son);
        else
            sons = new LinkedList<>(Arrays.asList(son));
    }
}

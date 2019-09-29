package utils.dataStructures;

public class Elem<T> {

    private T data;
    private PointerSet head;

    public Elem(T data, PointerSet<T> head) {
        this.data = data;
        this.head = head;
    }

    public T getData() {
        return data;
    }

    public void setHead(PointerSet head) {
        this.head = head;
    }

    public PointerSet getHead() {
        return head;
    }
}

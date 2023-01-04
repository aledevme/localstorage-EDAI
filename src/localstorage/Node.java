package localstorage;

public class Node <T> {
    private final T value;
    private Node<T> next;

    public Node(T value) {
        this.value = value;
    }

    public T getNode(){
        return value;
    }
    public Node<T> getNext(){
        return next;
    }
    public void setNext(Node<T> next){
        this.next = next;
    }
}

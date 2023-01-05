package localstorage;
public class LinkedList <T>{
    private Node<T> head;
    private int size;

    public void add(T value){
        Node<T> newNode = new Node<>(value);
        if(head == null){
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null){
                current = current.getNext();
            }
            current.setNext(newNode);
        }

        size++;
    }

    public void set(int index, T value){
        Node<T> newNode = new Node<>(value);
        if(index == 0){
            newNode.setNext(head);
            head = newNode;
        }else{
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    public void remove(int index){
        if(index == 0) {
            head = head.getNext();
        }
        else{
            Node<T> current = head;
            for (int i=0; i < index - 1; i++){
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        size--;
    }

    public Node get(int index){
        Node<T> current = head;
        for(int i=0; i< index; i++){
            current = current.getNext();
        }
        return current;
    }

    public int size() {
        return size;
    }


}

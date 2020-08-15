public class LinkedListDeque<T> implements Deque<T> {

    private class Node{
        private T item;
        private Node prev;
        private Node next;

        private Node(T item, Node p, Node n){
            this.item = item;
            prev = p;
            next = n;
        }
    }

    private final Node sentinel;
    private int size;

    /** Create an empty LinkedListDeque */
    public LinkedListDeque(){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item){
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    public void addLast(T item){
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        Node toPrint = sentinel.next;
        for(int i = 0; i < size; i++){
            System.out.print(toPrint.item + " ");
            toPrint = toPrint.next;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T toRemove = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return toRemove;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T toRemove = sentinel.prev.item;
        sentinel.prev.prev = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return toRemove;
    }

    public T get(int index) {
        Node toGet = sentinel;
        if (!isEmpty()) {
            for (int i = 0; i < index; i++) {
                toGet = toGet.next;
            }
        }
        return toGet.item;
    }


    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
        for(int i = 0; i < other.size(); i++){
            this.addFirst((T) other.get(i));
        }
    }

    public T getRecursive(int index){
        return getCurrentNode(index, sentinel).item;
    }

    private Node getCurrentNode(int index, Node n){
        if(index == 0){
            return n;
        }
        return getCurrentNode(index - 1, n.next);
    }
}

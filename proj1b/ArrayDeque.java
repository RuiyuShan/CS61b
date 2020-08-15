public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int head;
    private int tail;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        head = 0;
        tail = 0;
        size = 0;
    }

    private void doubleCapacity(){
        assert head == tail;
        int newCapacity = items.length << 1;
        T[] a = (T[]) new Object[newCapacity];
        System.arraycopy(items, head, a, 0, items.length - head);
        System.arraycopy(items, 0, a, items.length - head, head);
        tail = items.length;
        head = 0;
        items = a;
    }

    public void addFirst(T item){
        if(item == null){
            throw new NullPointerException();
        }
        items[head = (head - 1) & (items.length - 1)] = item;
        if(head == tail){
            doubleCapacity();
        }
        size += 1;
    }

    public void addLast(T item){
        if(item == null){
            throw new NullPointerException();
        }
        items[tail] = item;
        if((tail = (tail + 1) & (items.length - 1)) == head){
            doubleCapacity();
        }
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    public int size(){
        return size;
    }
    public void printDeque(){
        if(tail >= head){
            for(int i = head; i < tail; i++){
                System.out.print(items[i]);
            }
            System.out.println();
        }else if(tail < head){
            for(int i = head; i < items.length; i++){
                System.out.print(items[i]);
            }
            for(int i = 0; i < tail; i++){
                System.out.print(items[i]);
            }
            System.out.println();
        }
    }

    public T removeFirst(){
        int h = head;
        T returnItem = items[h];
        if(returnItem == null){
            return null;
        }
        items[h] = null;
        head = (head + 1) & (items.length - 1);
        size -= 1;
        return returnItem;
    }
    public T removeLast(){
        int t = (tail - 1) & (items.length - 1);
        T returnItem = items[t];
        if(returnItem == null){
            return null;
        }
        items[t] = null;
        tail = (tail - 1) & (items.length - 1);
        size -= 1;
        return returnItem;
    }
    public T get(int index){
        return items[(head + index) & (items.length - 1)];
    }
    public ArrayDeque(ArrayDeque other){
        int initialCapacity = other.items.length;
        items = (T[]) new Object[initialCapacity];
        head = other.head;
        tail = other.tail;
        size = other.size;
        if(tail >= head){
            System.arraycopy(other.items, other.head, items, head, tail - head);
        }else if(tail < head){
            System.arraycopy(other.items, other.head, items, head, items.length - head);
            System.arraycopy(other.items, 0, items, 0, tail);
        }
    }

}

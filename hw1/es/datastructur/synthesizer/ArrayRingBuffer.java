package es.datastructur.synthesizer;
import java.util.Iterator;

//TODO: Make sure to that this class and all of its methods are public
//TODO: Make sure to add the override tag for all overridden methods
//TODO: Make sure to make this class implement BoundedQueue<T>

public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update
        //       last.
        if(isFull()){
            throw new RuntimeException("Ring Buffer overflow");
        }else{
            rb[last] = x;
            fillCount += 1;
            last = (last + 1) % capacity();
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and
        //       update first.
        T firstItem;
        if(isEmpty()){
            throw new RuntimeException("Ring Buffer underflow");
        }else{
            firstItem = rb[first];
            fillCount -= 1;
            first = (first + 1) % capacity();
        }
        return firstItem;
    }

    public boolean contains(T item){
        for(int i = 0; i < capacity(); i++){
            if(rb[i] == item){
                return true;
            }
        }
        return false;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should
        //       change.
        if(isEmpty()){
            throw new RuntimeException("Ring Buffer underflow");
        }else{
            return rb[first];
        }
    }


    public static void main(String[] args){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(3);
        arb2.enqueue(0);
        arb2.enqueue(1);
        arb2.enqueue(2);

        System.out.println(arb.equals(arb2));
        arb.equals(arb2);


    }

    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.
    private class arbIterator implements Iterator<T>{
        int cursor;
        int count;
        public arbIterator(){
            cursor = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            T returnItem = rb[cursor];
            cursor = (cursor + 1) % capacity();
            count += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new arbIterator();
    }

    @Override
    public boolean equals(Object other){
        if(other == null){
            return false;
        }
        if(this == other){
            return true;
        }
        if(this.getClass() != other.getClass()){
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if(this.fillCount() != o.fillCount()){
            return false;
        }
        if(this.capacity() != o.capacity()){
            return false;
        }
        Iterator thisItr = this.iterator();
        Iterator otherItr = o.iterator();
        while (thisItr.hasNext() && otherItr.hasNext()){
            if(thisItr.next() != otherItr.next()){
                return false;
            }
        }
        return true;
    }






}
    // TODO: Remove all comments that say TODO when you're done.

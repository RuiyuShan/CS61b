package es.datastructur.synthesizer;
import org.junit.Test;

import java.util.MissingFormatArgumentException;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testARB(){
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(3);
        assertTrue(arb.isEmpty());
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals( 0, (int) arb.peek());
        assertTrue(arb.isFull());
        assertEquals(0, (int) arb.dequeue());
        assertEquals(1, (int) arb.peek());
        assertFalse(arb.isFull());

        ArrayRingBuffer<Integer> arb1 = new ArrayRingBuffer<>(3);
        assertTrue(arb1.isEmpty());
        arb1.enqueue(0);
        arb1.enqueue(1);
        arb1.enqueue(2);

        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer<>(3);
        arb2.enqueue(0);
        arb2.enqueue(1);
        arb2.enqueue(2);

        assertTrue(arb1.equals(arb2));
    }


}

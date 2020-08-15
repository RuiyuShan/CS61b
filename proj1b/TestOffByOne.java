import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars(){
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('c', 'd'));
        assertTrue(offByOne.equalChars('d', 'c'));

        assertFalse(offByOne.equalChars('a', 'c'));
        assertFalse(offByOne.equalChars('a', '2'));
        assertFalse(offByOne.equalChars('#', '!'));
        assertFalse(offByOne.equalChars('8', 'A'));
    }

    @Test
    public void testOffByN(){
        OffByN offBy5 = new OffByN(5);
    }

    // Your tests go here.
}

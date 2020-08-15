import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static OffByN offBy5 = new OffByN(5);

    @Test
    public void testOffBy5(){
        assertTrue(offBy5.equalChars('a', 'f'));  // true
        assertTrue(offBy5.equalChars('f', 'a'));  // true
        assertFalse(offBy5.equalChars('f', 'h'));  // false
    }




}

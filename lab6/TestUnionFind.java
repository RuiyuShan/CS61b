import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class TestUnionFind {
    @Test
    public void testConstructor(){
        UnionFind uf = new UnionFind(6);
        int[] expected = {-1, -1, -1, -1, -1, -1};
        int[] actual = uf.uset;
        assertTrue(Arrays.equals(expected, actual));
    }

    /**
     * Test exception;
     * @Rule
     * public ExpectedException exception = ExpectedException.none();
     */


    @Test(expected = IllegalArgumentException.class)
    public void testValidateThrowsException(){
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("Invalid index");
        UnionFind uf = new UnionFind(6);
        uf.sizeOf(8);
        uf.sizeOf(-2);
    }

    @Test
    public void testParent(){
        UnionFind uf = new UnionFind(6);
        int[] array = {2 , 0, -3, -1, -1, -1};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);
        int expected = 0;
        int actual = uf.parent(1);
        assertEquals(expected, actual);
    }

    @Test
    public void testUnion(){
        UnionFind uf = new UnionFind(6);
        int[] array = {-3 , 0, 1, -3, 3, 4};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);

        assertFalse(uf.connected(2, 5));
        uf.union(2, 5);
        assertTrue(uf.connected(2, 5));
        assertEquals(-6, uf.uset[3]);
        assertTrue(uf.uset[0] > -1);
    }

    @Test
    public void testConnected(){
        UnionFind uf = new UnionFind(6);
        int[] array = {2 , 0, -4, -1, 2, -1};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);

        assertTrue(uf.connected(1, 2));
        assertTrue(uf.connected(1, 4));
        assertFalse(uf.connected(1, 3));
        assertFalse(uf.connected(1, 5));
    }

    @Test
    public void testSizeOf(){
        UnionFind uf = new UnionFind(6);
        int[] array = {-4 , 0, 1, 2, -1, -1};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);

        assertEquals(4, uf.sizeOf(2));
        int a = uf.sizeOf(3);
        int[] arrayAfterPathCompression = {-4, 0, 0, 0, -1, -1};
        assertTrue(Arrays.equals(arrayAfterPathCompression, uf.uset));
        uf.find(2);
        assertTrue(Arrays.equals(arrayAfterPathCompression, uf.uset));
    }

    @Test
    public void testFind(){
        UnionFind uf = new UnionFind(6);
        int[] array = {-3 , 0, 1, -3, 3, 4};
        System.arraycopy(array, 0, uf.uset, 0, uf.uset.length);
        assertTrue(Arrays.equals(array, uf.uset));

        assertEquals(0, uf.find(2));
        assertEquals(3, uf.find(5));

        int[] arrayAfterPathCompression = {-3, 0, 0, -3, 3, 3};
        assertTrue(Arrays.equals(arrayAfterPathCompression, uf.uset));

    }







}

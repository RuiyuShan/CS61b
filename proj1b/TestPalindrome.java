import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        boolean resultFalse = palindrome.isPalindrome("cat");
        assertFalse(resultFalse);
        boolean resultTrue = palindrome.isPalindrome("noon");
        assertTrue(resultTrue);
    }

    @Test
    public void testIsPalindromeRecursive(){
        boolean resultFalse = palindrome.isPalindromeRecursive("cat");
        assertFalse(resultFalse);
        boolean resultTrue = palindrome.isPalindromeRecursive("noon");
        assertTrue(resultTrue);
    }
}
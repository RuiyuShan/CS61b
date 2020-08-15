public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        ArrayDeque<Character> toReturn = new ArrayDeque<Character>();
        for(int i = 0; i < word.length(); i++){
            toReturn.addLast(word.charAt(i));
        }
        return toReturn;
    }

    public boolean isPalindrome(String word){
        Deque d = this.wordToDeque(word);
        int size = d.size();
        for(int i = 0; i < size/2; i++){
            if(!d.get(i).equals(d.get((size-1)-i))){
                return false;
            }
        }
        if(d.size() == 0){
            return false;
        }
        return true;
    }

    public boolean isPalindromeRecursive(Deque<Character> wordInDeque){
        while(wordInDeque.size() > 1){
            return wordInDeque.removeFirst().equals(wordInDeque.removeLast()) && isPalindromeRecursive(wordInDeque);
        }
        return true;
    }

    public boolean isPalindromeRecursive(String word){
        Deque wordInDeque = this.wordToDeque(word);
        if(wordInDeque.size() == 0){
            return false;
        }
        return isPalindromeRecursive(wordInDeque);
    }

    public boolean isPalindromeOffByOne(Deque<Character> wordInDeque, CharacterComparator cc){
        while(wordInDeque.size() > 1){
            return cc.equalChars(wordInDeque.removeFirst(), wordInDeque.removeLast()) && isPalindromeOffByOne(wordInDeque, cc);
        }
        return true;
    }

    public boolean isPalindromeOffByOne(String word, CharacterComparator cc){
        Deque wordInDeque = this.wordToDeque(word);
        return isPalindromeOffByOne(wordInDeque, cc);
    }
}

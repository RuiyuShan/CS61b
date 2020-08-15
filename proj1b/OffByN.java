import java.lang.Math;
public class OffByN implements CharacterComparator{
    int n;
    public OffByN(int n){
        this.n = n;
    }

    public boolean equalChars(char x, char y){
        int diff = x - y;
        return n == Math.abs(diff);
    }
}

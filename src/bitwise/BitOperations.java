package bitwise;

/**
 * Created by jpatel on 6/19/15.
 */
public class BitOperations {
    public static void main(String[] args) throws Exception {

        Integer key =8033393;
        int h;
        Object value = ((h=key.hashCode()) ^ (h >>> 16));
        System.out.println(h >>> 16);
        System.out.println(key + ": " +value);
        int a = -1;
        int b = 8;
        int e = (a >> 31), f = (a >>> 32);
        byte c = (byte)(a >> 1);
        byte d = (byte)(a >>> 1);
        System.out.println("Before: " + e + " " + f);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("After: " + a + " " + b);
    }
}


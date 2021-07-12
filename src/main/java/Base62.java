import java.util.TreeMap;
import java.util.TreeSet;

public class Base62 {

    public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private TreeMap<Character, Integer> ALPHABETTREE = new TreeMap<Character, Integer>();

    public static final int BASE = ALPHABET.length();

    public Base62() {
        for (int i = 0; i < 62; i++)
            ALPHABETTREE.put(ALPHABET.charAt(i), i);
    }

    public String fromBase10(long i) {
        StringBuilder sb = new StringBuilder("");
        if (i == 0) {
            return "a";
        }
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private long fromBase10(long i, final StringBuilder sb) {
        long rem = i % BASE;
        sb.append(ALPHABET.charAt((int)rem));
        return i / BASE;
    }

    public long toBase10(String str) {
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    private long toBase10(char[] chars) {
        long n = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            n += toBase10(ALPHABETTREE.get(chars[i]), i);
        }
        return n;
    }

    private static long toBase10(long n, int pow) {
        return n * (long) Math.pow(BASE, pow);
    }
}
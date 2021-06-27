public class Base62 {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final long    BASE     = ALPHABET.length();

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while ( num > 0 ) {
            sb.append( ALPHABET.charAt( (int)num % (int)BASE)  );
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static long decode(String str) {
        long num = 0;
        for ( int i = 0; i < str.length(); i++ )
            num = num * BASE + ALPHABET.indexOf(str.charAt(i));
        return num;
    }
}
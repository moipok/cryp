import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.GenericDomain;
import com.idealista.fpe.config.GenericTransformations;
import com.idealista.fpe.transformer.IntToTextTransformer;
import com.idealista.fpe.transformer.TextToIntTransformer;

public class DomainBuilder {
    private static final Alphabet ALPHABET = new Alphabet2();
    private static final TextToIntTransformer TEXT_TO_INT_TRANSFORMER = new GenericTransformations(ALPHABET.availableCharacters());
    private static final IntToTextTransformer INT_TO_TEXT_TRANSFORMER = new GenericTransformations(ALPHABET.availableCharacters());
    public static final Domain DOMAIN = new GenericDomain(ALPHABET, TEXT_TO_INT_TRANSFORMER, INT_TO_TEXT_TRANSFORMER);

}

class Alphabet2 implements Alphabet {
    private static final String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    @Override
    public char[] availableCharacters() {
        return str.toCharArray();
    }

    @Override
    public Integer radix() {
        return 62;
    }
}

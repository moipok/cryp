import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.component.functions.prf.DefaultPseudoRandomFunction;
import com.idealista.fpe.config.LengthRange;

import java.util.Scanner;

public class Cryptography {

    public static void main(String[] args) {

        byte[] image1 = CruptoImage.getImage();
        byte[] image = new byte[32];
        byte[] key = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            key[i] = Byte.valueOf(String.valueOf(i));
        }
        for (int i = 0; i < 32; i++)
        {
            image[i] = image1[i%72];
        }
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(DomainBuilder.DOMAIN)
                .withPseudoRandomFunction(new DefaultPseudoRandomFunction(image))//задаю псевдо рандом картинкой кота)
                .withLengthRange(new LengthRange(2, 256))
                .build();

        System.out.println(image.length);
        Scanner scanner = new Scanner(System.in);
        Base62 base62 = new Base62();


        while (true)
        {
            System.out.println("Введите id - от 10000");
            Long id = scanner.nextLong();
            try {
                String base62s = base62.fromBase10(id);
                System.out.println("Line cryp62   : [" + base62 + "]");
                String cipherText = formatPreservingEncryption.encrypt(base62s, key);
                System.out.println("Line cryptFPE : [" + cipherText + "]");
                String plainText = formatPreservingEncryption.decrypt(cipherText, key);
                System.out.println("Line decrypt  : [" + plainText + "]");
                Long input = base62.toBase10(plainText);
                System.out.println("Test decrypt62: [" + input + "]");

            } catch (Exception e) {
                System.err.println("=(");
            }
        }
    }
}

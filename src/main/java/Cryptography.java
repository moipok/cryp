import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.component.functions.prf.DefaultPseudoRandomFunction;
import com.idealista.fpe.config.LengthRange;

import java.util.Scanner;

public class Cryptography {

    public static void main(String[] args) {

        byte[] image1 = CruptoImage.getImage();
        byte[] image = new byte[32];
        byte[] ii = new byte[8];
        for (int i = 0; i < 8; i++)
        {
            ii[i] = Byte.valueOf(String.valueOf(i));
        }
        for (int i = 0; i < 32; i++)
        {
            image[i] = image1[i%72];
        }
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(DomainBuilder.DOMAIN)
                .withPseudoRandomFunction(new DefaultPseudoRandomFunction(image))
                .withLengthRange(new LengthRange(2, 256))
                .build();

        System.out.println(image.length);
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            Long line = scanner.nextLong();
            try {
                System.out.println("Line input   : [" + line + "]");
                String base62 = Base62.encode(line);
                System.out.println("Line 62 encod: [" + base62 + "]");
                String cipherText = formatPreservingEncryption.encrypt(base62, ii);
                System.out.println("Line cryptFPE: [" + cipherText + "]");
                String plainText = formatPreservingEncryption.decrypt(cipherText, ii);
                System.out.println("Line decrypt : [" + plainText + "]");
                Long base62decode = Base62.decode(plainText);
                System.out.println("Line decryp62: [" + base62decode + "]");

            } catch (Exception e) {
                System.err.println("=(");
            }

        }
    }
}

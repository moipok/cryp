import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.component.functions.prf.DefaultPseudoRandomFunction;
import com.idealista.fpe.config.LengthRange;

import java.util.Scanner;

public class Base62FPELinkShortener {
    byte[] image1 = CruptoImage.getImage();
    byte[] image = new byte[32];
    byte[] key = new byte[8];
    FormatPreservingEncryption formatPreservingEncryption;
    Base62 base62 = new Base62();

    {
        for (int i = 0; i < 8; i++)
        {
            key[i] = Byte.valueOf(String.valueOf(i));
        }
        for (int i = 0; i < 32; i++)
        {
            image[i] = image1[i%72];
        }
        formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(DomainBuilder.DOMAIN)
                .withPseudoRandomFunction(new DefaultPseudoRandomFunction(image))//задаю псевдо рандом картинкой кота)
                .withLengthRange(new LengthRange(2, 256))
                .build();
    }

    public Base62FPELinkShortener() {
    }

    public String shortenLink(Long num)
    {
        String base62 = this.base62.fromBase10(num);
        String cipherText = formatPreservingEncryption.encrypt(base62, key);
        return cipherText;
    }

    public Long recoverLink(String str)
    {
        String plainText = formatPreservingEncryption.decrypt(str, key);
        Long input = this.base62.toBase10(plainText);
        return input;
    }



}

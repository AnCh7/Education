import org.apache.commons.codec.binary.Hex;
import org.junit.Before;

import java.util.ArrayList;

public class Week1Test {

    private ArrayList<Week1.DecryptionSet> _decryptionSets = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        _decryptionSets = Week1.createDecryptionSet();
    }

    // m1 ^ m2 ^ m2 = m1
    @org.junit.Test
    public void m1_xor_m2_xor_m2() throws Exception {
        for (Week1.DecryptionSet ds : _decryptionSets) {
            System.out.println("Cipher 1 - " + ds.cipher1.CipherHex);
            System.out.println("Cipher 2 - " + ds.cipher2.CipherHex);
            byte[] xor = Utils.XorHelper.xorChunks(ds.Cipher1XORCipher2, Week1.cribMessage);
            System.out.println("[ m1 ^ m2 ^ m2 = m1 ] - " + new String(xor, "UTF-8"));
            System.out.println("=================================================================");
        }
    }

    // c ^ m = k
    @org.junit.Test
    public void cipher_xor_message() throws Exception {
        for (Week1.Cipher cipher : Week1.getCiphers()) {
            byte[] key = Utils.XorHelper.xorChunks(cipher.CipherBytes, Week1.cribMessage);
            System.out.println("[ c1 ^ m1 = k ] - " + Hex.encodeHex(key));
        }
    }

    // c ^ k = m
    @org.junit.Test
    public void cipher_xor_key() throws Exception {
        for (Week1.Cipher cipher : Week1.getCiphers()) {
            byte[] message = Utils.XorHelper.xorChunks(cipher.CipherBytes, Hex.decodeHex(Week1.key.toCharArray()));
            String data = new String(message, "UTF-8");
            System.out.println("Message " + cipher.CipherId + " - " + data);
        }
    }
}
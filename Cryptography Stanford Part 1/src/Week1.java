import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.util.*;

public class Week1 {

    /*
    XOR space:
    a ^ SPACE = 01100001 ^ 00100000 = 01000001 = A
    A ^ SPACE = a ^ SP ^ SP = a

    If plain text m1 and m2, then
    c1 = m1 ^ k,
    c2 = m2 ^ k,
    m1 = c1 ^ k,
    m2 = c2 ^ k
    k = c1 ^ m1
    k = c2 ^ m2
    c1 ^ c2 = m1 ^ k ^ m2 ^ k = m1 ^ m2
    m1 ^ m2 ^ m2' = m1 (m2' - crib text)

    Message 01 - We can factor the number 15 with quantum computers. We can also factor the number 15 with a dog trained to bark three times - Robert Harley
    Message 02 - Euler would probably enjoy that now his theorem becomes a corner stone of crypto - Annonymous on Euler's theorem
    Message 03 - The nice thing about Keeyloq is now we cryptographers can drive a lot of fancy cars - Dan Boneh
    Message 04 - The ciphertext produced by a weak encryption algorithm looks as good as ciphertext produced by a strong encryption algorithm - Philip Zimmermann
    Message 05 - You don't want to buy a set of car keys from a guy who specializes in stealing cars - Marc Rotenberg commenting on Clipper
    Message 06 - There are two types of cryptography - that which will keep secrets safe from your little sister, and that which will keep secrets safe from your government - Bruce Schneier
    Message 07 - There are two types of cyptography: one that allows the Government to use brute force to break the code, and one that requires the Government to use brute to break you
    Message 08 - We can see the point where the chip is unhappy if a wrong bit is sent and consumes more power from the environment - Adi Shamir
    Message 09 - A (private-key)  encryption scheme states 3 algorithms, namely a procedure for generating keys, a procedure for encrypting, and a procedure for decrypting.�
    Message 10 -  The Concise OxfordDictionary (2006) deﬁnes crypto as the art of  writing o r solving codes.
    Message 11 - The secret message is: When using a stream cipher, never use the key more than once

    */

    private static String cipherText1 = "315c4eeaa8b5f8aaf9174145bf43e1784b8fa00dc71d885a804e5ee9fa40b16349c146fb778cdf2d3aff021dfff5b" +
            "403b510d0d0455468aeb98622b137dae857553ccd8883a7bc37520e06e515d22c954eba5025b8cc57ee59418ce7dc6bc41556bdb36bbca3e8774301f" +
            "bcaa3b83b220809560987815f65286764703de0f3d524400a19b159610b11ef3e";

    private static String cipherText2 = "234c02ecbbfbafa3ed18510abd11fa724fcda2018a1a8342cf064bbde548b12b07df44ba7191d9606ef4081ffde5a" +
            "d46a5069d9f7f543bedb9c861bf29c7e205132eda9382b0bc2c5c4b45f919cf3a9f1cb74151f6d551f4480c82b2cb24cc5b028aa76eb7b4ab24171ab" +
            "3cdadb8356f";

    private static String cipherText3 = "32510ba9a7b2bba9b8005d43a304b5714cc0bb0c8a34884dd91304b8ad40b62b07df44ba6e9d8a2368e51d04e0e7b" +
            "207b70b9b8261112bacb6c866a232dfe257527dc29398f5f3251a0d47e503c66e935de81230b59b7afb5f41afa8d661cb";

    private static String cipherText4 = "32510ba9aab2a8a4fd06414fb517b5605cc0aa0dc91a8908c2064ba8ad5ea06a029056f47a8ad3306ef5021eafe1a" +
            "c01a81197847a5c68a1b78769a37bc8f4575432c198ccb4ef63590256e305cd3a9544ee4160ead45aef520489e7da7d835402bca670bda8eb775200b" +
            "8dabbba246b130f040d8ec6447e2c767f3d30ed81ea2e4c1404e1315a1010e7229be6636aaa";

    private static String cipherText5 = "3f561ba9adb4b6ebec54424ba317b564418fac0dd35f8c08d31a1fe9e24fe56808c213f17c81d9607cee021dafe1e" +
            "001b21ade877a5e68bea88d61b93ac5ee0d562e8e9582f5ef375f0a4ae20ed86e935de81230b59b73fb4302cd95d770c65b40aaa065f2a5e33a5a0bb" +
            "5dcaba43722130f042f8ec85b7c2070";

    private static String cipherText6 = "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd2061bbde24eb76a19d84aba34d8de287be84d07e7e9a" +
            "30ee714979c7e1123a8bd9822a33ecaf512472e8e8f8db3f9635c1949e640c621854eba0d79eccf52ff111284b4cc61d11902aebc66f2b2e436434ea" +
            "cc0aba938220b084800c2ca4e693522643573b2c4ce35050b0cf774201f0fe52ac9f26d71b6cf61a711cc229f77ace7aa88a2f19983122b11be87a59" +
            "c355d25f8e4";

    private static String cipherText7 = "32510bfbacfbb9befd54415da243e1695ecabd58c519cd4bd90f1fa6ea5ba47b01c909ba7696cf606ef40c04afe1a" +
            "c0aa8148dd066592ded9f8774b529c7ea125d298e8883f5e9305f4b44f915cb2bd05af51373fd9b4af511039fa2d96f83414aaaf261bda2e97b170fb" +
            "5cce2a53e675c154c0d9681596934777e2275b381ce2e40582afe67650b13e72287ff2270abcf73bb028932836fbdecfecee0a3b894473c1bbeb6b49" +
            "13a536ce4f9b13f1efff71ea313c8661dd9a4ce";

    private static String cipherText8 = "315c4eeaa8b5f8bffd11155ea506b56041c6a00c8a08854dd21a4bbde54ce56801d943ba708b8a3574f40c00fff9e" +
            "00fa1439fd0654327a3bfc860b92f89ee04132ecb9298f5fd2d5e4b45e40ecc3b9d59e9417df7c95bba410e9aa2ca24c5474da2f276baa3ac325918b" +
            "2daada43d6712150441c2e04f6565517f317da9d3";

    private static String cipherText9 = "271946f9bbb2aeadec111841a81abc300ecaa01bd8069d5cc91005e9fe4aad6e04d513e96d99de2569bc5e50eeeca" +
            "709b50a8a987f4264edb6896fb537d0a716132ddc938fb0f836480e06ed0fcd6e9759f40462f9cf57f4564186a2c1778f1543efa270bda5e933421cb" +
            "e88a4a52222190f471e9bd15f652b653b7071aec59a2705081ffe72651d08f822c9ed6d76e48b63ab15d0208573a7eef027";

    private static String cipherText10 = "466d06ece998b7a2fb1d464fed2ced7641ddaa3cc31c9941cf110abbf409ed39598005b3399ccfafb61d0315fca0" +
            "a314be138a9f32503bedac8067f03adbf3575c3b8edc9ba7f537530541ab0f9f3cd04ff50d66f1d559ba520e89a2cb2a83";

    private static String cipherTextTarget = "32510ba9babebbbefd001547a810e67149caee11d945cd7fc81a05e9f85aac650e9052ba6a8cd8257bf14d13" +
            "e6f0a803b54fde9e77472dbff89d71b57bddef121336cb85ccb8f3315f4b52e301d16e9f52f904";

    public static String cribMessage = "";
    public static String key = "66396e89c9dbd8cc9874352acd6395102eafce78aa7fed28a07f6bc98d29c50b69b0339a19f8aa401a9c6d708f80c066c763fef0123148cdd8e802d05ba98777335daefcecd59c433a6b268b60bf4ef03c9a611098bb3e9a3161edc7b804a33522cfd202d2c68c57376edba8c2ca50027c61246ce2a12b0c4502175010c0a1ba4625786d911100797d8a47e98b0204c4ef06c867a950f11ac989deba8f83dae6225d709ecfdbe41a";

    public static class DecryptionSet {
        public Cipher cipher1;
        public Cipher cipher2;
        public byte[] Cipher1XORCipher2;
    }

    public static class Cipher {
        public String CipherId;
        public String CipherHex;
        public byte[] CipherBytes;

        public Cipher(String cipherId, String cipherHex, byte[] cipherBytes) {
            CipherId = cipherId;
            CipherHex = cipherHex;
            CipherBytes = cipherBytes;
        }
    }

    public static ArrayList<Cipher> getCiphers() throws DecoderException {
        ArrayList<Cipher> list = new ArrayList<>();
        list.add(new Cipher("01", cipherText1, Hex.decodeHex(cipherText1.toCharArray())));
        list.add(new Cipher("02", cipherText2, Hex.decodeHex(cipherText2.toCharArray())));
        list.add(new Cipher("03", cipherText3, Hex.decodeHex(cipherText3.toCharArray())));
        list.add(new Cipher("04", cipherText4, Hex.decodeHex(cipherText4.toCharArray())));
        list.add(new Cipher("05", cipherText5, Hex.decodeHex(cipherText5.toCharArray())));
        list.add(new Cipher("06", cipherText6, Hex.decodeHex(cipherText6.toCharArray())));
        list.add(new Cipher("07", cipherText7, Hex.decodeHex(cipherText7.toCharArray())));
        list.add(new Cipher("08", cipherText8, Hex.decodeHex(cipherText8.toCharArray())));
        list.add(new Cipher("09", cipherText9, Hex.decodeHex(cipherText9.toCharArray())));
        list.add(new Cipher("10", cipherText10, Hex.decodeHex(cipherText10.toCharArray())));
        list.add(new Cipher("11", cipherTextTarget, Hex.decodeHex(cipherTextTarget.toCharArray())));
        return list;
    }

    public static ArrayList<Week1.DecryptionSet> createDecryptionSet() throws DecoderException {

        ArrayList<Week1.DecryptionSet> sets = new ArrayList<>();
        ArrayList<Week1.Cipher> ciphers = Week1.getCiphers();
        List<LinkedHashSet<Week1.Cipher>> permutations = new Utils.OrderedPowerSet(ciphers).getPermutationsList(2);

        for (LinkedHashSet<Week1.Cipher> permutation : permutations) {
            Week1.DecryptionSet set = new Week1.DecryptionSet();
            Iterator<Week1.Cipher> i = permutation.iterator();
            set.cipher1 = i.next();
            set.cipher2 = i.next();
            set.Cipher1XORCipher2 = Utils.XorHelper.xor(set.cipher1.CipherBytes, set.cipher2.CipherBytes);
            sets.add(set);
        }

        return sets;
    }
}


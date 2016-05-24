import org.apache.geronimo.mail.util.Hex;
import org.junit.Assert;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Week2 {

    public static byte[] decrypt(byte[] key, byte[] initVector, byte[] encrypted, String mode) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");

            Cipher cipher = Cipher.getInstance(mode);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            return cipher.doFinal(encrypted);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @org.junit.Test
    public void test1() throws Exception {

        // Arrange
        String cbcKeyHex = "140b41b22a29beb4061bda66b6747e14";
        byte[] cbcKeyBytes = Hex.decode(cbcKeyHex);

        String cbcCipherText1Hex = "28a226d160dad07883d04e008a7897ee2e4b7465d5290d0c0e6c6822236e1daafb94ffe0c5da05d9476be028ad7c1d81";
        byte[] cbcCipherText1 = Hex.decode(cbcCipherText1Hex);

        String cbcCipherText2Hex = "b4832d0f26e1ab7da33249de7d4afc48e713ac646ace36e872ad5fb8a512428a6e21364b0c374df45503473c5242a253";
        byte[] cbcCipherText2 = Hex.decode(cbcCipherText2Hex);

        String initVector1Hex = "4ca00ff4c898d61e1edbf1800618fb28";
        byte[] initVector1Bytes = Hex.decode(initVector1Hex);

        String initVector2Hex = "5b68629feb8606f9a6667670b75b38a5";
        byte[] initVector2Bytes = Hex.decode(initVector2Hex);

        String cipherMode = "AES/CBC/PKCS5PADDING"; // algorithm/mode/padding

        // Act
        byte[] decrypted1 = decrypt(cbcKeyBytes, initVector1Bytes, cbcCipherText1, cipherMode);
        byte[] decrypted2 = decrypt(cbcKeyBytes, initVector2Bytes, cbcCipherText2, cipherMode);
        String decryptedText1 = new String(decrypted1, "UTF-8");
        String decryptedText2 = new String(decrypted2, "UTF-8");

        // Assert
        Assert.assertNotNull(decryptedText1);
        Assert.assertNotNull(decryptedText2);
    }

    @org.junit.Test
    public void test2() throws Exception {

        // Arrange
        String cbcKeyHex = "36f18357be4dbd77f050515c73fcf9f2";
        byte[] cbcKeyBytes = Hex.decode(cbcKeyHex);

        String cbcCipherText1Hex = "0ec7702330098ce7f7520d1cbbb20fc388d1b0adb5054dbd7370849dbf0b88d393f252e764f1f5f7ad97ef79d59ce29f5f51eeca32eabedd9afa9329";
        byte[] cbcCipherText1Bytes = Hex.decode(cbcCipherText1Hex);

        String cbcCipherText2Hex = "e46218c0a53cbeca695ae45faa8952aa0e311bde9d4e01726d3184c34451";
        byte[] cbcCipherText2Bytes = Hex.decode(cbcCipherText2Hex);

        String initVector1Hex = "69dda8455c7dd4254bf353b773304eec";
        byte[] initVector1Bytes = Hex.decode(initVector1Hex);

        String initVector2Hex = "770b80259ec33beb2561358a9f2dc617";
        byte[] initVector2Bytes = Hex.decode(initVector2Hex);

        String cipherMode = "AES/CTR/NoPadding"; // algorithm/mode/padding

        // Act
        byte[] decrypted1 = decrypt(cbcKeyBytes, initVector1Bytes, cbcCipherText1Bytes, cipherMode);
        byte[] decrypted2 = decrypt(cbcKeyBytes, initVector2Bytes, cbcCipherText2Bytes, cipherMode);
        String decryptedText1 = new String(decrypted1, "UTF-8");
        String decryptedText2 = new String(decrypted2, "UTF-8");

        // Assert
        Assert.assertNotNull(decryptedText1);
        Assert.assertNotNull(decryptedText2);
    }
}
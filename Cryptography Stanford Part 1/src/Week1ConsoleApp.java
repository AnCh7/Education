import com.google.common.base.Strings;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class Week1ConsoleApp {

    private static ArrayList<Week1.DecryptionSet> _decryptionSets = new ArrayList<>();

    public static void main(String[] args) throws IOException, DecoderException {
        _decryptionSets = Week1.createDecryptionSet();
        xorMessages(null);
        showMenu();
    }

    public static int showMenu() throws IOException, DecoderException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("[1] Enter crib message");
            System.out.println("[2] View keys");
            System.out.println("[0] Exit");

            String selection = scanner.next();
            switch (selection) {

                case "1":
                    System.out.print("Crib : ");
                    BufferedReader readerText = new BufferedReader(new InputStreamReader(System.in));
                    Week1.cribMessage = readerText.readLine();
                    xorMessages(Week1.cribMessage);
                    System.out.println("Crib's length : " + Week1.cribMessage.toCharArray().length);
                    break;

                case "2":
                    System.out.println("Row : ");
                    selection = scanner.next();
                    Week1.DecryptionSet ciphers = _decryptionSets.get(Integer.parseInt(selection));
                    byte[] key1 = Utils.XorHelper.xorChunks(Week1.cribMessage.getBytes(), ciphers.cipher1.CipherBytes);
                    System.out.println(Hex.encodeHexString(key1));
                    byte[] key2 = Utils.XorHelper.xorChunks(Week1.cribMessage.getBytes(), ciphers.cipher2.CipherBytes);
                    System.out.println(Hex.encodeHexString(key2));
                    break;

                case "0":
                    System.out.println("Exiting...");
                    System.exit(0);

                default:
                    System.out.println("Please enter a valid selection.");
            }
        }
    }

    public static void xorMessages(String cribMessage) throws UnsupportedEncodingException {
        int row = 0;
        for (Week1.DecryptionSet ds : _decryptionSets) {
            byte[] characters;
            if (Strings.isNullOrEmpty(cribMessage)) {
                characters = ds.Cipher1XORCipher2;
            } else {
                characters = Utils.XorHelper.xorChunks(ds.Cipher1XORCipher2, cribMessage);
            }
            StringBuilder stringBuilder = processCharacters(characters);
            System.out.println("Row " + row + " [ " + ds.cipher1.CipherId + " ^ " + ds.cipher2.CipherId + " ]   " + stringBuilder.toString());
            row++;
        }
    }

    public static StringBuilder processCharacters(byte[] bytes) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        for (char ch : new String(bytes, "UTF-8").toCharArray()) {
            checkCharacter(stringBuilder, ch);
        }
        return stringBuilder;
    }

    public static void checkCharacter(StringBuilder stringBuilder, char ch) {
        if (Pattern.matches("\\p{Punct}", Character.toString(ch))) {
            stringBuilder.append(ch);
        } else if (Character.isLetterOrDigit(ch)) {
            stringBuilder.append(ch);
        } else if (Character.isSpaceChar(ch)) {
            stringBuilder.append(' ');
        } else {
            stringBuilder.append('_');
        }
    }
}



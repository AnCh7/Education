import com.google.common.io.Files;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Week3 {

    private String folder = "d:\\Programming\\Education\\Cryptography I\\Week3\\";

    @org.junit.Test
    public void run_example() throws Exception {

        String hashResult = "03c08f4ee0b576fe319338139c045c89c3e8e9409633bea29442e21425006ea8";
        String filename = "6 - 2 - Generic birthday attack (16 min)_practice.mp4";
        File file = new File(folder + filename);
        byte[] fileBytes = Files.toByteArray(file);
        String hash = computeHash(fileBytes);
        Assert.assertEquals(hashResult, hash);
    }

    @org.junit.Test
    public void run_target() throws Exception {

        String filename = "6 - 1 - Introduction (11 min)_task.mp4";
        File file = new File(folder + filename);
        byte[] fileBytes = Files.toByteArray(file);
        String hash = computeHash(fileBytes);
        Assert.assertNotNull(hash);
    }

    private String computeHash(byte[] fileBytes) throws NoSuchAlgorithmException {

        byte[] hashBytes = new byte[0];
        int chunkSize = 1024;
        byte[] chunk;
        int totalChunks = fileBytes.length / chunkSize;
        for (int from = fileBytes.length; from >= 0; from -= chunkSize) {
            int to;
            if (from == fileBytes.length) {
                int lastChunkSize = fileBytes.length - totalChunks * chunkSize;
                from = fileBytes.length - lastChunkSize;
                to = fileBytes.length;
            } else {
                to = from + chunkSize;
            }

            chunk = Arrays.copyOfRange(fileBytes, from, to);
            byte[] block = ArrayUtils.addAll(chunk, hashBytes);
            hashBytes = Utils.Sha.hash256(block);
        }

        return Hex.encodeHexString(hashBytes);
    }
}

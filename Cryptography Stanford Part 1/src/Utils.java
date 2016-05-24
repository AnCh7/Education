import com.google.common.collect.Lists;
import com.google.common.primitives.Bytes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Utils {

    public static class OrderedPowerSet<E> {

        private static final int ELEMENT_LIMIT = 12;
        private List<E> inputList;
        public int N;
        private Map<Integer, List<LinkedHashSet<E>>> map = new HashMap<>();

        public OrderedPowerSet(List<E> list) {
            inputList = list;
            N = list.size();
            if (N > ELEMENT_LIMIT) {
                throw new RuntimeException("List with more then " + ELEMENT_LIMIT + " elements is too long...");
            }
        }

        public List<LinkedHashSet<E>> getPermutationsList(int elementCount) {
            if (elementCount < 1 || elementCount > N) {
                throw new IndexOutOfBoundsException("Can only generate permutations for a count between 1 to " + N);
            }
            if (map.containsKey(elementCount)) {
                return map.get(elementCount);
            }

            ArrayList<LinkedHashSet<E>> list = new ArrayList<>();
            if (elementCount == N) {
                list.add(new LinkedHashSet<>(inputList));
            } else if (elementCount == 1) {
                for (int i = 0; i < N; i++) {
                    LinkedHashSet<E> set = new LinkedHashSet<>();
                    set.add(inputList.get(i));
                    list.add(set);
                }
            } else {
                list = new ArrayList<>();
                for (int i = 0; i <= N - elementCount; i++) {
                    @SuppressWarnings("unchecked")
                    ArrayList<E> subList = (ArrayList<E>) ((ArrayList<E>) inputList).clone();
                    for (int j = i; j >= 0; j--) {
                        subList.remove(j);
                    }

                    OrderedPowerSet<E> subPowerSet = new OrderedPowerSet<>(subList);
                    List<LinkedHashSet<E>> pList = subPowerSet.getPermutationsList(elementCount - 1);
                    for (LinkedHashSet<E> s : pList) {
                        LinkedHashSet<E> set = new LinkedHashSet<E>();
                        set.add(inputList.get(i));
                        set.addAll(s);
                        list.add(set);
                    }
                }
            }
            map.put(elementCount, list);
            return map.get(elementCount);
        }
    }

    public static class XorHelper {

        public static byte[] xorChunks(byte[] cipher, String key) {
            byte[] keyBytes = key.getBytes();
            return xorChunks(cipher, keyBytes);
        }

        public static byte[] xorChunks(byte[] cipher, byte[] key) {
            List<Byte> result = new ArrayList<>();
            if (cipher.length > key.length) {
                // split cipher into key.length chunks
                List<Byte> cipherList = new ArrayList<>();
                cipherList.addAll(Bytes.asList(cipher));
                List<List<Byte>> subSets = Lists.partition(cipherList, key.length);

                for (List<Byte> subSet : subSets) {
                    byte[] tmp = xor(Bytes.toArray(subSet), key);
                    result.addAll(Bytes.asList(tmp));
                }
            } else {
                byte[] tmp = xor(cipher, key);
                result.addAll(Bytes.asList(tmp));
            }
            return Bytes.toArray(result);
        }

        public static byte[] xor(byte[] array1, byte[] array2) {
            byte[] result = new byte[Math.min(array1.length, array2.length)];
            for (int i = 0; i < result.length; i++) {
                result[i] = (byte) (((int) array1[i]) ^ ((int) array2[i]));
            }
            return result;
        }
    }

    public static class Sha {

        public static byte[] hash256(byte[] data) throws NoSuchAlgorithmException {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(data);
            byte[] digest = md.digest();
            return digest;
        }
    }
}

package pl.edu.pw.ee.compression.converters;

import java.util.Collections;

public class BinaryToBytesTextConverter {

    public static final int N_OF_BITS_IN_BYTE = 8;

    private final String binarySequence;
    private int nOfBitsInCompressedFile = 0;

    public BinaryToBytesTextConverter(String binarySequence) {
        validateBinarySequence(binarySequence);
        this.binarySequence = binarySequence;
    }

    public byte[] convert() {
        int binaryLength = binarySequence.length();
        int nOfBytesInCompressedFile = binaryLength / N_OF_BITS_IN_BYTE
                + (binaryLength % N_OF_BITS_IN_BYTE == 0 ? 0 : 1);
        byte[] bytes = new byte[nOfBytesInCompressedFile];

        this.nOfBitsInCompressedFile = nOfBytesInCompressedFile
                * N_OF_BITS_IN_BYTE;

        for (int start = 0; start < binaryLength; start += N_OF_BITS_IN_BYTE) {
            int end = Integer.min(start + N_OF_BITS_IN_BYTE, binaryLength);
            byte b;

            if (end - start == N_OF_BITS_IN_BYTE) {
                String byteString = binarySequence.substring(start, end);
                b = (byte) (int) Integer.valueOf(byteString, 2);
            } else {
                String byteString = binarySequence.substring(start, end)
                        + String.join("", Collections.nCopies(
                                N_OF_BITS_IN_BYTE - (end - start), "0"));
                b = (byte) (int) Integer.valueOf(byteString, 2);
            }
            bytes[start / N_OF_BITS_IN_BYTE] = b;
        }

        return bytes;
    }

    public int getNOfBitsInCompressedFile() {
        return nOfBitsInCompressedFile;
    }

    private void validateBinarySequence(String binarySequence) {
        if (binarySequence == null) {
            throw new IllegalArgumentException("Argument binarySequence is null.");
        }
        if (binarySequence.chars().anyMatch(c -> c != '0' && c != '1')) {
            throw new IllegalArgumentException(
                    "Binary sequence contains character different than zero or one.");
        }
    }
}

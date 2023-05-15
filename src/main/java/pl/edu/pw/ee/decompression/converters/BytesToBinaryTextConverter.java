package pl.edu.pw.ee.decompression.converters;

public class BytesToBinaryTextConverter {

    public static final int MIN_OVERFLOW_BIT_VALUE_FOR_BYTE = 256;
    public static final int N_OF_BITS_IN_BYTE = 8;

    private final byte[] compressedText;

    public BytesToBinaryTextConverter(byte[] compressedText) {
        validateBytesArray(compressedText);
        this.compressedText = compressedText;
    }

    public String convert() {
        StringBuilder sb = new StringBuilder();
        for (byte actualByte : compressedText) {
            String decompressedByte = Integer.toBinaryString(
                    (actualByte + MIN_OVERFLOW_BIT_VALUE_FOR_BYTE)
                    % MIN_OVERFLOW_BIT_VALUE_FOR_BYTE);
            decompressedByte = addLeadingZerosToByte(decompressedByte);
            sb.append(decompressedByte);
        }

        return sb.toString();
    }

    private String addLeadingZerosToByte(String byteS) {
        if (byteS.length() == N_OF_BITS_IN_BYTE) {
            return byteS;
        }

        StringBuilder sb = new StringBuilder(byteS);
        while (sb.length() < N_OF_BITS_IN_BYTE) {
            sb.insert(0, '0');
        }
        return sb.toString();
    }

    private void validateBytesArray(byte[] bytes) {
        if (bytes == null) {
            throw new IllegalArgumentException(
                    "Argument compressedText - byte[] is null.");
        }
    }
}

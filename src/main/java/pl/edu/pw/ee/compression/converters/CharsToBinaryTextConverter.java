package pl.edu.pw.ee.compression.converters;

import java.util.Map;

public class CharsToBinaryTextConverter {

    private final Map<Character, String> encoding;
    private final char[] text;
    private int nOfActualBits = 0;

    public CharsToBinaryTextConverter(Map<Character, String> encoding,
            char[] text) {

        validateEncoding(encoding);
        validateCharsArray(text);
        this.encoding = encoding;
        this.text = text;
    }

    public String convert() {
        StringBuilder sb = new StringBuilder();

        for (char character : text) {
            if (!encoding.containsKey(character)) {
                throw new IllegalStateException(
                        "Read character doesn't have its own code.");
            }
            sb.append(encoding.get(character));
        }

        nOfActualBits = sb.length();

        return sb.toString();
    }

    public int getNOfActualBits() {
        return nOfActualBits;
    }

    private void validateEncoding(Map<Character, String> encoding) {
        if (encoding == null) {
            throw new IllegalArgumentException("Encoding is null.");
        }
        if (encoding.isEmpty()) {
            throw new IllegalArgumentException("Encoding is empty.");
        }
    }

    private void validateCharsArray(char[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array is null.");
        }
        if (array.length == 0) {
            throw new IllegalArgumentException("Array is of size 0.");
        }
    }
}
